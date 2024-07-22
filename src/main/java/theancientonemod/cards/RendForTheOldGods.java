package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.TurnShiftAction;
import theancientonemod.powers.BleedPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class RendForTheOldGods extends BaseCard{
    public static final String ID = makeID(RendForTheOldGods.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ENEMY,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;
    private static final int BLEED = 3;
    private static final int UPG_BLEED = 1;
    public RendForTheOldGods() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(BLEED, UPG_BLEED);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(BleedPower.POWER_ID)) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(m, p, new BleedPower(m, magicNumber)));
    }
    @Override
    public AbstractCard makeCopy() {
        return new RendForTheOldGods();
    }
}
