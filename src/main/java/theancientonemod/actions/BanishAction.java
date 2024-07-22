package theancientonemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theancientonemod.ancientOneMod;
import theancientonemod.spirePatches.LockAmountFieldPatch;

import java.util.Iterator;

public class BanishAction extends AbstractGameAction {

    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean upgraded;
    private int amount;
    public BanishAction(boolean upgraded, int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgraded=upgraded;
        this.amount=amount;
    }
    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
            } else if (this.p.hand.size() == 1) {
                if (this.p.hand.getBottomCard().costForTurn == -1) {
                    if (!upgraded) {
                        this.addToTop(new DrawCardAction(EnergyPanel.getCurrentEnergy()));
                        this.addToTop(new GainEnergyAction(EnergyPanel.getCurrentEnergy()));
                    } else {
                        this.addToTop(new DrawCardAction(EnergyPanel.getCurrentEnergy()+1));
                        this.addToTop(new GainEnergyAction(EnergyPanel.getCurrentEnergy()+1));
                    }
                } else if (this.p.hand.getBottomCard().costForTurn > 0) {
                    if (!upgraded) {
                        this.addToTop(new DrawCardAction(this.p.hand.getBottomCard().costForTurn));
                        this.addToTop(new GainEnergyAction(this.p.hand.getBottomCard().costForTurn));
                    } else {
                        this.addToTop(new DrawCardAction(this.p.hand.getBottomCard().costForTurn+1));
                        this.addToTop(new GainEnergyAction(this.p.hand.getBottomCard().costForTurn+1));
                    }
                }
                LockAmountFieldPatch.changeLockAmount(p.hand.getBottomCard(), amount);
                this.tickDuration();
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractCard c;
                for(Iterator var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); var1.hasNext();p.hand.addToTop(c)) {
                    c = (AbstractCard)var1.next();
                    if (c.costForTurn == -1) {
                        if (!upgraded) {
                            this.addToTop(new DrawCardAction(EnergyPanel.getCurrentEnergy()));
                            this.addToTop(new GainEnergyAction(EnergyPanel.getCurrentEnergy()));
                        } else {
                            this.addToTop(new DrawCardAction(EnergyPanel.getCurrentEnergy()+1));
                            this.addToTop(new GainEnergyAction(EnergyPanel.getCurrentEnergy()+1));
                        }
                    } else if (c.costForTurn > 0) {
                        if (!upgraded) {
                            this.addToTop(new DrawCardAction(c.costForTurn));
                            this.addToTop(new GainEnergyAction(c.costForTurn));
                        } else {
                            this.addToTop(new DrawCardAction(c.costForTurn+1));
                            this.addToTop(new GainEnergyAction(c.costForTurn+1));
                        }
                    }
                    LockAmountFieldPatch.changeLockAmount(c, amount);
                }

                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            }

            this.tickDuration();
        }
    }
    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(ancientOneMod.makeID("BanishAction"));
        TEXT = uiStrings.TEXT;
    }
}
