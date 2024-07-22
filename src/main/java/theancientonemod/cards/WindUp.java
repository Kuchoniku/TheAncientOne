package theancientonemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theancientonemod.actions.UnlockHandAction;
import theancientonemod.actions.WindUpAction;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

public class WindUp extends BaseCard{
    public static final String ID = makeID(WindUp.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.SKILL,//Card Type, change for skill/power/stuff
            CardRarity.COMMON,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF_AND_ENEMY,//Look at other cards for different targets
            1//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int VIGOR = 3;
    private static final int UPG_VIGOR = 2;
    private static final int VULNERABLE = 1;
    private static final int VULNERABLE_UPG = 1;
    public WindUp() {
        super(ID, info);
        setMagic(VIGOR, UPG_VIGOR);
        setCustomVar("vulnerable", VULNERABLE, VULNERABLE_UPG);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new VigorPower(p, magicNumber)));
        addToBot(new WindUpAction(customVar("vulnerable"), m));
    }
    @Override
    public AbstractCard makeCopy() {
        return new WindUp();
    }
}
