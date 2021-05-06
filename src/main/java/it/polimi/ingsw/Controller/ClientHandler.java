package it.polimi.ingsw.Controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.*;
import it.polimi.ingsw.ExtraDeposit;
import it.polimi.ingsw.LeaderCard;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.ResourceType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private final Controller controller;
    private final Socket socket;
    private PrintWriter out;
    private String clientNickname;

    public ClientHandler(Socket socket, Controller controller) {
        this.socket = socket;
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            Gson gson = new Gson();
            socket.setSoTimeout(15000);
            // Leggo e scrivo nella connessione finche' non ricevo "quit"
            Message mex = new Message();
            mex.setMessageType("ConnectionAcceptedPleaseLoginNotification");
            String s = gson.toJson(mex);
            out.println(s);
            out.flush();
            String line;
            ServerPing serverPing= new ServerPing(out);
            Thread t = new Thread(serverPing);
            t.start();
            //LOGIN PHASE
            boolean loginDone=false;
            while(true){
                if(loginDone)
                    break;
                line = in.readLine();
                Message message = gson.fromJson(line, Message.class);
                if(message.getMessageType().equals("AddPlayer")){
                    String response = controller.startAction(line);
                    Message resp = gson.fromJson(response, Message.class);
                    AddPlayerMessage addMessage = gson.fromJson(line,AddPlayerMessage.class);
                    String tempNickname = addMessage.getSenderNickname();
                    if(resp.getMessageType().equals("PlayerAddedNotification")){
                        out.println(response);
                        out.flush();
                        clientNickname=tempNickname;
                        loginDone=true;
                        break;
                    }
                    else{
                        synchronized (controller) {
                            for (Player p : controller.getGame().getPlayers()) {
                                if (p.isAFK() && p.getNickname().equals(tempNickname)) {
                                    //Il player era disconnesso e si è riconnesso
                                    p.setIsAFK(false);
                                    mex = new Message();
                                    mex.setMessageType("LoginOkNotification");
                                    PlayerInNotification playerInNotification=new PlayerInNotification();
                                    playerInNotification.setMessageType("PlayerInNotification");
                                    playerInNotification.setSenderNickname(tempNickname);
                                    for(ClientHandler c: controller.getConnectedClients())
                                        c.notifyInterface(gson.toJson(playerInNotification));
                                    for(Player player: controller.getGame().getPlayers()){
                                        ReconnectConfigurationMessage reconnectConfigurationMessage= new ReconnectConfigurationMessage();
                                        reconnectConfigurationMessage.setSenderNickname(player.getNickname());
                                        reconnectConfigurationMessage.setMarbleGridConfiguration(controller.getGame().getMarketBoard().getMarketGrid());
                                        reconnectConfigurationMessage.setMarbleOut(controller.getGame().getMarketBoard().getMarbleOut());
                                        reconnectConfigurationMessage.setDeckgridConfiguration(controller.getGame().getDeckgrid());
                                        reconnectConfigurationMessage.setWareHouseConfiguration(player.getPersonalBoard().getWarehouseDepot());
                                        reconnectConfigurationMessage.setStrongboxConfiguration(player.getPersonalBoard().getStrongbox());
                                        ArrayList<ExtraDeposit> extraDeposits = new ArrayList<>();
                                        extraDeposits.add(player.getPersonalBoard().getExtraDeposit1());
                                        extraDeposits.add(player.getPersonalBoard().getExtraDeposit2());
                                        reconnectConfigurationMessage.setExtraDepositConfiguration(extraDeposits);
                                        reconnectConfigurationMessage.setNewFaithPoints(player.getFaithPoints());
                                        reconnectConfigurationMessage.setDevelopmentCardsConfiguration(player.getPersonalBoard().getDevelopmentCard());
                                        if(player.getNickname().equals(tempNickname)){
                                            System.out.println("1");
                                            if(player.hasChosenLeaderCards()){
                                                System.out.println("2");
                                                for(LeaderCard l: player.getChoosedLeaderCards()){
                                                    reconnectConfigurationMessage.getChoosedLeaderCards().add(l.getLeaderCardNumber());
                                                }
                                            }
                                            else{
                                                System.out.println("3");
                                                for(LeaderCard l: player.getChoosableLeaderCards()){
                                                    reconnectConfigurationMessage.getChoosableLeaderCards().add(l.getLeaderCardNumber());
                                                }
                                                System.out.println("5");
                                            }
                                        }
                                        else if(player.hasChosenLeaderCards()){
                                           for(LeaderCard l: player.getChoosedLeaderCards()){
                                               if(l.isActive()){
                                                   reconnectConfigurationMessage.getChoosedLeaderCards().add(l.getLeaderCardNumber());
                                               }
                                           }
                                        }
                                        System.out.println("4");
                                        out.println(gson.toJson(reconnectConfigurationMessage));
                                    }
                                    s = gson.toJson(mex);
                                    out.println(s);
                                    out.flush();
                                    clientNickname = tempNickname;
                                    loginDone=true;
                                    break;
                                }
                            }
                        }
                        mex= new Message();
                        mex.setMessageType("InvalidLoginNotification");
                        s=gson.toJson(mex);
                        out.println(s);
                        out.flush();
                    }
                }
                else{
                    mex= new Message();
                    mex.setMessageType("ExpectedLoginRequestNotification");
                    s=gson.toJson(mex);
                    out.println(s);
                    out.flush();
                }
            }
            //LOGIN PHASE END

            synchronized (controller) {
                controller.addClientHandler(this);
            }
            while ((line = in.readLine()) != null) {
                if (line.equals("quit") ) {
                    break;
                } else if(!line.equals("Pong")){
                    out.println(controller.startAction(line));
                    out.flush();
                }
            }
            // Chiudo lo stream e i socket
            in.close();
            out.close();
            socket.close();
            return;
        } catch(SocketTimeoutException e){
            System.err.println("Il client non risponde");
        } catch (IOException e) {
            System.err.println("IO chiamata dal client handler");
        }
        synchronized (controller){
            Gson gson = new Gson();
            controller.getConnectedClients().remove(this);
            if(clientNickname.equals(controller.getGame().getCurrentPlayer().getNickname())){
                controller.getGame().getCurrentPlayer().setIsAFK(true);
                controller.getGame().endTurn();
                String nickname=null;
                String winnerNickname=null;
                int tempResourcesDiscarded=0;
                boolean endGame=false;
                for(ResourceType res: controller.getGame().getMarketBoard().getTemporaryResources().keySet()){
                    tempResourcesDiscarded+=controller.getGame().getMarketBoard().getTemporaryResources().get(res);
                }
                nickname=controller.getGame().getCurrentPlayer().getNickname();
                endGame=controller.getGame().isEndGame();
                if(controller.getGame().getWinnerPlayer()!=null)
                    winnerNickname=controller.getGame().getWinnerPlayer().getNickname();
                EndTurnNotificationMessage endTurnNotificationMessage= new EndTurnNotificationMessage();
                endTurnNotificationMessage.setActualCurrentPlayer(nickname);
                endTurnNotificationMessage.setNumResourcesDiscarded(tempResourcesDiscarded);
                endTurnNotificationMessage.setWinnerPlayerNickname(winnerNickname);
                endTurnNotificationMessage.setGameEnding(endGame);
                for(ClientHandler c: controller.getConnectedClients())
                    c.notifyInterface(gson.toJson(endTurnNotificationMessage));
            }
            else{
                for(Player p: controller.getGame().getPlayers()){
                    if(p.getNickname().equals(clientNickname))
                        p.setIsAFK(true);
                }
            }
            PlayerOutNotification mex = new PlayerOutNotification();
            mex.setMessageType("PlayerOutNotification");
            mex.setSenderNickname(clientNickname);
            for(ClientHandler c: controller.getConnectedClients()){
                c.notifyInterface(gson.toJson(mex));
            }
        }
    }


    public void notifyInterface(String notification) {
        out.println(notification);
        out.flush();
    }

    public String getClientNickname() {
        return clientNickname;
    }
}