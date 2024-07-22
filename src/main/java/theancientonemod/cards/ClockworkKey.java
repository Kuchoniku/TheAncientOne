package theancientonemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class ClockworkKey extends BaseCard{
    public static final String ID = makeID(ClockworkKey.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            0//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int UNLOCK = 1;
    private boolean playedLastTurn = false;
    public ClockworkKey() {
        super(ID, info);
        setMagic(UNLOCK);
        setEthereal(true, false);
        setSelfRetain(false, true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (GameActionManager.turn%2!=0) {
            addToBot(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0], false, false, (c) -> true, (cards)->{
                for (AbstractCard c : cards) {
                    LockAmountFieldPatch.changeLockAmount(c, 1);
                }
            }));
        }
        else {
            addToBot(new UnlockHandAction(p, magicNumber));
        }
        playedLastTurn = true;

    }

    @Override
    public void atTurnStart() {
        if (playedLastTurn) {
            addToBot(new DiscardToHandAction(this));
        }
        playedLastTurn=false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new ClockworkKey();
    }
}
