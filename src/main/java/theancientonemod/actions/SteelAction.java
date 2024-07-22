package theancientonemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theancientonemod.spirePatches.LockAmountFieldPatch;

public class SteelAction extends AbstractGameAction {
    private AbstractCard c;
    public SteelAction(AbstractCard c, int amount) {
        this.c=c;
        this.amount=amount;
    }
    @Override
    public void update() {
        LockAmountFieldPatch.changeLockAmount(c, amount);
        if (c.canUpgrade() && c.type != AbstractCard.CardType.STATUS) {
            c.upgrade();
        }
        c.modifyCostForCombat(-amount);
        c.superFlash();
        c.applyPowers();
        this.isDone = true;
    }
}
