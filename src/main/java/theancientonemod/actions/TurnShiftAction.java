package theancientonemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.StoneCalendar;
import theancientonemod.cards.BaseCard;
import theancientonemod.powers.BasePower;
import theancientonemod.relics.BaseRelic;

public class TurnShiftAction extends AbstractGameAction {
    private int givenamount;
    private boolean timeSlip;
    public TurnShiftAction(int givenamount, boolean timeSlip) {
        this.givenamount=givenamount;
        this.timeSlip=timeSlip;
    }
    public TurnShiftAction(int givenamount) {
        this.givenamount=givenamount;
    }
    @Override
    public void update() {
        int amount = givenamount;
        if (-amount>=GameActionManager.turn) {
            amount=1-GameActionManager.turn;
        }
        GameActionManager.turn+=amount;
        if (amount!=0) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c instanceof BaseCard) {
                    ((BaseCard) c).onTurnShift();
                }
            }
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                for (AbstractPower p : m.powers) {
                    if (p instanceof BasePower) {
                        ((BasePower) p).turnShifted(amount, timeSlip);
                    }
                }
            }
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof BasePower) {
                    ((BasePower) p).turnShifted(amount, timeSlip);
                }
            }
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if (r instanceof BaseRelic) {
                    ((BaseRelic) r).onTurnShifted(amount);
                } else if (r instanceof StoneCalendar) {
                    r.atBattleStart();
                }
            }
        }
        AbstractDungeon.effectList.add(new TurnShiftEffect());
        this.isDone = true;
    }
}
