package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

import static theancientonemod.ancientOneMod.makeID;

public class DexOnUnlockPower extends BasePower {
    public static final String POWER_ID = makeID(DexOnUnlockPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public DexOnUnlockPower(AbstractCreature owner, int amount) {
        super (POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    @Override
    public void onCardUnlocked() {
        addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, amount)));
        addToBot(new ApplyPowerAction(owner, owner, new LoseDexterityPower(owner, amount)));
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
}
