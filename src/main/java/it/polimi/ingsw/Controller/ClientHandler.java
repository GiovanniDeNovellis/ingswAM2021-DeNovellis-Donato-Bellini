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

/**
 * This class represents the handler of the local game mode.
 * Read the input of the game and execute the different command.
 * At the end, notify the different interface with the data.
 * Manage the phase of the login and the different disconnections.
 */
public class ClientHandler implements Runnable, NotifiableHandler {
    private ServerPing serverPing;
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
            this.serverPing=serverPing;
            Thread t = new Thread(serverPing);
            t.start();
            //LOGIN PHASE
            boolean loginDone=false;
            while(true){
                do {
                    line = in.readLine();
                } while (line.equals("Pong"));
                Message message = gson.fromJson(line, Message.class);
                if(message.getMessageType().equals("AddPlayer")){
                    String response = controller.startAction(line);
                    Message resp = gson.fromJson(response, Message.class);
                    AddPlayerMessage addMessage = gson.fromJson(line,AddPlayerMessage.class);
                    String tempNickname = addMessage.getSenderNickname();
                    if(resp.getMessageType().equals("PlayerAddedNotification")){
                        LoginOkNotificationMessage m = new LoginOkNotificationMessage();
                        m.setMessageType("LoginOkNotification");
                        m.setSenderNickname(tempNickname);
                        out.println(gson.toJson(m));
                        out.flush();
                        ConnectedPlayersMessage c = new ConnectedPlayersMessage();
                        c.setMessageType("ConnectedPlayersMessage");
                        for(Player p : controller.getGame().getPlayers()){
                            c.getConnectedPlayers().add(p.getNickname());
                        }
                        out.println(gson.toJson(c));
                        out.flush();
                        clientNickname=tempNickname;
                        break;
                    }
                    else{
                        synchronized (controller) {
                            for (Player p : controller.getGame().getPlayers()) {
                                if (p.isAFK() && p.getNickname().equals(tempNickname)) {
                                    //Il player era disconnesso e si è riconnesso
                                    if( controller.getGame().isAllAFK() ){
                                                controller.getGame().setCurrentPlayer(p);
                                                controller.getGame().setAllAFK(false);
                                                controller.getGame().getCurrentPlayer().setCanEndTurn(false);
                                    }
                                    LoginOkNotificationMessage me = new LoginOkNotificationMessage();
                                    me.setSenderNickname(tempNickname);
                                    me.setMessageType("LoginOkNotification");
                                    out.println(gson.toJson(me));
                                    p.setIsAFK(false);
                                    mex = new Message();
                                    mex.setMessageType("ReconnectOkNotification");
                                    PlayerInNotification playerInNotification=new PlayerInNotification();
                                    playerInNotification.setMessageType("PlayerInNotification");
                                    playerInNotification.setSenderNickname(tempNickname);
                                    for(NotifiableHandler c: controller.getConnectedClients())
                                        c.notifyInterface(gson.toJson(playerInNotification));
                                    for(Player player: controller.getGame().getPlayers()){
                                        ReconnectConfigurationMessage reconnectConfigurationMessage= new ReconnectConfigurationMessage();
                                        reconnectConfigurationMessage.setMessageType("ReconnectConfigurationMessage");
                                        reconnectConfigurationMessage.setPlayerNumber(player.getPlayerNumber());
                                        reconnectConfigurationMessage.setSenderNickname(player.getNickname());
                                        reconnectConfigurationMessage.setMarbleGridConfiguration(controller.getGame().getMarketBoard().getMarketboardColours());
                                        reconnectConfigurationMessage.setMarbleOut(controller.getGame().getMarketBoard().getMarbleOut().getColour());
                                        reconnectConfigurationMessage.setDeckgridConfiguration(controller.getGame().getDeckgrid());
                                        reconnectConfigurationMessage.setWareHouseConfiguration(player.getPersonalBoard().getWarehouseDepot());
                                        reconnectConfigurationMessage.setStrongboxConfiguration(player.getPersonalBoard().getStrongbox());
                                        ArrayList<ExtraDeposit> extraDeposits = new ArrayList<>();
                                        extraDeposits.add(player.getPersonalBoard().getExtraDeposit1());
                                        extraDeposits.add(player.getPersonalBoard().getExtraDeposit2());
                                        reconnectConfigurationMessage.setExtraDepositConfiguration(extraDeposits);
                                        reconnectConfigurationMessage.setNewFaithPoints(player.getFaithPoints());
                                        reconnectConfigurationMessage.setDevelopmentCardsConfiguration(player.getPersonalBoard().getDevelopmentCard());
                                        reconnectConfigurationMessage.setHasChosenLeaderCards(player.hasChosenLeaderCards());
                                        boolean[] actives = {false,false};
                                        reconnectConfigurationMessage.setActiveLeaderCards(actives);
                                        if(controller.getGame().getCurrentPlayer()!=null)
                                            reconnectConfigurationMessage.setCurrentPlayerNickname(controller.getGame().getCurrentPlayer().getNickname());
                                        if(player.getNickname().equals(tempNickname)){
                                            if(player.hasChosenLeaderCards()){
                                                int k=0;
                                                for(LeaderCard l: player.getChoosedLeaderCards()){
                                                    actives[k]=l.isActive();
                                                    reconnectConfigurationMessage.getChoosedLeaderCards().add(l.getLeaderCardNumber());
                                                    k++;
                                                }
                                                reconnectConfigurationMessage.setActiveLeaderCards(actives);
                                            }
                                            else{
                                                for(LeaderCard l: player.getChoosableLeaderCards()){
                                                    reconnectConfigurationMessage.getChoosableLeaderCards().add(l.getLeaderCardNumber());
                                                }
                                            }
                                        }
                                        else if(player.hasChosenLeaderCards()){
                                            int k=0;
                                           for(LeaderCard l: player.getChoosedLeaderCards()){
                                                    actives[k]=l.isActive();
                                                   reconnectConfigurationMessage.getChoosedLeaderCards().add(l.getLeaderCardNumber());
                                                   k++;
                                           }
                                           reconnectConfigurationMessage.setActiveLeaderCards(actives);
                                        }
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
                        if(loginDone)
                            break;
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
            System.err.println("Un client si è disconnesso");
            this.serverPing.setRunning(false);
            in.close();
            out.close();
            socket.close();
        } catch(SocketTimeoutException e){
            System.err.println("Il client non risponde");
        } catch (IOException e) {
            System.err.println("IO chiamata dal client handler");
        }
        //GESTIONE DISCONNESSIONI
        if(clientNickname==null) {
            this.serverPing.setRunning(false);
            return;
        }
        synchronized (controller){
            Gson gson = new Gson();
            controller.getConnectedClients().remove(this);
            //DISCONNESSO MENTRE LA PARTITA NON E' INIZIATA
            if(controller.getGame().getCurrentPlayer()==null){
                for(Player p: controller.getGame().getPlayers()){
                    if(p.getNickname().equals(clientNickname))
                        p.setIsAFK(true);
                }
            }
            //DISCONNESSO DURANTE IL SUO TURNO
            else if(clientNickname.equals(controller.getGame().getCurrentPlayer().getNickname())){
                if(!controller.getGame().getCurrentPlayer().hasChosenLeaderCards()) {
                    controller.getGame().chooseLeaderCards(0, 1);
                    controller.getGame().getCurrentPlayer().setChosenLeaderCards(true);
                    ChosenLeaderCardsMessage mex = new ChosenLeaderCardsMessage();
                    mex.setSenderNickname(clientNickname);
                    mex.setMessageType("ChosenLeaderCardsMessage");
                    mex.setFirstChosenLeaderCardNumber(0);
                    mex.setSecondChosenLeaderCardNumber(1);
                    String notificationForAll = gson.toJson(mex);
                    for(NotifiableHandler c : controller.getConnectedClients()){
                        c.notifyInterface(notificationForAll);
                    }
                }
                if(controller.getGame().getCurrentPlayer().notDoneInitialDistribution()){
                    controller.getGame().distributionResourceSecondThird(ResourceType.STONES);
                    controller.getGame().distributionResourceFourthPlayer(ResourceType.STONES,ResourceType.STONES);
                    NotifyWarehouseChangedMessage mex = new NotifyWarehouseChangedMessage();
                    mex.setWarehouseConfiguration(controller.getGame().getCurrentPlayer().getPersonalBoard().getWarehouseDepot());
                    mex.setNickname(clientNickname);
                    mex.setMessageType("NotifyWareHouseChangedMessage");
                    for(NotifiableHandler c : controller.getConnectedClients()){
                        c.notifyInterface(gson.toJson(mex));
                    }
                }
                controller.getGame().getCurrentPlayer().setIsAFK(true);
                controller.getGame().getCurrentPlayer().setCanEndTurn(true);
                controller.getGame().endTurn();
                String nickname;
                String winnerNickname=null;
                int tempResourcesDiscarded=0;
                boolean endGame;
                for(ResourceType res: controller.getGame().getMarketBoard().getTemporaryResources().keySet()){
                    tempResourcesDiscarded+=controller.getGame().getMarketBoard().getTemporaryResources().get(res);
                }
                nickname=controller.getGame().getCurrentPlayer().getNickname();
                endGame=controller.getGame().isEndGame();
                if(controller.getGame().getWinnerPlayer()!=null)
                    winnerNickname=controller.getGame().getWinnerPlayer().getNickname();
                EndTurnNotificationMessage endTurnNotificationMessage= new EndTurnNotificationMessage();
                endTurnNotificationMessage.setMessageType("EndTurnNotificationMessage");
                endTurnNotificationMessage.setActualCurrentPlayer(nickname);
                endTurnNotificationMessage.setNumResourcesDiscarded(tempResourcesDiscarded);
                endTurnNotificationMessage.setWinnerPlayerNickname(winnerNickname);
                endTurnNotificationMessage.setGameEnding(endGame);
                for(NotifiableHandler c: controller.getConnectedClients())
                    c.notifyInterface(gson.toJson(endTurnNotificationMessage));
            }
            //DISCONNESSO MA NON ERA IL SUO TURNO
            else{
                for(Player p: controller.getGame().getPlayers()) {
                    if (p.getNickname().equals(clientNickname)) {
                        p.setIsAFK(true);
                        if (!p.hasChosenLeaderCards()) {
                            p.chooseLeaderCards(0, 1);
                            p.setChosenLeaderCards(true);
                            ChosenLeaderCardsMessage mex = new ChosenLeaderCardsMessage();
                            mex.setSenderNickname(clientNickname);
                            mex.setMessageType("ChosenLeaderCardsMessage");
                            mex.setFirstChosenLeaderCardNumber(0);
                            mex.setSecondChosenLeaderCardNumber(1);
                            String notificationForAll = gson.toJson(mex);
                            for(NotifiableHandler c : controller.getConnectedClients()){
                                c.notifyInterface(notificationForAll);
                            }
                        }
                        if (p.notDoneInitialDistribution()) {
                            if (p.getPlayerNumber() == 3)
                                p.insertResourcesIntoWarehouse(ResourceType.STONES, 2, 2);
                            else if (p.getPlayerNumber() == 1 || p.getPlayerNumber() == 2)
                                p.insertResourcesIntoWarehouse(ResourceType.STONES, 1, 1);
                            p.setInitialDistribution(true);
                            NotifyWarehouseChangedMessage mex = new NotifyWarehouseChangedMessage();
                            mex.setWarehouseConfiguration(p.getPersonalBoard().getWarehouseDepot());
                            mex.setNickname(clientNickname);
                            mex.setMessageType("NotifyWareHouseChangedMessage");
                            for(NotifiableHandler c : controller.getConnectedClients()){
                                c.notifyInterface(gson.toJson(mex));
                            }
                        }
                    }
                }
            }
            PlayerOutNotification mex = new PlayerOutNotification();
            mex.setMessageType("PlayerOutNotification");
            mex.setSenderNickname(clientNickname);
            for(NotifiableHandler c: controller.getConnectedClients()){
                c.notifyInterface(gson.toJson(mex));
            }
        }
        this.serverPing.setRunning(false);
    }


    public void notifyInterface(String notification) {
        out.println(notification);
        out.flush();
    }

    public String getClientNickname() {
        return clientNickname;
    }
}