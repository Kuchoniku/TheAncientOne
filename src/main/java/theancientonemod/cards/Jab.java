package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import theancientonemod.powers.TempStrNextTurnPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Jab extends BaseCard{
    public static final String ID = makeID(Jab.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ENEMY,//Look at other cards for different targets
            0//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 2;
    private static final int CARD = 1;
    private static final int UPG_CARD = 1;
    public Jab() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(CARD, UPG_CARD);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, magicNumber)));
    }
    @Override
    public AbstractCard makeCopy() {
        return new Jab();
    }
}
