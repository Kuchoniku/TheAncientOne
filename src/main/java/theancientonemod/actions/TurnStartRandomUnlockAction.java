package theancientonemod.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.spirePatches.LockAmountFieldPatch;

public class TurnStartRandomUnlockAction extends AbstractGameAction {
    private int amount;
    public TurnStartRandomUnlockAction(int amount) {
        this.amount=amount;
    }
    @Override
    public void update() {
        CardGroup l = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (LockAmountFieldPatch.lockAmount.get(c)>0) {
                l.addToBottom(c);
            }
        }
        if (l.isEmpty()) {
            this.isDone = true;
            return;
        } else {
            LockAmountFieldPatch.changeLockAmount(l.getRandomCard(AbstractDungeon.cardRandomRng), -amount);
        }
        this.isDone = true;
    }
}
