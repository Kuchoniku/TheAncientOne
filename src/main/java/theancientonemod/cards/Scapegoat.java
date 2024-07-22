package theancientonemod.cards;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.LockCardAction;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Scapegoat extends BaseCard{
    public static final String ID = makeID(Scapegoat.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF_AND_ENEMY,//Look at other cards for different targets
            0//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 3;
    private static final int BLOCK = 2;
    private static final int LOCK_AMOUNT = 0;
    private static final int LOCK_AMOUNT_UPG = 2;
    public Scapegoat() {
        super(ID, info);
        tags.add(AncientOneTags.UNLOCKABLE);
        setDamage(DAMAGE);
        setBlock(BLOCK);
        setMagic(LOCK_AMOUNT, LOCK_AMOUNT_UPG);
        LockAmountFieldPatch.lockAmount.set(this, magicNumber);
    }

    @Override
    public void upgrade() {
        super.upgrade();
        LockAmountFieldPatch.lockAmount.set(this, magicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i=0; i<LockAmountFieldPatch.lockAmount.get(this); i++) {
            addToBot(new GainBlockAction(p, p, block, true));
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
        if (LockAmountFieldPatch.lockAmount.get(this)<magicNumber) {
            addToBot(new LockCardAction(this, magicNumber-LockAmountFieldPatch.lockAmount.get(this)));
        }
    }



    @Override
    public AbstractCard makeCopy() {
        return new Scapegoat();
    }
}
