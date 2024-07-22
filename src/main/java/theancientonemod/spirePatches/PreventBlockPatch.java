package theancientonemod.spirePatches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theancientonemod.powers.BasePower;

import java.util.ArrayList;
import java.util.Iterator;

public class PreventBlockPatch {
    @SpirePatch(
            clz= AbstractCreature.class,
            method = "addBlock",
            paramtypez = {int.class}
    )
    public static class onGainedBlockPatch {
        @SpireInsertPatch(
                rloc=1,
                localvars = {"tmp"}
        )
        public static void Insert (AbstractCreature ___instance, int blockAmount, @ByRef float tmp[]) {
            for (AbstractPower p : ___instance.powers) {
                if (p instanceof BasePower) {
                    tmp[0]=((BasePower)p).newOnGainedBlock(tmp[0]);
                }
            }
        }
    }
//    public static class Locator extends SpireInsertLocator {
//
//        @Override
//        public int[] Locate(CtBehavior ctaddBlock) throws CannotCompileException, PatchingException {
//            Matcher finalMatcher = new Matcher.FieldAccessMatcher(Boolean.class, "isPlayer");
//            return LineFinder.findInOrder(ctaddBlock, new ArrayList<Matcher>(), finalMatcher);
//        }
//    }
}
