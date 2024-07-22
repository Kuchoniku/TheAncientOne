package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.KeybladeAction;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Keyblade extends BaseCard{
    public static final String ID = makeID(Keyblade.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ENEMY,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;
    private static final int UNLOCK = 1;
    public Keyblade() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(UNLOCK);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new KeybladeAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), p, magicNumber));

    }
    @Override
    public AbstractCard makeCopy() {
        return new Keyblade();
    }
}
