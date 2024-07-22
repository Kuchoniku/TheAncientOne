package theancientonemod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.actions.LockCardAction;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;

import static theancientonemod.ancientOneMod.makeID;

public class MagnifyingGlass extends BaseRelic {
    private static final String NAME = MagnifyingGlass.class.getSimpleName(); //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    //Add Fields Here
    private static final int DAMAGE = 4;
    private boolean trigger;
    public MagnifyingGlass() {
        super(ID, NAME, TheAncientOne.Meta.CARD_COLOR, RARITY, SOUND);
    }

    public float atDamageModify(float damage, AbstractCard c) {
        return c.hasTag(AncientOneTags.BEAM) ? damage+DAMAGE : damage;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]+DAMAGE+DESCRIPTIONS[1];
    }
}
