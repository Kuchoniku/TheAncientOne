package theancientonemod.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.cards.Autobeam;
import theancientonemod.spirePatches.AncientOneTags;

import static theancientonemod.ancientOneMod.makeID;

public class OverdriveUpgradePower extends BasePower {
    public static final String POWER_ID = makeID(OverdriveUpgradePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    public OverdriveUpgradePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card.hasTag(AncientOneTags.BEAM)) {
            return damage + amount;
        }
        return damage;
    }


    public void updateDescription() {this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];}
}
