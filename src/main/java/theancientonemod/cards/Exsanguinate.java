package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.powers.BasePower;
import theancientonemod.powers.BleedPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Exsanguinate extends BaseCard{
    public static final String ID = makeID(Exsanguinate.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ENEMY,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DMG = 8;
    private static final int UPG_COST = 0;
    public Exsanguinate() {
        super(ID, info);
        setDamage(DMG);
        setCostUpgrade(UPG_COST);
    }
    public void calculateCardDamage(AbstractMonster mo) {
        if (mo.hasPower(BleedPower.POWER_ID)) {
            this.baseMagicNumber = ((BleedPower)mo.getPower(BleedPower.POWER_ID)).amount2;
        } else {
           baseMagicNumber = 0;
        }
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damage+=magicNumber;
        calculateCardDamage(m);
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }
    @Override
    public AbstractCard makeCopy() {
        return new Exsanguinate();
    }
}
