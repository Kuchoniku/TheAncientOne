package theancientonemod.spirePatches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theancientonemod.cards.Autobeam;

public class BeamPlayPatch {
    @SpirePatch(
            clz = GameActionManager.class,
            method = SpirePatch.CLASS
    )
    public static class BeamPlayFieldPatch {
        public static SpireField<Integer> beamsPlayed = new SpireField<>(()->0);
        public static void increment (GameActionManager g) {
            BeamPlayFieldPatch.beamsPlayed.set(g, BeamPlayFieldPatch.beamsPlayed.get(g)+1);
        }
    }
    @SpirePatch(
            clz = GameActionManager.class,
            method = SpirePatch.CLASS
    )
    public static class AutobeamPlayFieldPatch {
        public static SpireField<Integer> autobeamsPlayed = new SpireField<>(()->0);
        public static void increment (GameActionManager g) {
            AutobeamPlayFieldPatch.autobeamsPlayed.set(g, AutobeamPlayFieldPatch.autobeamsPlayed.get(g)+1);
        }
    }
    @SpirePatch(
            clz = GameActionManager.class,
            method = "clear"
    )
    public static class BeamPlayResetPatch {
        @SpirePostfixPatch
        public static void Prefix (GameActionManager ___instance) {
            BeamPlayFieldPatch.beamsPlayed.set(___instance, 0);
            AutobeamPlayFieldPatch.autobeamsPlayed.set(___instance, 0);
        }
    }
    @SpirePatch(
            clz = UseCardAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCard.class, AbstractCreature.class}
    )
    public static class BeamPlayIncrementPatch {
        @SpirePostfixPatch
        public static void Postfix (UseCardAction ___instance, AbstractCard card, AbstractCreature target) {
            if (card.hasTag(AncientOneTags.BEAM)) {
                BeamPlayFieldPatch.increment(AbstractDungeon.actionManager);
                if (card instanceof Autobeam) {
                    AutobeamPlayFieldPatch.increment(AbstractDungeon.actionManager);
                }
            }
        }
    }
}
