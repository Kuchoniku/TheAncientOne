package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class SteelSkin extends BaseCard{
    public static final String ID = makeID(SteelSkin.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 2;
    private static final int RETAIN_BLOCK = 3;
    private static final int RETAIN_BLOCK_UPG = 1;
    public SteelSkin() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(RETAIN_BLOCK, RETAIN_BLOCK_UPG);
        this.selfRetain = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }
    public void onRetained() {
        addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, magicNumber));
    }
    @Override
    public AbstractCard makeCopy() {
        return new SteelSkin();
    }
}
