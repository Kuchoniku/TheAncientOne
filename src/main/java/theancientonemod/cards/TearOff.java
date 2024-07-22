package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class TearOff extends BaseCard{
    public static final String ID = makeID(TearOff.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            0//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int UNLOCK = 2;
    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = -1;
    public TearOff() {
        super(ID, info);
        setMagic(UNLOCK);
        setCustomVar("Dmg", DAMAGE, UPG_DAMAGE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction (p, new DamageInfo(p, customVar("Dmg"), DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SMASH));
        addToBot(new UnlockHandAction(p, magicNumber));
    }
    @Override
    public AbstractCard makeCopy() {
        return new TearOff();
    }
}
