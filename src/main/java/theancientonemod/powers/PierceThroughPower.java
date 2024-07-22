package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theancientonemod.ancientOneMod.makeID;

public class PierceThroughPower extends BasePower {
    public static final String POWER_ID = makeID(PierceThroughPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public PierceThroughPower(AbstractCreature owner, int amount) {
        super (POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public int newOnGainedBlock(float blockAmount) {
        addToBot(new ApplyPowerAction(owner, AbstractDungeon.player, new BleedPower(owner, amount)));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
        return 0;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
}
