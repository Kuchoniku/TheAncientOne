package theancientonemod.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.powers.BleedPower;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;

import static theancientonemod.ancientOneMod.makeID;

public class SerratedMirror extends BaseRelic implements OnReceivePowerRelic {
    private static final String NAME = SerratedMirror.class.getSimpleName(); //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.UNCOMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.
    //Add Fields Here
    private static final int BLEED = 1;
    public SerratedMirror() {
        super(ID, NAME, TheAncientOne.Meta.CARD_COLOR, RARITY, SOUND);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]+BLEED+DESCRIPTIONS[1];
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature) {
        if (abstractPower.type == AbstractPower.PowerType.DEBUFF) {
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new BleedPower(m, BLEED)));
            }
        }
        return true;
    }
}
