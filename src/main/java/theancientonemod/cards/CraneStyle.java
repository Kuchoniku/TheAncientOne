package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.powers.CraneStylePower;
import theancientonemod.powers.NextTurnRetainEnergyPower;
import theancientonemod.powers.RetainEnergyPower;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class CraneStyle extends BaseCard{
    public static final String ID = makeID(CraneStyle.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int HOLD = 3;
    private static final int UPG_HOLD = 2;
    public CraneStyle() {
        super(ID, info);
        setMagic(HOLD, UPG_HOLD);
        tags.add(AncientOneTags.STYLE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscardAction(p, p, 1, false));
        addToBot(new ApplyPowerAction(p, p, new CraneStylePower(p,1, magicNumber)));
    }
    @Override
    public AbstractCard makeCopy() {
        return new CraneStyle();
    }
}
