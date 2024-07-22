package theancientonemod.spirePatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import theancientonemod.ancientOneMod;

@SpirePatch(
        clz= AbstractCard.class,
        method="canUse"
)

public class LockPatch {
    public static SpireReturn<Boolean> Prefix(AbstractCard ___instance) {
        if ((LockAmountFieldPatch.lockAmount.get(___instance)>0)&&!(___instance.hasTag(AncientOneTags.UNLOCKABLE))) {
            ___instance.cantUseMessage = CardCrawlGame.languagePack.getUIString(ancientOneMod.makeID("CantPlayLocked")).TEXT[0];
            return SpireReturn.Return(false);
        }
       return SpireReturn.Continue();
    }
}
