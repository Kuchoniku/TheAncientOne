package theancientonemod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.actions.TurnShiftAction;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;

import static theancientonemod.ancientOneMod.makeID;

public class TimeMachine extends BaseRelic {
    private static final String NAME = TimeMachine.class.getSimpleName(); //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.UNCOMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    //Add Fields Here
    private static final int TURN = 3;
    public TimeMachine() {
        super(ID, NAME, TheAncientOne.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStartPreDraw() {
        this.flash();
        addToBot(new TurnShiftAction(TURN-1));
    }
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]+TURN+DESCRIPTIONS[1];
    }
}
