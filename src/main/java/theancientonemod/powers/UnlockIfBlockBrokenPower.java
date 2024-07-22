package theancientonemod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnMyBlockBrokenPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theancientonemod.actions.TurnStartLockAction;

import static theancientonemod.ancientOneMod.makeID;

public class UnlockIfBlockBrokenPower extends BasePower implements OnMyBlockBrokenPower {
    public static final String POWER_ID = makeID(UnlockIfBlockBrokenPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private boolean broken;
    public UnlockIfBlockBrokenPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        broken = false;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (broken) {
            flash();
            addToBot(new TurnStartLockAction(amount, false, true, null));
        }
        addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }

    @Override
    public void onMyBlockBroken() {
        flash();
        broken=true;
    }
}
