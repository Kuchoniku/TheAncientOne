package theancientonemod.powers;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theancientonemod.spirePatches.LockAmountFieldPatch;

import static theancientonemod.ancientOneMod.makeID;

public class TetherPower extends BasePower{
    public static final String POWER_ID = makeID(TetherPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    public TetherPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer&&!AbstractDungeon.player.hand.isEmpty()) {
            addToBot(new SelectCardsInHandAction(amount, DESCRIPTIONS[2], true, true,
                    (c)->LockAmountFieldPatch.lockAmount.get(c)>0,
                    (c)-> {
                for (AbstractCard card : c) {
                    card.retain = true;
                }
                    }
            ));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
}
