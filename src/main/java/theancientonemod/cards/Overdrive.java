package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theancientonemod.actions.TurnShiftAction;
import theancientonemod.powers.OverdriveDamagePower;
import theancientonemod.powers.OverdriveUpgradePower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Overdrive extends BaseCard{
    public static final String ID = makeID(Overdrive.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.POWER,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int UPGRADE=5;
    private static final int UPGRADE_UPG = 2;
    private static final int SELF_DAMAGE = 2;
    public Overdrive() {
        super(ID, info);
        setMagic(UPGRADE, UPGRADE_UPG);
        setCustomVar("selfDamage", SELF_DAMAGE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new OverdriveUpgradePower(p,magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new OverdriveDamagePower(p,customVar("selfDamage"))));
    }
    @Override
    public AbstractCard makeCopy() {
        return new Overdrive();
    }
}
