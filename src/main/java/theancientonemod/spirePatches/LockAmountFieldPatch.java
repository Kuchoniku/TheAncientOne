package theancientonemod.spirePatches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theancientonemod.cards.BaseCard;
import theancientonemod.powers.BasePower;
import theancientonemod.relics.BaseRelic;

@SpirePatch(
        clz = AbstractCard.class,
        method = SpirePatch.CLASS
)
public class LockAmountFieldPatch {
    public static SpireField<Integer> lockAmount = new SpireField<>(()->0);
    public static void changeLockAmount (AbstractCard card, int amount) {
        int oldAmount = LockAmountFieldPatch.lockAmount.get(card);
        LockAmountFieldPatch.lockAmount.set(card, LockAmountFieldPatch.lockAmount.get(card)+amount);
        if (LockAmountFieldPatch.lockAmount.get(card)<0) {
            LockAmountFieldPatch.lockAmount.set(card, 0);
        }
        if (amount>0) {
            if (card instanceof BaseCard) {
                ((BaseCard) card).onLock();

            }
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof BasePower) {
                    ((BasePower)p).onCardLocked();
                }
            }
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if (r instanceof BaseRelic) {
                    ((BaseRelic)r).onCardLocked();
                }
            }
        }
        else if (amount<0) {
            if (card instanceof BaseCard) {
                ((BaseCard) card).onUnLock();

            }
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof BasePower) {
                    ((BasePower)p).onCardUnlocked();
                }
            }
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if (r instanceof BaseRelic) {
                    ((BaseRelic)r).onCardUnlocked(oldAmount);
                }
            }
        }
    }
    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class LockOnceOnPlayPatch {
        public static SpireField<Integer> lockOnceOnPlay = new SpireField<>(()->0);
    }
    @SpirePatch(
            clz = UseCardAction.class,
            method = "update"
    )
    public static class LockOncePatch {
        @SpireInsertPatch(
                rloc = 74
        )
        public static void Insert (UseCardAction ___instance, AbstractCard ___targetCard) {
            if(LockOnceOnPlayPatch.lockOnceOnPlay.get(___targetCard)>0) {
                LockAmountFieldPatch.changeLockAmount(___targetCard, LockOnceOnPlayPatch.lockOnceOnPlay.get(___targetCard));
            }
            LockOnceOnPlayPatch.lockOnceOnPlay.set(___targetCard, 0);
        }
    }
}
