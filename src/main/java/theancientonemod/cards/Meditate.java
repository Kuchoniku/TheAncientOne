package theancientonemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.TurnShiftAction;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Meditate extends BaseCard{
    public static final String ID = makeID(Meditate.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int TURN_CHANGE = 3;
    private static final int TURN_CHANGE_UPG = 2;
    public Meditate() {
        super(ID, info);
        setMagic(TURN_CHANGE, TURN_CHANGE_UPG);
        this.exhaust = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new TurnShiftAction(magicNumber));
    }
    @Override
    public AbstractCard makeCopy() {
        return new Meditate();
    }
}
