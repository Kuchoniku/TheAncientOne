package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class ShinyKey extends BaseCard{
    public static final String ID = makeID(ShinyKey.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int UNLOCK = 1;
    private static final int DRAW = 1;
    private boolean playedLastTurn = false;
    public ShinyKey() {
        super(ID, info);
        setMagic(UNLOCK);
        setCustomVar("draw", DRAW);
        setSelfRetain(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, customVar("draw")));
        addToBot(new UnlockHandAction(p, magicNumber));
        if (upgraded) {
            playedLastTurn = true;
        }
    }

    @Override
    public void atTurnStart() {
        if (upgraded&&playedLastTurn) {
            addToBot(new DiscardToHandAction(this));
        }
        playedLastTurn=false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new ShinyKey();
    }
}
