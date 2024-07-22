package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import theancientonemod.actions.MonkeyStyleAction;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class MonkeyStyle extends BaseCard{
    public static final String ID = makeID(MonkeyStyle.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            -1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BLUR = 2;
    private static final int BLUR_UPG = 1;
    private static final int BEAMS = 2;
    public MonkeyStyle() {
        super(ID, info);
        setMagic(BLUR, BLUR_UPG);
        setCustomVar("beams", BEAMS);
        this.exhaust=true;
        tags.add(AncientOneTags.STYLE);
        this.cardsToPreview = new Autobeam();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BlurPower(p, magicNumber)));
        addToBot(new MonkeyStyleAction(p, this.energyOnUse, customVar("beams"), upgraded, freeToPlayOnce, this));

    }
    @Override
    public AbstractCard makeCopy() {
        return new MonkeyStyle();
    }
    public void upgrade() {
        super.upgrade();
        Autobeam a = new Autobeam();
        a.upgrade();
        this.cardsToPreview = a;
    }
}
