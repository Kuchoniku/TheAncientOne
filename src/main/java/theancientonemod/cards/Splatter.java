package theancientonemod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import theancientonemod.powers.BleedPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

import java.util.Iterator;

public class Splatter extends BaseCard{
    public static final String ID = makeID(Splatter.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ENEMY,//Look at other cards for different targets
            2//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int UPG_COST = 1;
    public Splatter() {
        super(ID, info);
        setCostUpgrade(UPG_COST);
        this.exhaust=true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 0;
        if (m.hasPower(BleedPower.POWER_ID)) {
            amount=m.getPower(BleedPower.POWER_ID).amount;
        }
        if (amount!=0) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new ShockWaveEffect(m.hb.cX, m.hb.cY, Color.SCARLET, ShockWaveEffect.ShockWaveType.ADDITIVE), 0.5F));
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToBot(new ApplyPowerAction(mo, p, new BleedPower(mo, amount)));
            }
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new Splatter();
    }
}
