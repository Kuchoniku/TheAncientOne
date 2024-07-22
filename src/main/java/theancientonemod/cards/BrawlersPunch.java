package theancientonemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.BrawlersPunchAction;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class BrawlersPunch extends BaseCard{
    public static final String ID = makeID(BrawlersPunch.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ENEMY,//Look at other cards for different targets
            2//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;
    private static final int LOCK = 1;
    private static final int LOCK_UPG = 2;
    public BrawlersPunch() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(LOCK, LOCK_UPG);
        tags.add(CardTags.STRIKE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(magicNumber, "Lock", upgraded, upgraded, (c) -> true, (cards)->{
            for (AbstractCard c : cards) {
                LockAmountFieldPatch.changeLockAmount(c, 1);
            }
        }));
        addToBot(new BrawlersPunchAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), p, damage));
    }
    @Override
    public AbstractCard makeCopy() {
        return new BrawlersPunch();
    }
}
