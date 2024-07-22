package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.LockCardAction;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class ChargeBeam extends BaseCard{
    public static final String ID = makeID(ChargeBeam.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ENEMY,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;
    private static final int RETAINED_DMG = 2;
    private static final int RETAINED_DMG_UPG = 1;
    private static final int LOCK = 2;
    public ChargeBeam() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(RETAINED_DMG, RETAINED_DMG_UPG);
        setCustomVar("lock", LOCK);
        tags.add(AncientOneTags.BEAM);
        this.selfRetain = true;
        LockAmountFieldPatch.lockAmount.set(this, customVar("lock"));
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new LockCardAction(this, customVar("lock")));
    }
    public void onRetained () {
        this.upgradeDamage(magicNumber);
    }
    @Override
    public AbstractCard makeCopy() {
        return new ChargeBeam();
    }
}
