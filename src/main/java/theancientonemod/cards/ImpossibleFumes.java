package theancientonemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theancientonemod.powers.BleedPower;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

import java.util.Iterator;

public class ImpossibleFumes extends BaseCard{
    public static final String ID = makeID(ImpossibleFumes.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            2//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 2;
    private static final int WEAK = 1;
    private static final int WEAK_UPG = 1;
    private static final int BLEED = 2;
    private static final int BLEED_UPG = 1;
    public ImpossibleFumes() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(WEAK, WEAK_UPG);
        setCustomVar("bleed", BLEED, BLEED_UPG);
        this.exhaust=true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();
            addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, magicNumber, false)));
            addToBot(new ApplyPowerAction(mo, p, new BleedPower(mo, customVar("bleed"))));
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new ImpossibleFumes();
    }
}
