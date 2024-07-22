package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import theancientonemod.actions.TurnStartLockAction;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.spirePatches.AncientOneTags;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static theancientonemod.ancientOneMod.makeID;

public class UnlockStartOfTurnPower extends BasePower{
    public static final String POWER_ID = makeID(UnlockStartOfTurnPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public UnlockStartOfTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new TurnStartLockAction(amount, false, true, null));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
}
