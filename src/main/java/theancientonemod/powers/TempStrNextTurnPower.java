package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theancientonemod.ancientOneMod.makeID;

public class TempStrNextTurnPower extends BasePower {
    public static final String POWER_ID = makeID(TempStrNextTurnPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public TempStrNextTurnPower(AbstractCreature owner, int amount) {
        super (POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount)));
        addToBot(new ApplyPowerAction(owner, owner, new LoseStrengthPower(owner, amount)));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
}
