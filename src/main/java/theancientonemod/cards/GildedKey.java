package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.TurnShiftAction;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class GildedKey extends BaseCard{
    public static final String ID = makeID(GildedKey.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            0//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int UNLOCK = 1;
    private static final int TURN_COUNT= 1;
    private static final int BLOCK = 4;
    private static final int BLOCK_UPG = 4;
    private static final int DRAW = 1;
    public GildedKey() {
        super(ID, info);
        setMagic(UNLOCK);
        setBlock(BLOCK, BLOCK_UPG);
        setCustomVar("turn", TURN_COUNT);
        setCustomVar("draw", DRAW);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new UnlockHandAction(p, magicNumber));
        addToBot(new TurnShiftAction(customVar("turn")));
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new DrawCardAction(p, customVar("draw")));
    }
    @Override
    public AbstractCard makeCopy() {
        return new GildedKey();
    }
}
