package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.powers.LockCardsPlayedPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class MentalShackles extends BaseCard{
    public static final String ID = makeID(MentalShackles.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            0//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DRAW = 3;
    private static final int UPG_DRAW = 1;
    public MentalShackles() {
        super(ID, info);
        setMagic(DRAW, UPG_DRAW);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber, false));
        addToBot(new ApplyPowerAction(p, p, new LockCardsPlayedPower(p, 1)));
    }
    @Override
    public AbstractCard makeCopy() {
        return new MentalShackles();
    }
}
