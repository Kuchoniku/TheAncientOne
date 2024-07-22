package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.actions.LockCardAction;
import theancientonemod.actions.TurnStartLockAction;
import theancientonemod.actions.UnlockHandAction;

import static theancientonemod.ancientOneMod.makeID;

public class RandomLockStartOfTurnPower extends BasePower{
    public static final String POWER_ID = makeID(RandomLockStartOfTurnPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public RandomLockStartOfTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new TurnStartLockAction(amount, false, false, null));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
}
