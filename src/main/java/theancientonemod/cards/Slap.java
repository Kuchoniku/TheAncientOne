package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.powers.BleedPower;
import theancientonemod.powers.TempStrNextTurnPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Slap extends BaseCard{
    public static final String ID = makeID(Slap.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ENEMY,//Look at other cards for different targets
            2//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;
    private static final int BLEED = 4;
    private static final int UPG_BLEED = 2;
    private boolean playedLastTurn = false;
    public Slap() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(BLEED, UPG_BLEED);
        setEthereal(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(m, p, new BleedPower(m, magicNumber)));
        playedLastTurn = true;
    }
    @Override
    public void atTurnStart() {
        if (playedLastTurn) {
            addToBot(new DiscardToHandAction(this));
        }
        playedLastTurn=false;
    }
    @Override
    public AbstractCard makeCopy() {
        return new Slap();
    }
}
