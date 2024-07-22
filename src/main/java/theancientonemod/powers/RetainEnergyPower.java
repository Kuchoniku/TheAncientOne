package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static theancientonemod.ancientOneMod.makeID;

public class RetainEnergyPower extends BasePower {
    public static final String POWER_ID = makeID(RetainEnergyPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int energyRetained;

    public RetainEnergyPower (AbstractCreature owner, int amount) {
        super (POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    public void atEndOfTurn (boolean isPlayerTurn) {
        energyRetained= EnergyPanel.totalCount;
        if (energyRetained>amount) {
            energyRetained=amount;
        }
    }
    public void onEnergyRecharge () {
        AbstractDungeon.player.gainEnergy(energyRetained);
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
}
