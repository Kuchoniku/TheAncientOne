package theancientonemod.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.spirePatches.LockAmountFieldPatch;

public class TurnStartLockAction extends AbstractGameAction {
    private int amount;
    private boolean choose;
    private boolean unlock;
    private String d;
    public TurnStartLockAction(int amount, boolean choose, boolean unlock, String d) {
        this.amount=amount;
        this.choose=choose;
        this.unlock=unlock;
        this.d=d;
    }
    @Override
    public void update() {
        if(unlock) {
            addToBot(new UnlockHandAction(AbstractDungeon.player, amount));
            this.isDone=true;
        } else if (choose) {
            addToBot(new SelectCardsInHandAction(amount, d, true, true, (c) -> true, (c) -> {
                for (AbstractCard card : c) {
                   LockAmountFieldPatch.changeLockAmount(card, amount);
                }
            }));
            this.isDone=true;
        }
        else {
            if (AbstractDungeon.player.hand.isEmpty()) {
                this.isDone = true;
                return;
            }
            AbstractCard c = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
            addToBot(new LockCardAction(c, amount));
            this.isDone=true;
        }
    }
}
