package theancientonemod.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theancientonemod.actions.TurnShiftAction;
import theancientonemod.theAncientOne.TheAncientOne;

import static theancientonemod.ancientOneMod.makeID;

public class TwistedWires extends BaseRelic {
    private static final String NAME = TwistedWires.class.getSimpleName(); //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    //Add Fields Here
    private static final int AMOUNT = 1;
    private static final int TURN = 3;
    public TwistedWires() {
        super(ID, NAME, TheAncientOne.Meta.CARD_COLOR, RARITY, SOUND);
    }
    @Override
    public void atTurnStart() {
        if (GameActionManager.turn == TURN) {
            grayscale = false;
            beginLongPulse();
        } else if (GameActionManager.turn>TURN) {
            stopPulse();
            grayscale = true;
        }
        else {
            stopPulse();
            grayscale = false;
        }
    }

    @Override
    public void onTurnShifted(int amount) {
        if (GameActionManager.turn == TURN) {
            grayscale = false;
            beginLongPulse();
        } else if (GameActionManager.turn>TURN) {
            stopPulse();
            grayscale = true;
        }
        else {
            stopPulse();
            grayscale = false;
        }
    }

    @Override
    public void onPlayerEndTurn() {
        if (GameActionManager.turn == TURN) {
            flash();
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, AMOUNT)));
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, AMOUNT)));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]+TURN+DESCRIPTIONS[1]+AMOUNT+DESCRIPTIONS[2];
    }
}
