package theancientonemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import theancientonemod.actions.LockCardAction;
import theancientonemod.actions.SteelAction;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class CertaintyOfSteel extends BaseCard{
    public static final String ID = makeID(CertaintyOfSteel.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            2//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int UPG_COST = 1;
    private static final int CARD_EFFECT_AMOUNT = 1;
    private static final int ARTIFACT = 1;
    public CertaintyOfSteel() {
        super(ID, info);
        setMagic(CARD_EFFECT_AMOUNT);
        setCustomVar("artifact", ARTIFACT);
        setCostUpgrade(UPG_COST);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0], (cards)->{
                for (AbstractCard c : cards) {
                    addToBot(new SteelAction(c, magicNumber));
                }
            }));
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, customVar("artifact"))));
    }
    @Override
    public AbstractCard makeCopy() {
        return new CertaintyOfSteel();
    }
}
