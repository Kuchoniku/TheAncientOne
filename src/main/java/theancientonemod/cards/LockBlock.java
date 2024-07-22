package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.actions.TurnShiftAction;
import theancientonemod.powers.UnlockIfBlockBrokenPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class LockBlock extends BaseCard{
    public static final String ID = makeID(LockBlock.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;
    private static final int UNLOCK = 1;
    public LockBlock() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(UNLOCK);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new UnlockIfBlockBrokenPower(p, magicNumber)));
    }
    @Override
    public AbstractCard makeCopy() {
        return new LockBlock();
    }
}
