package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import theancientonemod.actions.PlayAutobeamsAction;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class OrbitalBeam extends BaseCard{
    public static final String ID = makeID(OrbitalBeam.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.NONE,//Look at other cards for different targets
            3//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 27;
    private static final int UPG_DAMAGE = 7;
    private static final int BEAMS = 3;
    private static final int BEAMS_UPG = 1;
    public OrbitalBeam() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(BEAMS, BEAMS_UPG);
        tags.add(AncientOneTags.BEAM);
        this.cardsToPreview = new Autobeam(true);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster target = null;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (target!=null) {
                if (mo.currentHealth>target.currentHealth) {
                    target=mo;
                }
            } else {
                target=mo;
            }
        }
        if (target != null) {
            this.addToBot(new VFXAction(new WeightyImpactEffect(target.hb.cX, target.hb.cY)));
        }
        this.addToBot(new WaitAction(0.8F));
        addToBot(new DamageAction(target, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new PlayAutobeamsAction(this, upgraded, magicNumber));
    }
    @Override
    public AbstractCard makeCopy() {
        return new OrbitalBeam();
    }
    public void upgrade() {
        super.upgrade();
        Autobeam a = new Autobeam(true);
        a.upgrade();
        this.cardsToPreview = a;
    }
}
