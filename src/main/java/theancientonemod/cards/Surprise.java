package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import theancientonemod.powers.BleedPower;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

import java.util.Iterator;

public class Surprise extends BaseCard{
    public static final String ID = makeID(Surprise.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ENEMY,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DEBUFFS = 5;
    public Surprise() {
        super(ID, info);
        setMagic(DEBUFFS);
        this.upgInnate = true;
    }
    public void applyPowers() {
        super.applyPowers();
        this.magicNumber = baseMagicNumber-GameActionManager.turn;
        if (magicNumber<0) {
            magicNumber=0;
        }
        isMagicNumberModified = magicNumber != baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyPowers();
        if (magicNumber>=3) {
            AbstractDungeon.actionManager.addToBottom(new SFXAction("MONSTER_COLLECTOR_DEBUFF"));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new CollectorCurseEffect(m.hb.cX, m.hb.cY), 2.0F));
        }
        if (magicNumber>0) {
            this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new WeakPower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new VulnerablePower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }

    }

    @Override
    public AbstractCard makeCopy() {
        return new Surprise();
    }
}
