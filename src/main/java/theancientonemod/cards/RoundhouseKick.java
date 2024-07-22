package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.TurnShiftAction;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class RoundhouseKick extends BaseCard{
    public static final String ID = makeID(RoundhouseKick.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ENEMY,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 3;
    private static final int TURN = -2;
    private static final int SPEEDS = 2;
    private static final int SPEEDS_UPG = 1;
    public RoundhouseKick() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(-TURN);
        setCustomVar("speeds", SPEEDS, SPEEDS_UPG);
        this.cardsToPreview = new TimewarpSpeed();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new TurnShiftAction(-magicNumber));
        addToBot(new MakeTempCardInDrawPileAction(new TimewarpSpeed(), customVar("speeds"), true, true));
    }
    @Override
    public AbstractCard makeCopy() {
        return new RoundhouseKick();
    }
}
