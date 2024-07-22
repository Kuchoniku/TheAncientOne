package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.powers.NextTurnRetainEnergyPower;
import theancientonemod.powers.RetainEnergyPower;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class CatStyle extends BaseCard{
    public static final String ID = makeID(CatStyle.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            0//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 2;
    public CatStyle() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        tags.add(AncientOneTags.STYLE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new RetainEnergyPower(p,1)));
        if(upgraded) {
            addToBot(new ApplyPowerAction(p, p, new NextTurnRetainEnergyPower(p, 1)));
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new CatStyle();
    }
}
