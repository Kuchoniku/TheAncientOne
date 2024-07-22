package theancientonemod.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import theancientonemod.powers.ClockCircuitPower;
import theancientonemod.powers.ReadyPositionPower;
import theancientonemod.powers.UpgradedClockCircuitPower;
import theancientonemod.spirePatches.AncientOneTags;
import theancientonemod.theAncientOne.TheAncientOne;
import theancientonemod.util.CardStats;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ReadyPosition extends BaseCard{
    public static final String ID = makeID(ReadyPosition.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheAncientOne.Meta.CARD_COLOR,//Card Color, just copy this
            CardType.POWER,//Card Type, change for skill/power/stuff
            CardRarity.RARE,// Card Rarity, BASIC/COMMON/UNCOMMON/RARE/SPECIAL/CURSE
            CardTarget.SELF,//Look at other cards for different targets
            3//Energy Cost, -1 for X cost, -2 for Unplayable
    );
    private static final int CARDS = 1;
    private static final int CARDS_UPG = 1;
    private static final float PREVIEW_TIMER = 2f;
    private float timer = 0F;
    CardGroup cards;
    public ReadyPosition() {
        super(ID, info);
        setMagic(CARDS, CARDS_UPG);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new ApplyPowerAction(p, p, new ReadyPositionPower(p,magicNumber)));
    }
    @Override
    public AbstractCard makeCopy() {
        return new ReadyPosition();
    }
    public void update() {
        super.update();
        timer-= Gdx.graphics.getDeltaTime();
        if (timer<=0) {
            timer = PREVIEW_TIMER;
            if (cards != null) {
                cards.clear();
            }
            cards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            Iterator var3 = CardLibrary.cards.entrySet().iterator();
            while (var3.hasNext()) {
                Map.Entry<String, AbstractCard> c = (Map.Entry) var3.next();
                if (c.getValue().hasTag(AncientOneTags.STYLE)) {
                    cards.addToTop(CardLibrary.cards.get(c.getKey()).makeCopy());
                }
            }
            cardsToPreview=cards.getRandomCard(new Random());
        }
    }



}
