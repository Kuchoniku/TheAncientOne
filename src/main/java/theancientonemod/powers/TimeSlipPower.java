package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import theancientonemod.actions.TurnShiftAction;

import static theancientonemod.ancientOneMod.makeID;

public class TimeSlipPower extends BasePower{
    public static final String POWER_ID = makeID(TimeSlipPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public TimeSlipPower(AbstractCreature owner, int amount) {
        super (POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void startOfPlayerTurn() {
        this.flash();
        this.addToBot(new SFXAction("ATTACK_WHIRLWIND"));
        this.addToBot(new VFXAction(new WhirlwindEffect(), 0.0F));
        addToBot(new TurnShiftAction(amount, true));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
}
