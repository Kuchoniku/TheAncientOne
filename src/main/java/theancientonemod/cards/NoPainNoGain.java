package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.powers.BlockOnLockPower;
import theancientonemod.powers.DamageOnUnlockPower;
import theancientonemod.powers.DexOnUnlockPower;
import theancientonemod.powers.StrOnLockPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class NoPainNoGain extends BaseCard{
    public static final String ID = makeID(NoPainNoGain.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.POWER,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            2//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 2;
    private static final int STR = 1;
    public NoPainNoGain() {
        super(ID, info);
        setMagic(BLOCK, UPG_BLOCK);
        setCustomVar("Str", STR);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BlockOnLockPower(p,magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new StrOnLockPower(p,customVar("Str"))));
    }
    @Override
    public AbstractCard makeCopy() {
        return new NoPainNoGain();
    }
}
