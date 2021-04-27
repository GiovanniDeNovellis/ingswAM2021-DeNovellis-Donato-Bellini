package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ExtraDeposit;

public class ActivateLeaderAbilityDepositMessage extends Message{
    private int position;
    private ExtraDeposit[] extraDepositConfiguration;

    public int getPosition() {
        return position;
    }

    public ExtraDeposit[] getExtraDepositConfiguration() {
        return extraDepositConfiguration;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setExtraDepositConfiguration(ExtraDeposit[] extraDepositConfiguration) {
        this.extraDepositConfiguration = extraDepositConfiguration;
    }
}
