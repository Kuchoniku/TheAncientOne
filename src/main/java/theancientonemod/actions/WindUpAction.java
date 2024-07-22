package theancientonemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class WindUpAction extends AbstractGameAction {
    private AbstractMonster m;
    public WindUpAction(int vulnerable, AbstractMonster m) {
        this.actionType = ActionType.WAIT;
        this.amount = vulnerable;
        this.m = m;
    }
    @Override
    public void update() {
        if (this.m != null && this.m.getIntentBaseDmg() >= 0) {
            this.addToTop(new ApplyPowerAction(this.m, AbstractDungeon.player, new VulnerablePower(this.m, this.amount, false), this.amount));
        }
        this.isDone = true;
    }
}
