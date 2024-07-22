package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.LockCardAction;
import theancientonemod.actions.TurnShiftAction;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Jailbreak extends BaseCard{
    public static final String ID = makeID(Jailbreak.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            0//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int LOCK = 4;
    private static final int UPG_LOCK = -1;
    private static final int ENERGY = 2;
    private static final int CARDS = 2;
    private static final int UPG_CARDS = 1;
    public Jailbreak() {
        super(ID, info);
        setMagic(LOCK, UPG_LOCK);
        setCustomVar("energy", ENERGY);
        setCustomVar("cards", CARDS, UPG_CARDS);
        LockAmountFieldPatch.lockAmount.set(this, magicNumber);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(customVar("energy")));
        addToBot(new DrawCardAction(p, customVar("cards"), false));
        addToBot(new LockCardAction(this, magicNumber));
    }
    @Override
    public void upgrade() {
        super.upgrade();
        LockAmountFieldPatch.lockAmount.set(this, magicNumber);
    }
    @Override
    public AbstractCard makeCopy() {
        return new Jailbreak();
    }
}
