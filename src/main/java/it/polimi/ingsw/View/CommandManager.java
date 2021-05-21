package it.polimi.ingsw.View;


import it.polimi.ingsw.View.MessageBuilders.*;
import it.polimi.ingsw.View.Printers.DeckGridPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
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
            //Help to show all commands
            case "test":

                return "show";
            case "help":
                toBuild = new HelpMessageBuilder();
                return toBuild.buildMessage();
//Logging in
            case "login":
                toBuild = new LoginMessageBuilder();
                return toBuild.buildMessage();
//Activating production
            case "activate prod":
                showDevelopmentCards();
                showMarketBoard();
                toBuild = new ActivateProductionMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating leader card(s)
            case "activate leader card":
                toBuild = new ActivateLeaderCardMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating leader ability
            case "activate leader ability":
                toBuild = new ActivateLeaderAbilityMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating action card
            case "take action token":
                toBuild = new ActivateActionCardMessageBuilder();
                return toBuild.buildMessage();
//Activating SwitchLevels
            case "switch levels":
                toBuild = new SwitchLevelMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating TakeResourceFromMarket
            case "take res from market":
                showMarketBoard();
                toBuild = new TakeResourceFromMarketMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating InsertResourceIntoWarehouse
            case "insert res":
                toBuild = new InsertResourcesIntoWarehouseMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating BuyDevelopmentCard
            case "buy dev card":
                showDeckGrid();
                showDevelopmentCards();
                toBuild = new BuyDevelopmentCardMessageBuilder(nickname);
                return toBuild.buildMessage();
//Starting single player mode
            case "start single player":
                toBuild = new StartSinglePlayerMessageBuilder();
                return toBuild.buildMessage();
//Starting a multi player game
            case "start multi player":
                toBuild = new StartMultiPlayerMessageBuilder();
                return toBuild.buildMessage();
//Activating an initial resource distribution for the second or the third player
            case "initial res second player":
            case "initial res third player":
                toBuild = new DistributionSecondThirdMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating an initial resource distribution for the fourth player
            case "initial res fourth player":
                toBuild = new DistributionFourthMessageBuilder(nickname);
                return toBuild.buildMessage();
//Selecting the leader cards from the 4 choosable cards
            case "select leader cards":
                showChoosableLeaderCards();
                toBuild = new LeaderCardSelectionMessageBuilder(nickname);
                return toBuild.buildMessage();
