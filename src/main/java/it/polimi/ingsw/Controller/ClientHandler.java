package it.polimi.ingsw.Controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.AddPlayerMessage;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.Messages.SimpleMessage;
import it.polimi.ingsw.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
            // Leggo e scrivo nella connessione finche' non ricevo "quit"
            Message mex = new Message();
            mex.setMessageType("ConnectionAcceptedPleaseLoginNotification");
            String s = gson.toJson(mex);
            out.println(s);
            out.flush();
            String line;
            //LOGIN PHASE
            while(true){
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
                        break;
                    }
                    else{
                        synchronized (controller.getGame()) {
                            for (Player p : controller.getGame().getPlayers()) {
                                if (p.isAFK() && p.getNickname().equals(tempNickname)) {
                                    p.setIsAFK(false);
                                    mex = new Message();
                                    mex.setMessageType("LoginOkNotification");
                                    s = gson.toJson(mex);
                                    out.println(s);
                                    out.flush();
                                    clientNickname = tempNickname;
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
            controller.addClientHandler(this);
            while ((line = in.readLine()) != null) {
                if (line.equals("quit") ) {
                    break;
                } else {
                    out.println(controller.startAction(line));
                    out.flush();
                }
            }
            // Chiudo lo stream e i socket
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            //TODO METTI AFK IL PLAYER E FAGLI SALTARE IL TURNO SE ERA IL SUO TURNO
            System.err.println(e.getMessage());
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