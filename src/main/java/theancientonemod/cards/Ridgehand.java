package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.LockCardAction;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;
import theancientonemod.spirePatches.LockAmountFieldPatch;

public class Ridgehand extends BaseCard{
    public static final String ID = makeID(Ridgehand.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.BASIC,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF_AND_ENEMY,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;
    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 2;
    private static final int LOCK_AMOUNT =1;
    public Ridgehand() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(LOCK_AMOUNT);
        LockAmountFieldPatch.lockAmount.set(this, magicNumber);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new LockCardAction(this, magicNumber));
    }
    @Override
    public AbstractCard makeCopy() {
        return new Ridgehand();
    }
}
