package theancientonemod.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theancientonemod.actions.BleedLoseHpAction;

import static theancientonemod.ancientOneMod.makeID;

public class BleedPower extends BasePower implements HealthBarRenderPower {
    public static final String POWER_ID = makeID(BleedPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;
    public BleedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        amount2 = amount;
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        amount2+=stackAmount;
        if (amount2<1) {
            amount2=1;
        }
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer&&owner.equals(AbstractDungeon.player)) {
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.flashWithoutSound();
                this.addToBot(new BleedLoseHpAction(this.owner, this.source, this.amount2, AbstractGameAction.AttackEffect.POISON, this.ID));
            }
        }
    }
    @Override
    public void atStartOfTurn() {
        if (owner!=AbstractDungeon.player) {
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.flashWithoutSound();
                this.addToBot(new BleedLoseHpAction(this.owner, this.source, this.amount2, AbstractGameAction.AttackEffect.POISON, this.ID));
            }
        }
    }
    @Override
    public void startOfPlayerTurn() {
        amount2++;
        updateDescription();
    }
    @Override
    public void turnShifted (int amount, boolean timeSlip) {
        amount2+=amount;
        if (amount2<1) {
            amount2=1;
        }
        updateDescription();
    }
    public void updateDescription() {
        if (owner==AbstractDungeon.player) {
            this.description = DESCRIPTIONS[1]+amount2+DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0]+amount2+DESCRIPTIONS[2];
        }
    }

    @Override
    public int getHealthBarAmount() {
        return amount2;
    }

    @Override
    public Color getColor() {
        return Color.MAGENTA;
    }
}
