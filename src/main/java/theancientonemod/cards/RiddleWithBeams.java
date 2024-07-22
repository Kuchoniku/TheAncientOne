package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.PlayAutobeamsAction;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class RiddleWithBeams extends BaseCard{
    public static final String ID = makeID(RiddleWithBeams.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            2//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BEAMS = 4;
    private static final int UPG_BEAMS = 1;
    public RiddleWithBeams() {
        super(ID, info);
        setMagic(BEAMS, UPG_BEAMS);
        tags.add(AncientOneTags.BEAM);
        Autobeam a = new Autobeam();
        a.upgrade();
        this.cardsToPreview = a;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PlayAutobeamsAction(this, true, magicNumber));
    }
    @Override
    public AbstractCard makeCopy() {
        return new RiddleWithBeams();
    }
}
