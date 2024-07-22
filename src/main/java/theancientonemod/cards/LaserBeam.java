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
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

import java.util.Iterator;

public class LaserBeam extends BaseCard{
    public static final String ID = makeID(LaserBeam.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ALL_ENEMY,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;
    private static final int VULNERABLE = 1;
    private static final int VULNERABLE_UPG = 1;
    public LaserBeam() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(VULNERABLE, VULNERABLE_UPG);
        this.isMultiDamage = true;
        tags.add(AncientOneTags.BEAM);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_DEFECT_BEAM"));
        this.addToBot(new VFXAction(p, new SweepingBeamEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractDungeon.player.flipHorizontal), 0.4F));
        addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();
            this.addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new LaserBeam();
    }
}
