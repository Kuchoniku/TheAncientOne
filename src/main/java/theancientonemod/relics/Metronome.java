package theancientonemod.relics;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theancientonemod.actions.TurnShiftAction;
import theancientonemod.theAncientOne.TheAncientOne;

import static theancientonemod.ancientOneMod.makeID;

public class Metronome extends BaseRelic {
    private static final String NAME = Metronome.class.getSimpleName(); //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SHOP; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.FLAT; //The sound played when the relic is clicked.
    //Add Fields Here
    private static final int TURN = 1;
    public Metronome() {
        super(ID, NAME, TheAncientOne.Meta.CARD_COLOR, RARITY, SOUND);
    }
    @Override
    public void atTurnStart() {
        if (GameActionManager.turn%2!=0) {
            beginLongPulse();
        } else {
            stopPulse();
        }
    }

    @Override
    public void onTurnShifted(int amount) {
        if (GameActionManager.turn%2==0) {
            beginLongPulse();
        } else {
            stopPulse();
        }
    }

    @Override
    public void onPlayerEndTurn() {
        if (GameActionManager.turn%2==0) {
            flash();
            addToBot(new TurnShiftAction(-TURN));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]+TURN+DESCRIPTIONS[1];
    }
}
