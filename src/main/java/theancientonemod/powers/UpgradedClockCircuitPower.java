package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theancientonemod.actions.PlayAutobeamsAction;
import theancientonemod.cards.Autobeam;

import static theancientonemod.ancientOneMod.makeID;

public class UpgradedClockCircuitPower extends BasePower{
    public static final String POWER_ID = makeID(UpgradedClockCircuitPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    public UpgradedClockCircuitPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new PlayAutobeamsAction(null, true, amount));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
}
