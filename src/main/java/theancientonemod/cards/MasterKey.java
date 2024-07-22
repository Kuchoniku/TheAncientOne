package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import theancientonemod.actions.MasterKeyAction;
import theancientonemod.actions.MonkeyStyleAction;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class MasterKey extends BaseCard{
    public static final String ID = makeID(MasterKey.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            -1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int REFUND = 1;
    private static final int UPG_REFUND = 1;
    public MasterKey() {
        super(ID, info);
        setMagic(REFUND, UPG_REFUND);
        this.exhaust=true;
        tags.add(AncientOneTags.UNLOCKABLE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MasterKeyAction(p, energyOnUse, magicNumber, upgraded, freeToPlayOnce));

    }
    @Override
    public AbstractCard makeCopy() {
        return new MasterKey();
    }
}
