package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theancientonemod.spirePatches.AncientOneTags;

import static theancientonemod.ancientOneMod.makeID;

public class SanguinePower extends BasePower {
    public static final String POWER_ID = makeID(SanguinePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public SanguinePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL && target.hasPower(BleedPower.POWER_ID)) {
            this.flash();
            this.addToTop(new GainBlockAction(owner, owner, amount, true));
        }

    }


    public void updateDescription() {this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];}
}
