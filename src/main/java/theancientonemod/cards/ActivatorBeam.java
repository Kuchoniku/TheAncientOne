package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.PlayAutobeamsAction;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class ActivatorBeam extends BaseCard{
    public static final String ID = makeID(ActivatorBeam.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 2;
    private static final int BEAMS = 2;
    private static final int BEAMS_UPG = 1;
    public ActivatorBeam() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(BEAMS, BEAMS_UPG);
        tags.add(AncientOneTags.BEAM);
        this.cardsToPreview = new Autobeam();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new PlayAutobeamsAction(this, upgraded, magicNumber));
    }
    @Override
    public AbstractCard makeCopy() {
        return new ActivatorBeam();
    }
    public void upgrade() {
        super.upgrade();
        Autobeam a = new Autobeam();
        a.upgrade();
        this.cardsToPreview = a;
    }
}
