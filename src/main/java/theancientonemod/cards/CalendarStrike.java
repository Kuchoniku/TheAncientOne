package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class CalendarStrike extends BaseCard{
    public static final String ID = makeID(CalendarStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ENEMY,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 14;
    private static final int UPG_DAMAGE = 4;
    private static final int TURN = 5;
    private static final int UPG_TURN = -1;
    public CalendarStrike() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(TURN, UPG_TURN);
        tags.add(CardTags.STRIKE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            if (GameActionManager.turn<magicNumber) {
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0] + magicNumber + cardStrings.EXTENDED_DESCRIPTION[1];
                return false;
            }
            else {
                return true;
            }
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new CalendarStrike();
    }
}
