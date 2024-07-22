package theancientonemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Key extends BaseCard{
    public static final String ID = makeID(Key.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.BASIC,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int UNLOCK = 1;
    private static final int UPG_COST = 0;
    public Key() {
        super(ID, info);
        setMagic(UNLOCK);
        setCostUpgrade(UPG_COST);
        setSelfRetain(true);
        tags.add(AncientOneTags.UNLOCKABLE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new UnlockHandAction(p, magicNumber));
    }
    @Override
    public AbstractCard makeCopy() {
        return new Key();
    }
}
