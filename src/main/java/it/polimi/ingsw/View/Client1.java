package it.polimi.ingsw.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class Client1 {

    public static void main(String[] args) {
            String hostName = "127.0.0.1";
            int portNumber = 1234;
            try (
                    Socket echoSocket = new Socket(hostName, portNumber);
                    PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
            ) {
                echoSocket.setSoTimeout(15000);
                String userInput;
                System.out.println(in.readLine());
                ClientPong clientPong=new ClientPong(out);
                Thread t1 = new Thread(clientPong);
                t1.start();
                ServerReader serverReader = new ServerReader(in);
                Thread t = new Thread(serverReader);
                t.start();
                while (true) {
                    if((userInput = stdIn.readLine()) != null) {
                        out.println(userInput);
                    }
                }
            } catch (UnknownHostException e) {
                System.err.println("Don't know about host " + hostName);
                System.exit(1);
            } catch(SocketTimeoutException e){
                System.out.println("Il server non manda più ping");
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to " +
                        hostName);
                System.exit(1);
            }
    }
}

