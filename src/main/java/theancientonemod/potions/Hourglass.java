package theancientonemod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theancientonemod.actions.PlayAutobeamsAction;
import theancientonemod.actions.TurnShiftAction;
import theancientonemod.ancientOneMod;
import theancientonemod.powers.BleedPower;

import static theancientonemod.ancientOneMod.makeID;

public class Hourglass extends BasePotion{
    public static final String ID = makeID(Hourglass.class.getSimpleName());
    private static final Color LIQUID_COLOR = CardHelper.getColor(200, 200, 120);
    private static final Color HYBRID_COLOR = CardHelper.getColor(200, 200, 60);
    private static final Color SPOTS_COLOR = null;
    public Hourglass() {
        super(ID, 2, PotionRarity.COMMON, PotionSize.SPIKY, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
        isThrown=true;
        targetRequired=true;
    }
    @Override
    public String getDescription() {
        return potionStrings.DESCRIPTIONS[0]+potency+potionStrings.DESCRIPTIONS[1]+potency+potionStrings.DESCRIPTIONS[2];
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            addToBot(new ApplyPowerAction(abstractCreature, AbstractDungeon.player, new BleedPower(abstractCreature, potency)));
            addToBot(new TurnShiftAction(potency));
        }
    }
    public void addAdditionalTips() {
        this.tips.add(new PowerTip(TipHelper.capitalize(ancientOneMod.keywords.get(makeID("Bleed")).PROPER_NAME), ancientOneMod.keywords.get(makeID("Bleed")).DESCRIPTION));
    }
}
