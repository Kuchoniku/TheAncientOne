package theancientonemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.spirePatches.LockAmountFieldPatch;

public class BrawlersPunchAction extends AbstractGameAction {
    private int damage;
    private AbstractPlayer p;
    public BrawlersPunchAction(AbstractCreature target, DamageInfo info, AbstractPlayer p, int damage) {
        this.setValues(target, info);
        this.actionType=ActionType.DAMAGE;
        this.damage=damage;
        this.p=p;
        this.target=target;
    }

    @Override
    public void update() {
        if (this.shouldCancelAction()) {
            this.isDone = true;
        } else {
            int counter = 0;
            for (AbstractCard c : p.hand.group) {
                counter+= LockAmountFieldPatch.lockAmount.get(c);
            }
            for (int i = 0; i<counter; i++) {
                addToBot(new DamageAction(target, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            }
        }
        this.isDone=true;
    }
}
