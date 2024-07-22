package theancientonemod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;

import static theancientonemod.ancientOneMod.makeID;

public class BrokenKey extends BaseRelic {
    private static final String NAME = BrokenKey.class.getSimpleName(); //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    //Add Fields Here
    private static final int UNLOCK = 1;
    private boolean trigger;
    public BrokenKey() {
        super(ID, NAME, TheAncientOne.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStartPreDraw() {
        trigger=true;
        beginLongPulse();
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if(trigger) {
            if (LockAmountFieldPatch.lockAmount.get(drawnCard) > 0) {
                this.flash();
                addToBot(new UnlockHandAction(AbstractDungeon.player, UNLOCK));
                this.trigger=false;
                this.grayscale=true;
                stopPulse();

            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]+UNLOCK+DESCRIPTIONS[1];
    }
}
