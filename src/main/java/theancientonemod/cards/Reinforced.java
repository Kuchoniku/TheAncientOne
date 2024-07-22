package theancientonemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Reinforced extends BaseCard{
    public static final String ID = makeID(Reinforced.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.POWER,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int ARMOR = 3;
    private static final int ARMOR_UPG = 1;
    public Reinforced() {
        super(ID, info);
        setMagic(ARMOR);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, magicNumber)));
    }
    public boolean canUpgrade() {
        return true;
    }
    public void upgrade() {
        this.upgradeMagicNumber(ARMOR_UPG);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }
    @Override
    public AbstractCard makeCopy() {
        return new Reinforced();
    }
}
