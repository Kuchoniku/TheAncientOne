package theancientonemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.cards.ChainWhip;

public class ChainWhipLockAction extends AbstractGameAction {
    public ChainWhipLockAction (int amount) {
        this.amount=amount;
    }
    @Override
    public void update() {
        for (CardQueueItem q: AbstractDungeon.actionManager.cardQueue) {
            AbstractCard c=q.card;
            if (c instanceof ChainWhip) {
                c.baseDamage+=amount;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof ChainWhip) {
                c.baseDamage+=amount;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ChainWhip) {
                c.baseDamage+=amount;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof ChainWhip) {
                c.baseDamage+=amount;
            }
        }
        this.isDone = true;
    }
}
