package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ActivateProductionMessage;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.ResourceType;

import java.util.Scanner;

public class ActivateProductionMessageBuilder extends MessageBuilder{
    private String nickname;

    public ActivateProductionMessageBuilder(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String buildMessage() {
        Scanner input = new Scanner(System.in);
        Gson gson = new Gson();
        ActivateProductionMessage message = new ActivateProductionMessage();
        message.setMessageType("ActivateProduction");
        message.setSenderNickname(nickname);
        String resp;
        boolean[] whichDevCardSlot = {false,false,false};
        do {
            System.out.println("Do you want to activate the first slot card? (Y/N)");
            resp=input.nextLine();
        }while(!resp.equals("Y")&&!resp.equals("N"));
        if(resp.equals("Y"))
            whichDevCardSlot[0]=true;
        do {
            System.out.println("Do you want to activate the second slot card? (Y/N)");
            resp=input.nextLine();
        }while(!resp.equals("Y")&&!resp.equals("N"));
        if(resp.equals("Y"))
            whichDevCardSlot[1]=true;
        do {
            System.out.println("Do you want to activate the third slot card? (Y/N)");
            resp=input.nextLine();
        }while(!resp.equals("Y")&&!resp.equals("N"));
        if(resp.equals("Y"))
            whichDevCardSlot[2]=true;
        message.setWhichDevCardSlot(whichDevCardSlot);
        do {
            System.out.println("Do you want to activate the personal board production? (Y/N)");
            resp=input.nextLine();
        }while(!resp.equals("Y")&&!resp.equals("N"));
        ResourceType[] resourceBaseProduction = {null,null,null};
        if(resp.equals("Y")){
            message.setFromPersonalBoard(true);
            do{
                System.out.println("Please write the first resource to convert. (SHIELDS/COINS/SERVANTS/STONES)");
                resp=input.nextLine();
            }while(!resp.equals("SHIELDS")&&!resp.equals("COINS")&&!resp.equals("SERVANTS")&&!resp.equals("STONES"));
            resourceBaseProduction[0]=ResourceType.valueOf(resp);
            do{
                System.out.println("Please write the second resource to convert. (SHIELDS/COINS/SERVANTS/STONES)");
                resp=input.nextLine();
            }while(!resp.equals("SHIELDS")&&!resp.equals("COINS")&&!resp.equals("SERVANTS")&&!resp.equals("STONES"));
            resourceBaseProduction[1]=ResourceType.valueOf(resp);
            do{
                System.out.println("Please write the resource to obtain. (SHIELDS/COINS/SERVANTS/STONES)");
                resp=input.nextLine();
            }while(!resp.equals("SHIELDS")&&!resp.equals("COINS")&&!resp.equals("SERVANTS")&&!resp.equals("STONES"));
            resourceBaseProduction[2]=ResourceType.valueOf(resp);
            message.setResourceBaseProduction(resourceBaseProduction);
        }
        else{
            message.setFromPersonalBoard(false);
            message.setResourceBaseProduction(resourceBaseProduction);
        }
        boolean[] whichLeaderCard = {false,false};
        do {
            System.out.println("Do you want to activate the production from the first leader card? (You may not have a leader card for the production)" +
                    "(Y/N)");
            resp=input.nextLine();
        }while(!resp.equals("Y")&&!resp.equals("N"));
        if(resp.equals("Y"))
            whichLeaderCard[0]=true;
        do {
            System.out.println("Do you want to activate the production from the second leader card? (You may not have a leader card for the production)" +
                    "(Y/N)");
            resp=input.nextLine();
        }while(!resp.equals("Y")&&!resp.equals("N"));
        if(resp.equals("Y"))
            whichLeaderCard[1]=true;
        message.setWhichLeaderCard(whichLeaderCard);
        ResourceType[] resourceFromLeader = {null,null};
        if(whichLeaderCard[0]){
            do{
                System.out.println("Please write the resource to obtain from the first leader card. (SHIELDS/COINS/SERVANTS/STONES)");
                resp=input.nextLine();
            }while(!resp.equals("SHIELDS")&&!resp.equals("COINS")&&!resp.equals("SERVANTS")&&!resp.equals("STONES"));
            resourceFromLeader[0]=ResourceType.valueOf(resp);
        }
        if(whichLeaderCard[1]){
            do{
                System.out.println("Please write the resource to obtain from the first leader card. (SHIELDS/COINS/SERVANTS/STONES)");
                resp=input.nextLine();
            }while(!resp.equals("SHIELDS")&&!resp.equals("COINS")&&!resp.equals("SERVANTS")&&!resp.equals("STONES"));
            resourceFromLeader[1]=ResourceType.valueOf(resp);
        }
        message.setResourceFromLeader(resourceFromLeader);
        int[] payUsingExtraDeposit = {0,0};
        System.out.println("Do you want to pay any resource from the first extra deposit? (0 if you don't have it)");
        payUsingExtraDeposit[0]=input.nextInt();
        System.out.println("Do you want to pay any resource from the first extra deposit? (0 if you don't have it)");
        payUsingExtraDeposit[1]=input.nextInt();
        message.setPayUsingExtraDeposit(payUsingExtraDeposit);
        return gson.toJson(message);
    }
}
