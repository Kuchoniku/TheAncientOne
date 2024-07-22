package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.powers.DamageOnUnlockPower;
import theancientonemod.powers.DexOnUnlockPower;
import theancientonemod.powers.NextTurnRetainEnergyPower;
import theancientonemod.powers.RetainEnergyPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class LightFeet extends BaseCard{
    public static final String ID = makeID(LightFeet.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.POWER,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 2;
    private static final int DEX = 1;
    public LightFeet() {
        super(ID, info);
        setMagic(DAMAGE, UPG_DAMAGE);
        setCustomVar("Dex", DEX);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DamageOnUnlockPower(p,magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new DexOnUnlockPower(p,customVar("Dex"))));
    }
    @Override
    public AbstractCard makeCopy() {
        return new LightFeet();
    }
}
