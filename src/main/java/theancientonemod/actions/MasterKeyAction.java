package theancientonemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theancientonemod.cards.Autobeam;

public class MasterKeyAction extends AbstractGameAction {
    private boolean freeToPlayOnce = false;
    private AbstractPlayer p;
    private int energyOnUse = -1;
    private int refund;
    private boolean upgraded;
    public MasterKeyAction(AbstractPlayer p, int energyOnUse, int refund, boolean upgraded, boolean freeToPlayOnce) {
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.p = p;
        this.refund=refund;
        this.upgraded=upgraded;
    }
    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }
        if (freeToPlayOnce) {
            refund=0;
        } else {
            if (effect<refund) {
                refund=effect;
            }
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        if (upgraded) {
            effect++;
        }
        if (effect > 0) {
            addToBot(new UnlockHandAction(p, effect));
            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
            addToBot(new GainEnergyAction(refund));
        }
        this.isDone=true;
    }
}
