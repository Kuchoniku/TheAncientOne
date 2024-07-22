package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.powers.SanguinePower;
import theancientonemod.powers.WitheringPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Sanguine extends BaseCard{
    public static final String ID = makeID(Sanguine.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.POWER,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 1;
    public Sanguine() {
        super(ID, info);
        setMagic(BLOCK, UPG_BLOCK);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new ApplyPowerAction(p, p, new SanguinePower(p,magicNumber)));
    }
    @Override
    public AbstractCard makeCopy() {
        return new Sanguine();
    }
}
