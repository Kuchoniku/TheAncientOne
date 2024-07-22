package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Defend extends BaseCard{
    public static final String ID = makeID(Defend.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.BASIC,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;
    public Defend() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        tags.add(CardTags.STARTER_DEFEND);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }
    @Override
    public AbstractCard makeCopy() {
        return new Defend();
    }
}