// Ending turn
            case "end turn":
                toBuild = new EndTurnRequestMessageBuilder(nickname);
                return toBuild.buildMessage();
                //TODO FORSE FARE SHOW CURRENT PLAYER E SHOW PERSONAL BOARD e TEMPORARY RESOURCES
            //__________________________SHOW CASES________________________
            case "show warehouse":
                showWarehouse();
                return "show";
            case "show strongbox":
                showStrongbox();
                return "show";
            case "show development cards":
                showDevelopmentCards();
                return "show";
            case "show faith track":
                showFaithTrack();
                return "show";
            case "show extra deposits":
                showExtraDeposits();
                return "show";
            case "show choosable leader cards":
                showChoosableLeaderCards();
                return "show";
            case "show chosen leader cards":
                showChosenLeaderCards();
                return "show";
            case "show active leader cards":
                showActiveLeaderCards();
                return "show";
            case "show deck grid":
                showDeckGrid();
                return "show";
            case "show temporary resources":
                showTemporaryResources();
                return "show";
            case "show market board":
                showMarketBoard();
                return "show";
            case "show players":
                showPlayers();
                return "show";
            case "show all inserted development cards":
                showInsertedDevCards();
                return "show";
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

    public void showChoosableLeaderCards(){
        LeaderCardsPrinter printable = null;
        for (LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters()) {
            if (l.getOwnerNickname().equals(nickname))
                printable = l;
        }
        if (printable == null)
            System.out.println("Nickname bug.");
        else
            modelPrinter.print(printable, "choosableLeaderCards");

    }

    public void showChosenLeaderCards() {
        LeaderCardsPrinter printable = null;
        for (LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters()) {
            if (l.getOwnerNickname().equals(nickname))
                printable = l;
        }
            if (printable == null)
                System.out.println("Nickname bug.");
            else
                modelPrinter.print(printable, "choosenLeaderCards");

    }

    public void showActiveLeaderCards(){
        System.out.println("Whose active leader card do you want to see? Write the player's nickname," +
                " or \"all\" to see active leader card from all the players.");
        Scanner input = new Scanner(System.in);
        String nickname = input.nextLine();
        LeaderCardsPrinter printable = null;
        if (!nickname.equals("all")) {
            for (LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters()) {
                if (l.getOwnerNickname().equals(nickname))
                    printable = l;
            }
            if( printable!=null )
                modelPrinter.print(printable, "activeLeaderCards");
            else
                System.out.println("Write again the show command followed by a correct nickname.");
        } else {
            for (LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters()) {
                modelPrinter.print(l, "activeLeaderCards");
            }
        }
    }

    public void showDeckGrid(){
        DeckGridPrinter printable = modelPrinter.getDeckGridPrinter();
        modelPrinter.print(printable,"deckgrid");
    }

    public void showStrongbox(){
        System.out.println("Which one do you want to see? Write the strongbox owner's nickname," +
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
                modelPrinter.print(printable, "strongbox");
            else
                System.out.println("Write again the show command followed by a correct nickname.");
        } else {
            for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
                modelPrinter.print(p, "strongbox");
            }
        }
    }

    public void showDevelopmentCards(){
        System.out.println("Whose development cards do you want to see? Write the player's nickname," +
            " or \"all\" to see all player's development cards.");
        Scanner input = new Scanner(System.in);
        String nickname = input.nextLine();
        PersonalBoardPrinter printable = null;
        if (!nickname.equals("all")) {
            for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
                if (p.getOwnerNickname().equals(nickname))
                    printable = p;
            }
            if( printable!=null )
                modelPrinter.print(printable, "developmentCards");
            else
                System.out.println("Write again the show command followed by a correct nickname.");
        } else {
            for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
                modelPrinter.print(p, "developmentCards");
            }
        }
    }

    public void showFaithTrack(){
        System.out.println("Whose faith track do you want to see? Write the player's nickname," +
                " or \"all\" to see all player's faith tracks or \"blackFaithPoints\" to show Lorenzo's faith track if you are " +
                "playing a single player game.");
        Scanner input = new Scanner(System.in);
        String nickname = input.nextLine();
        PersonalBoardPrinter printable = null;
        if(nickname.equals("blackFaithPoints")){
            if(modelPrinter.getBlackFaithPoints()==-1)
                System.out.println("You are not playing a single player game!");
            else
                modelPrinter.print(null,"blackFaithPoints");
        }
        else if (!nickname.equals("all")) {
            for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
                if (p.getOwnerNickname().equals(nickname))
                    printable = p;
            }
            if( printable!=null )
                modelPrinter.print(printable, "faithTrack");
            else
                System.out.println("Write again the show command followed by a correct nickname.");
        } else {
            for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
                modelPrinter.print(p, "faithTrack");
            }
            System.out.println("Done printing the faith tracks");
        }
    }

    public void showExtraDeposits(){
        System.out.println("Whose extra deposit do you want to see? Write the player's nickname," +
                " or \"all\" to see all player's extra deposits.");
        Scanner input = new Scanner(System.in);
        String nickname = input.nextLine();
        PersonalBoardPrinter printable = null;
        if (!nickname.equals("all")) {
            for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
                if (p.getOwnerNickname().equals(nickname))
                    printable = p;
            }
            if( printable!=null )
                modelPrinter.print(printable, "extraDeposit");
            else
                System.out.println("Write again the show command followed by a correct nickname.");
        } else {
            for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
                modelPrinter.print(p, "extraDeposit");
            }
        }
    }

    public void showTemporaryResources(){
        MarketBoardPrinter printable = modelPrinter.getMarketBoardPrinter();
        modelPrinter.print(printable, "temporaryResources");
    }

    public void showMarketBoard(){
        MarketBoardPrinter printable = modelPrinter.getMarketBoardPrinter();
        modelPrinter.print(printable, "marketboard");
    }

    public void showPlayers(){
        for( PersonalBoardPrinter p: modelPrinter.getPersonalBoards() ){
            System.out.println("Player name: " + Colours.ANSI_RED.escape() + p.getOwnerNickname()
                    + Colours.RESET + "\tPlayer number: " +  Colours.ANSI_RED.escape() +(p.getPlayerNumber()) + Colours.RESET +"\n");
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
