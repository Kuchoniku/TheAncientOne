package theancientonemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theancientonemod.spirePatches.LockAmountFieldPatch;

public class LockCardAction extends AbstractGameAction {
    private AbstractCard c;
    private int amount;
    public LockCardAction (AbstractCard c, int amount) {
        this.c=c;
        this.amount=amount;
    }
    @Override
    public void update() {
        LockAmountFieldPatch.changeLockAmount(c, amount);
        this.isDone = true;
    }
}
