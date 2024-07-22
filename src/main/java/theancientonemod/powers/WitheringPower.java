package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import static theancientonemod.ancientOneMod.makeID;

public class WitheringPower extends BasePower{
    public static final String POWER_ID = makeID(WitheringPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public WitheringPower(AbstractCreature owner, int amount) {
        super (POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void startOfPlayerTurn() {
        this.flash();
        AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.getRandomMonster();
        addToBot(new ApplyPowerAction(mo, owner, new BleedPower(mo, this.amount)));
    }

    @Override
    public void turnShifted(int amount, boolean timeSlip) {
        this.flash();
       for(int i=0;i<Math.abs(amount);i++) {
           AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.getRandomMonster();
           addToBot(new ApplyPowerAction(mo, owner, new BleedPower(mo, this.amount)));
       }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
}
