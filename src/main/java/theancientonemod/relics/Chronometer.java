package theancientonemod.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.actions.TurnShiftAction;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;

import static theancientonemod.ancientOneMod.makeID;

public class Chronometer extends BaseRelic implements ClickableRelic {
    private static final String NAME = Chronometer.class.getSimpleName(); //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    //Add Fields Here
    public Chronometer() {
        super(ID, NAME, TheAncientOne.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStartPreDraw() {
        counter=0;
    }

    @Override
    public void atTurnStart() {
        counter++;
    }

    @Override
    public void onTurnShifted(int amount) {
        counter+=amount;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onRightClick() {
        addToBot(new TurnShiftAction(0));
    }
}
