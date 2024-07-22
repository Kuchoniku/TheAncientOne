package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theancientonemod.spirePatches.BeamPlayPatch;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class VentHeat extends BaseCard{
    public static final String ID = makeID(VentHeat.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.ATTACK,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.ENEMY,//Look at other cards for different targets
            2//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int DAMAGE = 5;
    private static final int INCREASE = 1;
    private static final int INCREASE_UPG = 1;
    public VentHeat() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(INCREASE, INCREASE_UPG);
    }
    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        applyPowers();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damage += magicNumber*BeamPlayPatch.BeamPlayFieldPatch.beamsPlayed.get(AbstractDungeon.actionManager);
        this.calculateCardDamage(m);
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE, true));
    }
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += magicNumber*BeamPlayPatch.BeamPlayFieldPatch.beamsPlayed.get(AbstractDungeon.actionManager);
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += magicNumber*BeamPlayPatch.BeamPlayFieldPatch.beamsPlayed.get(AbstractDungeon.actionManager);;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
    @Override
    public AbstractCard makeCopy() {
        return new VentHeat();
    }
}
