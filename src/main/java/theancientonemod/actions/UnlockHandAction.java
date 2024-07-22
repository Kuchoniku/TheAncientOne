package theancientonemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import theancientonemod.spirePatches.LockAmountFieldPatch;

import java.util.Iterator;

public class UnlockHandAction extends AbstractGameAction {
    private int amount;
    private AbstractPlayer p;
    public UnlockHandAction(AbstractPlayer p, int amount) {
        this.amount=amount;
        this.p=p;
    }
    @Override
    public void update() {
        Iterator var1=this.p.hand.group.iterator();
        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (LockAmountFieldPatch.lockAmount.get(c)>0) {
                LockAmountFieldPatch.changeLockAmount(c, -1*amount);
                c.superFlash();
            }
        }
        this.isDone=true;
    }
}
