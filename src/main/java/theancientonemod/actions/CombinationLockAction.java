package theancientonemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.spirePatches.LockAmountFieldPatch;

public class CombinationLockAction extends AbstractGameAction {
    private int amount;
    public CombinationLockAction(int amount) {
        this.amount=amount;
    }
    @Override
    public void update() {
        CardGroup l = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        CardGroup u = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (LockAmountFieldPatch.lockAmount.get(c)==0) {
                u.addToBottom(c);
            } else {
                l.addToBottom(c);
            }
        }
        if (l.size()>u.size()) {
            if (!l.isEmpty()){
                AbstractCard c =l.getRandomCard(AbstractDungeon.cardRandomRng);
                LockAmountFieldPatch.changeLockAmount(c, amount);
            }
        } else {
            if (!u.isEmpty()) {
                AbstractCard c = u.getRandomCard(AbstractDungeon.cardRandomRng);
                LockAmountFieldPatch.changeLockAmount(c, amount);
            }
        }
        this.isDone = true;
    }
}
