package theancientonemod.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theancientonemod.actions.TurnShiftAction;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class Unchain extends BaseCard{
    public static final String ID = makeID(Unchain.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.UNCOMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int STR_LOST = 5;
    public Unchain() {
        super(ID, info);
        setCustomVar("strLost", STR_LOST);
        this.exhaust=true;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        magicNumber = GameActionManager.turn;
        isMagicNumberModified = magicNumber!=baseMagicNumber;
        if (upgraded) {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        } else {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyPowers();
        AbstractCreature target = p;
        if (upgraded) {
            target = SelfOrEnemyTargeting.getTarget(this);
        }
        addToBot(new ApplyPowerAction(target, p, new StrengthPower(target,-customVar("strLost"))));
        addToBot(new ApplyPowerAction(target, p, new StrengthPower(target,magicNumber)));
    }
    @Override
    public void upgrade() {
        super.upgrade();
        target = SelfOrEnemyTargeting.SELF_OR_ENEMY;
    }
    @Override
    public AbstractCard makeCopy() {
        return new Unchain();
    }
}
