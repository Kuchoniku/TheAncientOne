package theancientonemod.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.actions.CombinationLockAction;
import theancientonemod.theAncientOne.TheAncientOne;

import static theancientonemod.ancientOneMod.makeID;

public class CombinationLock extends BaseRelic {
    private static final String NAME = CombinationLock.class.getSimpleName(); //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.SOLID; //The sound played when the relic is clicked.
    //Add Fields Here
    private static final int LOCK = 2;
    public CombinationLock() {
        super(ID, NAME, TheAncientOne.Meta.CARD_COLOR, RARITY, SOUND);
    }
    @Override
    public void atTurnStartPostDraw() {
        flash();
        addToBot(new CombinationLockAction(LOCK));
    }
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]+LOCK+DESCRIPTIONS[1]+LOCK+DESCRIPTIONS[2];
    }
}
