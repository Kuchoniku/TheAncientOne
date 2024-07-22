package theancientonemod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theancientonemod.actions.TurnStartLockAction;
import theancientonemod.actions.TurnStartRandomUnlockAction;

import static theancientonemod.ancientOneMod.makeID;

public class ThievesToolsPower extends BasePower{
    public static final String POWER_ID = makeID(ThievesToolsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public ThievesToolsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new TurnStartRandomUnlockAction(amount));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
}
