package theancientonemod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theancientonemod.actions.PlayAutobeamsAction;

import static theancientonemod.ancientOneMod.makeID;

public class BeamInABottle extends BasePotion{
    public static final String ID = makeID(BeamInABottle.class.getSimpleName());
    private static final Color LIQUID_COLOR = CardHelper.getColor(120, 120, 255);
    private static final Color HYBRID_COLOR = CardHelper.getColor(60, 60, 255);
    private static final Color SPOTS_COLOR = null;
    public BeamInABottle() {
        super(ID, 4, PotionRarity.UNCOMMON, PotionSize.JAR, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
        isThrown=true;
    }
    @Override
    public String getDescription() {
        return potionStrings.DESCRIPTIONS[0]+potency+potionStrings.DESCRIPTIONS[1];
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            addToBot(new PlayAutobeamsAction(null, true, potency));
        }
    }
}
