package theancientonemod.spirePatches;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.CardModifierPatches;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import org.xml.sax.Locator;
import theancientonemod.theAncientOne.LockIcon;




public class LockAmountRenderPatch {
    @SpirePatch(
            clz= AbstractCard.class,
            method="render",
            paramtypez = {
                    SpriteBatch.class, boolean.class
            }
    )

    public static class SmallLockRenderPatch {
        @SpireInsertPatch(
                rloc = 7
        )
        public static void Insert(AbstractCard ___instance, SpriteBatch sb, boolean selected) {
          if (LockAmountFieldPatch.lockAmount.get(___instance) > 0) {
                ExtraIcons.icon(LockIcon.get().region.getTexture()).text(String.valueOf(LockAmountFieldPatch.lockAmount.get(___instance))).render(___instance);
            }
        }
    }
    @SpirePatch(
            clz= AbstractCard.class,
            method="renderInLibrary",
            paramtypez = {
                    SpriteBatch.class
            }
    )
    public static class LibraryLockRenderPatch {
        @SpireInsertPatch (
                rlocs = {9, 24}
        )
        public static void Insert (AbstractCard ___instance, SpriteBatch sb) {
            if (LockAmountFieldPatch.lockAmount.get(___instance) > 0) {
                ExtraIcons.icon(LockIcon.get().region.getTexture()).text(String.valueOf(LockAmountFieldPatch.lockAmount.get(___instance))).render(___instance);
            }
        }
    }
    @SpirePatch(
            clz= SingleCardViewPopup.class,
            method="render",
            paramtypez = {
                    SpriteBatch.class
            }
    )
    public static class PreviewLockRenderPatch {
        @SpireInsertPatch (
                rloc = 26
        )
        public static void Insert (SingleCardViewPopup ___instance, SpriteBatch sb, AbstractCard ___card) {
            if (LockAmountFieldPatch.lockAmount.get(___card) > 0) {
                ExtraIcons.icon(LockIcon.get().region.getTexture()).text(String.valueOf(LockAmountFieldPatch.lockAmount.get(___card))).render(___card);
            }
        }
    }
}

