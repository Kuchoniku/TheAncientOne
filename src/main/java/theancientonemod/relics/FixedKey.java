package theancientonemod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.actions.LockCardAction;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;

import static theancientonemod.ancientOneMod.makeID;

public class FixedKey extends BaseRelic {
    private static final String NAME = FixedKey.class.getSimpleName(); //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    //Add Fields Here
    private static final int UNLOCK = 1;
    private boolean trigger;
    public FixedKey() {
        super(ID, NAME, TheAncientOne.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atTurnStart() {
        trigger = true;
        beginLongPulse();
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if(trigger) {
            if (LockAmountFieldPatch.lockAmount.get(drawnCard) > 0) {
                this.flash();
                addToBot(new LockCardAction(drawnCard, -UNLOCK));
                this.trigger=false;
                this.grayscale=true;
                stopPulse();
            }
        }
    }
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(BrokenKey.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(BrokenKey.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(BrokenKey.ID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
