package it.polimi.ingsw.View.MessageBuilders;

/**
 * Used to check all available commands
 */
public class HelpMessageBuilder extends MessageBuilder{
    @Override
    public String buildMessage() {
        System.out.print("List of possible commands: \n" +
                "help - To see all commands \n" +
                "login - To log in the game \n" +
                "activate prod - To activate a production \n" +
                "activate leader card - To activate a leader card \n" +
                "discard leader card - To discard a leader card \n" +
                "activate leader ability - To activate a leader ability \n" +
                "take action token - To activate an action card in a single player game \n" +
                "switch levels - To switch two levels of the warehouse \n" +
                "take res from market - To take resources from the market \n" +
                "insert res - To insert resources taken from the market \n" +
                "buy dev card - To buy a development card \n" +
                "start single player - To start a single player game \n" +
                "start multi player - To start a multi player game \n" +
                "initial res second player - To get a free resource if you are the second or the third player \n" +
                "initial res third player - To get a free resource if you are the second or the third player \n" +
                "initial res fourth player - To get two free resources if you are the fourth player \n" +
                "select leader cards - To choose your two leader cards \n" +
                "end turn - To end the turn \n" +
                "show warehouse - To see a player's warehouse \n" +
                "show strongbox - To see a player's strongbox \n" +
                "show development cards - To see a player's active development cards \n" +
                "show faith track - To see a player's faith track \n" +
                "show extra deposits - To see a player's extra deposits \n" +
                "show choosable leader cards - To see your choosable leader cards \n" +
                "show chosen leader cards - To see your chosen leader cards \n" +
                "show active leader cards - To see a player's active leader cards \n" +
                "show deck grid - To see the deck grid \n" +
                "show market board - To see the market board \n" +
                "show temporary resources - To see the temporary resources of the market \n" +
                "show players - To see the connected players \n" +
                "show all inserted development cards - To see all inserted development cards of a player\n" +
                "");
        return "show";
    }
}
