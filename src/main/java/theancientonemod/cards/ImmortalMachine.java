package theancientonemod.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ReaperEffect;
import theancientonemod.powers.ChooseLockStartOfTurnPower;
import theancientonemod.powers.ImmortalMachinePower;
import theancientonemod.powers.RandomLockStartOfTurnPower;
import theancientonemod.powers.UnlockStartOfTurnPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class ImmortalMachine extends BaseCard{
    public static final String ID = makeID(ImmortalMachine.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.POWER,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            3//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BLEED = 4;
    private static final int UPG_COST = 2;
    public ImmortalMachine() {
        super(ID, info);
        setMagic(BLEED);
        setCostUpgrade(UPG_COST);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ImmortalMachinePower(p, magicNumber)));
    }
    @Override
    public AbstractCard makeCopy() {
        return new ImmortalMachine();
    }
}
