package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class TwinBeam extends BaseCard{
    public static final String ID = makeID(TwinBeam.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ENEMY,//Look at other cards for different targets
            0//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 2;
    private static final int TIMES = 2;
    private static final int TIMES_UPG = 1;
    public TwinBeam() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(TIMES, TIMES_UPG);
        tags.add(AncientOneTags.BEAM);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i=0; i<magicNumber;i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new TwinBeam();
    }
}
