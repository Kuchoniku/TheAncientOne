package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.powers.ClockCircuitPower;
import theancientonemod.powers.DamageOnUnlockPower;
import theancientonemod.powers.DexOnUnlockPower;
import theancientonemod.powers.UpgradedClockCircuitPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class ClockCircuit extends BaseCard{
    public static final String ID = makeID(ClockCircuit.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.POWER,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BEAMS = 2;
    public ClockCircuit() {
        super(ID, info);
        setMagic(BEAMS);
        this.baseInnate = false;
        this.upgInnate = true;
        this.cardsToPreview = new Autobeam();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            addToBot(new ApplyPowerAction(p, p, new UpgradedClockCircuitPower(p,magicNumber)));
        } else {
            addToBot(new ApplyPowerAction(p, p, new ClockCircuitPower(p,magicNumber)));
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new ClockCircuit();
    }
    public void upgrade() {
        super.upgrade();
        Autobeam a = new Autobeam();
        a.upgrade();
        this.cardsToPreview = a;
    }
}
