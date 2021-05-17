package it.polimi.ingsw.View;


import it.polimi.ingsw.View.MessageBuilders.*;
import it.polimi.ingsw.View.Printers.MarketBoardPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;

import java.util.Scanner;

public class CommandManager {
    private ModelPrinter modelPrinter;
    private String nickname;

    public CommandManager(ModelPrinter modelPrinter, String nickname) {
        this.modelPrinter = modelPrinter;
        this.nickname = nickname;
    }

    public String manage(String userInput){
        MessageBuilder toBuild;
        switch (userInput) {
            //TODO( AGGIUNGERE I COMANDI SHOW )
            //Help to show all commands
            case "help":
                toBuild = new HelpMessageBuilder();
                return toBuild.buildMessage();
//Logging in
            case "login":
                toBuild = new LoginMessageBuilder();
                return toBuild.buildMessage();
//Activating production
            case "ActivateProduction":
                toBuild = new ActivateProductionMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating leader card(s)
            case "ActivateLeaderCard":
                toBuild = new ActivateLeaderCardMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating leader ability
            case "ActivateLeaderAbility":
                toBuild = new ActivateLeaderAbilityMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating action card
            case "ActionCardActivation":
                toBuild = new ActivateActionCardMessageBuilder();
                return toBuild.buildMessage();
//Activating SwitchLevels
            case "SwitchLevels":
                toBuild = new SwitchLevelMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating TakeResourceFromMarket
            case "TakeResourcesFromMarket":
                toBuild = new TakeResourceFromMarketMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating InsertResourceIntoWarehouse
            case "InsertResourcesIntoWarehouse":
                toBuild = new InsertResourcesIntoWarehouseMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating BuyDevelopmentCard
            case "BuyDevelopmentCard":
                toBuild = new BuyDevelopmentCardMessageBuilder(nickname);
                return toBuild.buildMessage();
//Starting single player mode
            case "StartSinglePlayer":
                toBuild = new StartSinglePlayerMessageBuilder();
                return toBuild.buildMessage();
//Starting a multi player game
            case "StartMultiPlayer":
                toBuild = new StartMultiPlayerMessageBuilder();
                return toBuild.buildMessage();
//Activating an initial resource distribution for the second or the third player
            case "DistributionSecondThird":
                toBuild = new DistributionSecondThirdMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating an initial resource distribution for the fourth player
            case "DistributionFourth":
                toBuild = new DistributionFourthMessageBuilder(nickname);
                return toBuild.buildMessage();
//Selecting the leader cards from the 4 choosable cards
            case "LeaderCardSelection":
                toBuild = new LeaderCardSelectionMessageBuilder(nickname);
                return toBuild.buildMessage();
// Ending turn
            case "EndTurnRequest":
                toBuild = new EndTurnRequestMessageBuilder(nickname);
                return toBuild.buildMessage();
            //__________________________SHOW CASES________________________
            case "show warehouse":
                showWarehouse();
                break;
            case "show strongbox":
                showStrongbox();
                break;
            case "show development cards":
                showDevelopmentCards();
                break;
            case "show faith track":
                showFaithTrack();
                break;
            case "show extra deposits":
                showExtraDeposits();
                break;
            case "show choosable leader cards":
                showChoosableLeaderCards();
                break;
            case "show chosen leader cards":
                showChosenLeaderCards();
                break;
            case "show active leader cards":
                showActiveLeaderCards();
                break;
            case "show deck grid":
                showDeckGrid();
                break;
            case "show market board":
                showMarketBoard();
                break;
            case "show players":
                showPlayers();
                break;
            case "show all inserted development cards":
                showInsertedDevCards();
                break;
        }
        return "Invalid command.";
    }

    public void showWarehouse(){
        System.out.println("Which one do you want to see? Write the warehouse owner's nickname," +
                " or \"all\" to see all the warehouses.");
        Scanner input = new Scanner(System.in);
        String nickname = input.nextLine();
        PersonalBoardPrinter printable = null;
        if (!nickname.equals("all")) {
            for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
                if (p.getOwnerNickname().equals(nickname))
                    printable = p;
            }
            if( printable!=null )
                modelPrinter.print(printable, "warehouse");
            else
                System.out.println("Write again the show command followed by a correct nickname.");
        } else {
            for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
                modelPrinter.print(p, "warehouse");
            }
        }
    }

    public void showStrongbox(){ }
    public void showDevelopmentCards(){ }
    public void showFaithTrack(){ }//in single player farà vedere quello di Lorenzo
    public void showExtraDeposits(){ }
    public void showChoosableLeaderCards(){ }
    public void showChosenLeaderCards(){ }
    public void showActiveLeaderCards(){ }
    public void showDeckGrid(){ }

    public void showMarketBoard(){
        MarketBoardPrinter printable = modelPrinter.getMarketBoardPrinter();
        modelPrinter.print(printable, "marketboard");
    }

    public void showPlayers(){
        for( PersonalBoardPrinter p: modelPrinter.getPersonalBoards() ){ //turn position
            System.out.println(p.getOwnerNickname() + "\tPlayer number: " + (p.getPlayerNumber()+1) + "\n");
        }
    }

    public void showInsertedDevCards(){
        System.out.println("Whose inserted development cards do you want to see? Write the player's nickname," +
                " or \"all\" to see inserted development cards from all the players.");
        Scanner input = new Scanner(System.in);
        String nickname = input.nextLine();
        PersonalBoardPrinter printable = null;
        if (!nickname.equals("all")) {
            for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
                if (p.getOwnerNickname().equals(nickname))
                    printable = p;
            }
            if( printable!=null )
                modelPrinter.print(printable, "insertedDevCards");
            else
                System.out.println("Write again the show command followed by a correct nickname.");
        } else {
            for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
                modelPrinter.print(p, "insertedDevCards");
            }
        }
    }

}
