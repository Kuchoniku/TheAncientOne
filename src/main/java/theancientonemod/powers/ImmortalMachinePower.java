package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static theancientonemod.ancientOneMod.makeID;

public class ImmortalMachinePower extends BasePower {
    public static final String POWER_ID = makeID(ImmortalMachinePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ImmortalMachinePower(AbstractCreature owner, int amount) {
        super (POWER_ID, TYPE, TURN_BASED, owner, amount);
        amount2=1;
        updateDescription();
    }
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        amount2++;
        updateDescription();
    }
    @Override
    public void atStartOfTurn() {
        addToBot(new ApplyPowerAction(owner, owner, new BleedPower(owner, amount2)));
    }
    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if(info.type== DamageInfo.DamageType.NORMAL&&damageAmount>0) {
            this.flash();
            addToBot(new ApplyPowerAction(owner, owner, new BleedPower(owner, amount)));
            return 0;
        }
        return damageAmount;
    }

    @Override
    public float modifyBlockLast (float blockAmount) {
        return 0;
    }
    @Override
    public int newOnGainedBlock(float blockAmount) {
        return 0;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount2+DESCRIPTIONS[1]+amount+DESCRIPTIONS[2];
    }
}
