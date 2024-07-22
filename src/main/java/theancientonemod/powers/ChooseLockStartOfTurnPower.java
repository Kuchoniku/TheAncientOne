package theancientonemod.powers;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.actions.LockCardAction;
import theancientonemod.actions.TurnStartLockAction;

import java.util.Collections;

import static theancientonemod.ancientOneMod.makeID;

public class ChooseLockStartOfTurnPower extends BasePower{
    public static final String POWER_ID = makeID(ChooseLockStartOfTurnPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public ChooseLockStartOfTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new TurnStartLockAction(amount, true, false, DESCRIPTIONS[3]));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1]+amount+DESCRIPTIONS[2];
    }
}
