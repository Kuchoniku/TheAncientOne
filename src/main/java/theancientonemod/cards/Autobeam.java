package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

import static theancientonemod.util.GeneralUtils.removePrefix;
import static theancientonemod.util.TextureLoader.getCardTextureString;

public class Autobeam extends BaseCard{
    public static final String ID = makeID(Autobeam.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.SPECIAL,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ALL_ENEMY,//Look at other cards for different targets
            0//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 1;
    private boolean orbital;
    public Autobeam(boolean orbital) {
        super(ID, info, getCardTextureString("OrbitalAutobeam", info.cardType));
        setDamage(DAMAGE, UPG_DAMAGE);
        tags.add(AncientOneTags.BEAM);
        this.exhaust = true;
        this.orbital = orbital;
    }
    public Autobeam () {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        tags.add(AncientOneTags.BEAM);
        this.exhaust = true;
        this.orbital = false;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
        AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
        if (orbital) {
            float x = (float)(mo.hb.cX+((2*Math.random()-1)*mo.hb.width/2));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmallLaserEffect(x, mo.hb.cY-mo.hb.height/2, x, Settings.HEIGHT), 0.3F));
            addToBot(new DamageAction(mo, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE ));
        } else {
            float x = (float)(p.hb.cX+((2*Math.random()-1)*p.hb.width/2));
            float y = (float)(p.hb.cY+((2*Math.random()-1)*p.hb.height/2));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmallLaserEffect(mo.hb.cX, mo.hb.cY, x, y), 0.3F));
            addToBot(new DamageAction(mo, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY ));
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new Autobeam();
    }
}
