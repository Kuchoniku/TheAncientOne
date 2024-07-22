package theancientonemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Padlock extends BaseCard{
    public static final String ID = makeID(Padlock.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            2//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BLOCK = 11;
    private static final int UPG_BLOCK = 3;
    private static final int ARMOR = 2;
    private static final int ARMOR_UPG = 1;
    private static final int LOCK = 1;
    public Padlock() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(ARMOR, ARMOR_UPG);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hand.size() <= LOCK) {
            int i = p.hand.size();

            for(int j = 0; j < i; ++j) {
                AbstractCard c = p.hand.getTopCard();
                LockAmountFieldPatch.changeLockAmount(c, 1);
            }
        } else {
            addToBot(new SelectCardsInHandAction(LOCK, cardStrings.EXTENDED_DESCRIPTION[0], (cards)->{
                for (AbstractCard c : cards) {
                    LockAmountFieldPatch.changeLockAmount(c, LOCK);
                }
            }));
        }
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, magicNumber)));
    }
    @Override
    public AbstractCard makeCopy() {
        return new Padlock();
    }
}
