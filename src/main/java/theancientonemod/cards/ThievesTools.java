package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.powers.ThievesToolsPower;
import theancientonemod.powers.WitheringPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class ThievesTools extends BaseCard{
    public static final String ID = makeID(ThievesTools.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.POWER,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            2//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int UPG_COST = 1;
    public ThievesTools() {
        super(ID, info);
        setCostUpgrade(UPG_COST);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new ApplyPowerAction(p, p, new ThievesToolsPower(p,1)));
    }
    @Override
    public AbstractCard makeCopy() {
        return new ThievesTools();
    }
}
