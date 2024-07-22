package theancientonemod.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theancientonemod.actions.PlayAutobeamsAction;
import theancientonemod.actions.ShowCardEffect;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.spirePatches.BeamPlayPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class AThousandBrilliantBeams extends BaseCard{
    public static final String ID = makeID(AThousandBrilliantBeams.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BEAMS = 2;
    public AThousandBrilliantBeams() {
        super(ID, info);
        setCustomVar("beams", BEAMS);
        exhaust = true;
        tags.add(AncientOneTags.BEAM);
        this.cardsToPreview = new Autobeam();
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        applyPowers();
    }

    public void applyPowers() {
        magicNumber = (int)(BeamPlayPatch.AutobeamPlayFieldPatch.autobeamsPlayed.get(AbstractDungeon.actionManager)/customVar("beams"));
        isMagicNumberModified = magicNumber!=baseMagicNumber;
        if (upgraded) {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        initializeDescription();
        super.applyPowers();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyPowers();
        addToBot(new PlayAutobeamsAction(this, upgraded, magicNumber));
    }
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }
    @Override
    public AbstractCard makeCopy() {
        return new AThousandBrilliantBeams();
    }
    public void upgrade() {
        super.upgrade();
        Autobeam a = new Autobeam();
        a.upgrade();
        this.cardsToPreview = a;
    }
}
