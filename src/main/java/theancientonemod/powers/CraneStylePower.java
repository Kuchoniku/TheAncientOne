package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theancientonemod.ancientOneMod.makeID;

public class CraneStylePower extends BasePower{
    public static final String POWER_ID = makeID(CraneStylePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    public int numbermax = 0;
    private int number = 0;
    public CraneStylePower (AbstractCreature owner, int amount, int numbermax) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        AbstractPower p = AbstractDungeon.player.getPower(ID);
        if (p!=null) {
            if (numbermax>((CraneStylePower)p).numbermax) {
                ((CraneStylePower)p).numbermax=numbermax;
                updateDescription();
            }
        } else {
            this.numbermax = numbermax;
            updateDescription();
        }
    }
    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isPlayer) {
            if (AbstractDungeon.player.hand.size()<numbermax) {
                number = AbstractDungeon.player.hand.size();
            } else {
                number = numbermax;
            }
        }
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new DrawCardAction(owner, number));
        number=0;
        reducePower(1);
        if (amount<=0) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
            numbermax=0;
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1]+numbermax;
    }
}
