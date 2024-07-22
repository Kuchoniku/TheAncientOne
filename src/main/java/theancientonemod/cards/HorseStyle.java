package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.powers.BlockOnLockPower;
import theancientonemod.powers.BlockPerCardPlayNextTurnPower;
import theancientonemod.powers.DmgPerCardPlayNextTurnPower;
import theancientonemod.powers.StrOnLockPower;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class HorseStyle extends BaseCard{
    public static final String ID = makeID(HorseStyle.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            2//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DMG_AMOUNT = 2;
    private static final int DMG_UPG = 1;
    private static final int BLOCK_AMT = 2;
    public HorseStyle() {
        super(ID, info);
        setMagic(DMG_AMOUNT, DMG_UPG);
        setCustomVar("block", BLOCK_AMT);
        tags.add(AncientOneTags.STYLE);
        this.exhaust = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DmgPerCardPlayNextTurnPower(p,magicNumber)));
        if (upgraded) {
        addToBot(new ApplyPowerAction(p, p, new BlockPerCardPlayNextTurnPower(p,customVar("block"))));
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new HorseStyle();
    }
}
