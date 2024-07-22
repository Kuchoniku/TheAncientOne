package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theancientonemod.ancientOneMod.makeID;

public class DmgPerCardPlayPower extends BasePower{
    public static final String POWER_ID = makeID(DmgPerCardPlayPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    int counter = 0;
    public DmgPerCardPlayPower(AbstractCreature owner, int amount) {
        super (POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        counter+=amount;
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new DamageRandomEnemyAction(new DamageInfo(owner, counter, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.LIGHTNING));
            addToBot(new RemoveSpecificPowerAction(owner, owner, this.ID));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1]+counter+DESCRIPTIONS[2];
    }
}
