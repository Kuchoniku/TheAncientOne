package theancientonemod.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;

public class KeybladeAction extends AbstractGameAction {
    private DamageInfo info;
    private int amount;
    private AbstractPlayer p;
    public KeybladeAction(AbstractCreature target,DamageInfo info, AbstractPlayer p, int amount) {
        this.info=info;
        this.setValues(target, info);
        this.actionType=ActionType.DAMAGE;
        this.amount=amount;
        this.p=p;
    }

    @Override
    public void update() {
        if (this.shouldCancelAction()) {
            this.isDone = true;
        } else {
            this.tickDuration();
            if (this.isDone) {
                AbstractDungeon.effectList.add(new ViolentAttackEffect(target.hb.cX, target.hb.cY, new Color(250, 250, 250, 250)));
                this.target.damage(this.info);
                if (this.target.lastDamageTaken > 0) {
                    this.addToTop((new UnlockHandAction(p, amount)));
                }
                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                } else {
                    this.addToTop(new WaitAction(0.1F));
                }
            }
        }
    }
}
