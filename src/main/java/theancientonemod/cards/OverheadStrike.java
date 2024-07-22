package theancientonemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.TurnStartLockAction;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class OverheadStrike extends BaseCard{
    public static final String ID = makeID(OverheadStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ENEMY,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 11;
    private static final int UPG_DAMAGE = 5;
    private static final int LOCK = 1;
    public OverheadStrike() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(LOCK);
        tags.add(CardTags.STRIKE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        if (!upgraded) {
            addToBot(new TurnStartLockAction(magicNumber, false, false, cardStrings.EXTENDED_DESCRIPTION[0]));
        } else {
            addToBot(new SelectCardsInHandAction(magicNumber, cardStrings.EXTENDED_DESCRIPTION[0], (cards)->{
                for (AbstractCard c : cards) {
                    LockAmountFieldPatch.changeLockAmount(c, magicNumber);
                }
            }));
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new OverheadStrike();
    }
}
