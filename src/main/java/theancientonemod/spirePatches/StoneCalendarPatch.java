package theancientonemod.spirePatches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.relics.StoneCalendar;

public class StoneCalendarPatch {
    @SpirePatch(
            clz = StoneCalendar.class,
            method = SpirePatch.CLASS
    )
    public static class turnZeroField {
        public static SpireField<Boolean> turnZero = new SpireField<>(()->false);
    }
    @SpirePatch(
            clz = StoneCalendar.class,
            method = "atTurnStart"
    )
    public static class turnStartPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(StoneCalendar ___instance, @ByRef int[] ___counter) {
            StoneCalendarPatch.turnZeroField.turnZero.set(___instance, false);
            if (___instance.grayscale) {
                ___counter[0]++;
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
    @SpirePatch(
            clz = StoneCalendar.class,
            method = "atBattleStart"
    )
    public static class battleStartPatch {
        @SpirePostfixPatch
        public static void Postfix(StoneCalendar ___instance, int ___TURNS, @ByRef int[] ___counter) {
            if (StoneCalendarPatch.turnZeroField.turnZero.get(___instance)) {
                ___counter[0]=0;
            } else {
                ___counter[0] = GameActionManager.turn;
            }
            StoneCalendarPatch.turnZeroField.turnZero.set(___instance, false);
            if (___counter[0] == ___TURNS && !___instance.grayscale) {
                ___instance.beginLongPulse();
            }
        }
    }
    @SpirePatch(
            clz = StoneCalendar.class,
            method = "onPlayerEndTurn"
    )
    public static class endTurnPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(StoneCalendar ___instance, boolean ___grayscale) {
            if (___grayscale) {
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
    @SpirePatch (
            clz = StoneCalendar.class,
            method = "onVictory"
    )
    public static class victoryPatch {
        @SpirePostfixPatch
        public static void Postfix (StoneCalendar ___instance) {
            StoneCalendarPatch.turnZeroField.turnZero.set(___instance, true);
        }
    }
    @SpirePatch (
            clz = StoneCalendar.class,
            method = "justEnteredRoom"
    )
    public static class enterRoomPatch {
        @SpirePostfixPatch
        public static void Postfix (StoneCalendar ___instance) {
            StoneCalendarPatch.turnZeroField.turnZero.set(___instance, true);
        }
    }
}
