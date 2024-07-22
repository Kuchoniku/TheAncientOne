package theancientonemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.LockCardAction;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class SkeletonKey extends BaseCard{
    public static final String ID = makeID(SkeletonKey.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF_AND_ENEMY,//Look at other cards for different targets
            2//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int UNLOCK = 1;
    private static final int UPG_UNLOCK = 1;
    public SkeletonKey() {
        super(ID, info);
        setMagic(UNLOCK, UPG_UNLOCK);
        this.exhaust = true;
        setSelfRetain(false, true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(magicNumber, cardStrings.EXTENDED_DESCRIPTION[0], false, false, (c) -> true, (cards)->{
            for (AbstractCard c : cards) {
                addToBot(new LockCardAction(c, -LockAmountFieldPatch.lockAmount.get(c)));
                addToBot(new NewQueueCardAction(c, m, false, true));
            }
        }));
    }
    @Override
    public AbstractCard makeCopy() {
        return new SkeletonKey();
    }
}
