package theancientonemod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theancientonemod.actions.PlayAutobeamsAction;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.ancientOneMod;

import static theancientonemod.ancientOneMod.makeID;

public class KeyTea extends BasePotion{
    public static final String ID = makeID(KeyTea.class.getSimpleName());
    private static final Color LIQUID_COLOR = CardHelper.getColor(100, 100, 100);
    private static final Color HYBRID_COLOR = CardHelper.getColor(60, 60, 60);
    private static final Color SPOTS_COLOR = null;
    public KeyTea() {
        super(ID, 3, PotionRarity.RARE, PotionSize.EYE, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
    }
    @Override
    public String getDescription() {
        return potionStrings.DESCRIPTIONS[0]+potency+potionStrings.DESCRIPTIONS[1];
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            addToBot(new UnlockHandAction(AbstractDungeon.player, potency));
        }
    }
    public void addAdditionalTips() {
        this.tips.add(new PowerTip(ancientOneMod.keywords.get(makeID("Unlock")).PROPER_NAME, ancientOneMod.keywords.get(makeID("Unlock")).DESCRIPTION));
    }
}
