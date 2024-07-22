package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theancientonemod.ancientOneMod.makeID;

public class NextTurnRetainEnergyPower extends BasePower{
    public static final String POWER_ID = makeID(NextTurnRetainEnergyPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public NextTurnRetainEnergyPower (AbstractCreature owner, int amount) {
        super (POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    public void onEnergyRecharge() {
        addToBot(new ApplyPowerAction(owner, owner, new RetainEnergyPower(owner, amount)));
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
}
