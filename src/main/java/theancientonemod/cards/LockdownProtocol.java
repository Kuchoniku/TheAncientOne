package theancientonemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class LockdownProtocol extends BaseCard{
    public static final String ID = makeID(LockdownProtocol.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int INTANGIBLE = 1;
    private static final int LOCK = 2;
    private static final int LOCK_UPG = -1;
    public LockdownProtocol() {
        super(ID, info);
        setMagic(INTANGIBLE);
        setCustomVar("lock", LOCK, LOCK_UPG);
        setExhaust(true);
        setSelfRetain(false, true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c : p.hand.group) {
            LockAmountFieldPatch.changeLockAmount(c, customVar("lock"));
        }
        addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, magicNumber)));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return super.canUse(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        return new LockdownProtocol();
    }
}
