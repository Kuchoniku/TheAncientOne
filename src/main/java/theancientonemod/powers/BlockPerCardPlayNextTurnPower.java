package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static theancientonemod.ancientOneMod.makeID;

public class BlockPerCardPlayNextTurnPower extends BasePower{
    public static final String POWER_ID = makeID(BlockPerCardPlayNextTurnPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    int counter = 0;
    public BlockPerCardPlayNextTurnPower(AbstractCreature owner, int amount) {
        super (POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ApplyPowerAction(owner, owner, new BlockPerCardPlayPower(owner, amount)));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
}
