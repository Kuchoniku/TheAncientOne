package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.ChainWhipLockAction;
import theancientonemod.actions.ChainWhipPlayAction;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class ChainWhip extends BaseCard{
    public static final String ID = makeID(ChainWhip.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ALL_ENEMY,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 2;
    private static final int INCREASE = 2;
    private static final int INCREASE_UPG = 1;
    public ChainWhip() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(INCREASE, INCREASE_UPG);
        this.isMultiDamage = true;
        tags.add(AncientOneTags.UNLOCKABLE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
        addToBot(new ChainWhipPlayAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng), 1));
    }
    public void onLock () {
        addToBot(new ChainWhipLockAction(magicNumber));
    }
    @Override
    public AbstractCard makeCopy() {
        return new ChainWhip();
    }
}
