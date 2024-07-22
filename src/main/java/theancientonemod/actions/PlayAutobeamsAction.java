package theancientonemod.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theancientonemod.cards.AThousandBrilliantBeams;
import theancientonemod.cards.Autobeam;
import theancientonemod.cards.OrbitalBeam;
import theancientonemod.relics.SpareBattery;

public class PlayAutobeamsAction extends AbstractGameAction {
    AbstractCard card;
    boolean upgraded;
    int amount;
    boolean brilliantBeams;
    boolean orbital;
    public PlayAutobeamsAction(AbstractCard card, boolean upgraded, int amount) {
        this.card=card;
        this.upgraded=upgraded;
        this.amount=amount;
    }
    @Override
    public void update() {
        int newAmount = amount;
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof SpareBattery) {
                newAmount++;
                r.flash();
            }
        }
        if (card instanceof AThousandBrilliantBeams) {
            brilliantBeams = true;
        }
        if (card instanceof OrbitalBeam) {
            orbital = true;
        }
        for (int i = 0; i<newAmount; i++) {
            Autobeam a = new Autobeam(orbital);
            if(upgraded) {
                a.upgrade();
            }
            a.purgeOnUse = true;
            if (brilliantBeams) {
                float x = MathUtils.random(0.2F, 0.8F) * (float) Settings.WIDTH;
                float y = MathUtils.random(0.3F, 0.7F) * (float)Settings.HEIGHT;
                AbstractDungeon.effectList.add(new ShowCardEffect(a, x, y));
            }
            addToBot(new NewQueueCardAction(a, true, false, true));
        }
        this.isDone = true;
    }
}
