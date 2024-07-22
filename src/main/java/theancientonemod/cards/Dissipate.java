package theancientonemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Dissipate extends BaseCard{
    public static final String ID = makeID(Dissipate.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            0//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int BLOCK = 2;
    private static final int UPG_BLOCK = 1;
    public Dissipate() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        this.exhaust=true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hand.isEmpty()) {
            addToBot(new SelectCardsInHandAction(p.hand.size(), cardStrings.EXTENDED_DESCRIPTION[0], true, true,
                    (c)-> LockAmountFieldPatch.lockAmount.get(c)>0,
                    (c)-> {
                        for (AbstractCard card : c) {
                            int i = LockAmountFieldPatch.lockAmount.get(card);
                            addToBot(new ExhaustSpecificCardAction(card, p.hand, true));
                            while (i>0) {
                                addToBot(new GainBlockAction(p, p, block, true));
                                i--;
                            }
                        }
                    }
            ));
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new Dissipate();
    }
}
