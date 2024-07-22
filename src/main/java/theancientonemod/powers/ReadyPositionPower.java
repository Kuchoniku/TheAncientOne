package theancientonemod.powers;

import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import theancientonemod.cards.Autobeam;
import theancientonemod.spirePatches.AncientOneTags;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static theancientonemod.ancientOneMod.makeID;

public class ReadyPositionPower extends BasePower{
    public static final String POWER_ID = makeID(ReadyPositionPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public ReadyPositionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        ArrayList<String> tmp = new ArrayList();
        Iterator var3 = CardLibrary.cards.entrySet().iterator();
        while (var3.hasNext()) {
            Map.Entry<String, AbstractCard> c = (Map.Entry) var3.next();
            if (c.getValue().hasTag(AncientOneTags.STYLE)) {
                tmp.add(c.getKey());
            }
        }
        AbstractCard style = CardLibrary.cards.get(tmp.get(AbstractDungeon.cardRng.random(0, tmp.size() - 1)));
        for (int i = 0; i<amount; i++) {
            style = style.makeCopy();
            style.purgeOnUse = true;
            addToBot(new NewQueueCardAction(style, true, false, true));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
}
