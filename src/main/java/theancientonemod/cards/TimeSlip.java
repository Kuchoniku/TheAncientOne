package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.powers.TimeSlipPower;
import theancientonemod.powers.UpgradedTimeSlipPower;
import theancientonemod.powers.WitheringPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class TimeSlip extends BaseCard{
    public static final String ID = makeID(TimeSlip.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.POWER,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            3//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static int SHIFT = 1;
    public TimeSlip() {
        super(ID, info);
        setMagic(SHIFT);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            addToBot(new ApplyPowerAction(p, p, new UpgradedTimeSlipPower(p, magicNumber)));
        } else {
            addToBot(new ApplyPowerAction(p, p, new TimeSlipPower(p, magicNumber)));
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new TimeSlip();
    }
}
