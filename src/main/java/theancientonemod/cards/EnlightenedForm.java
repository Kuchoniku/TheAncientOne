package theancientonemod.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.powers.ChooseLockStartOfTurnPower;
import theancientonemod.powers.RandomLockStartOfTurnPower;
import theancientonemod.powers.ReadyPositionPower;
import theancientonemod.powers.UnlockStartOfTurnPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class EnlightenedForm extends BaseCard{
    public static final String ID = makeID(EnlightenedForm.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.POWER,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            3//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int UNLOCK = 1;
    private static final int LOCK = 2;
    public EnlightenedForm() {
        super(ID, info);
        setMagic(UNLOCK);
        setCustomVar("lock", LOCK);
        tags.add(BaseModCardTags.FORM);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            addToBot(new ApplyPowerAction(p, p, new ChooseLockStartOfTurnPower(p, customVar("lock"))));
        } else {
            addToBot(new ApplyPowerAction(p, p, new RandomLockStartOfTurnPower(p, customVar("lock"))));
        }
            addToBot(new ApplyPowerAction(p, p, new UnlockStartOfTurnPower(p,magicNumber)));
    }
    @Override
    public AbstractCard makeCopy() {
        return new EnlightenedForm();
    }
}
