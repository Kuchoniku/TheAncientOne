package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;
import theancientonemod.actions.LockCardAction;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.spirePatches.LockAmountFieldPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

import java.util.Iterator;

public class LastResort extends BaseCard{
    public static final String ID = makeID(LastResort.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ALL_ENEMY,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 25;
    private static final int UPG_DAMAGE = 10;
    private static final int LOCK = 6;
    public LastResort() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(LOCK);
        this.isMultiDamage = true;
        LockAmountFieldPatch.lockAmount.set(this, magicNumber);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        addToBot(new LockCardAction(this, magicNumber));
    }
    @Override
    public AbstractCard makeCopy() {
        return new LastResort();
    }
}
