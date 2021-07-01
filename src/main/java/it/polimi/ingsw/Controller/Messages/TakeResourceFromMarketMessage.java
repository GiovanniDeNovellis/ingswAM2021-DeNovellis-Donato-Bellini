package it.polimi.ingsw.Controller.Messages;

/**
 * Message used to request to take resources
 */
    public class TakeResourceFromMarketMessage extends Message {


        public void setMarketIndex(int[] marketIndex) {
            this.marketIndex = marketIndex;
        }

        private int[] marketIndex;

        public String getSenderNickname() {
            return senderNickname;
        }

        public void setSenderNickname(String senderNickname) {
            this.senderNickname = senderNickname;
        }

        private String senderNickname;

        public int[] getMarketIndex() {
            return marketIndex;
        }
    }
