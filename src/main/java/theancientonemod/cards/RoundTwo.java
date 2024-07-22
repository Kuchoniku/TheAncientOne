package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theancientonemod.actions.TurnShiftAction;
import theancientonemod.powers.BlockOnLockPower;
import theancientonemod.powers.StrOnLockPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class RoundTwo extends BaseCard{
    public static final String ID = makeID(RoundTwo.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.POWER,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            2//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int AMOUNT=2;
    private static final int ENERGY_UPG = 1;
    private static final int TURN = 1;
    public RoundTwo() {
        super(ID, info);
        setMagic(AMOUNT);
        setCostUpgrade(ENERGY_UPG);
        setCustomVar("turn", TURN);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new TurnShiftAction(customVar("turn") - GameActionManager.turn));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p,magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p,magicNumber)));
    }
    @Override
    public AbstractCard makeCopy() {
        return new RoundTwo();
    }
}
