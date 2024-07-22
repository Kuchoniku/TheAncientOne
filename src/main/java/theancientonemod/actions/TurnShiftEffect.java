package theancientonemod.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BattleStartEffect;

import java.util.Iterator;

import static com.megacrit.cardcrawl.vfx.PlayerTurnEffect.getOrdinalNaming;

public class TurnShiftEffect extends AbstractGameEffect {
    private Color bgColor;
    private Color color;
    private String turnMessage;
    private static final float DUR = 2.0F;
    private static final float HEIGHT_DIV_2;
    private static final float WIDTH_DIV_2;
    private static final float TARGET_HEIGHT;
    private static final float BG_RECT_EXPAND_SPEED = 3.0F;
    private float currentHeight = 0.0F;
    private static final float MAIN_MSG_OFFSET_Y;
    private static final float TURN_MSG_OFFSET_Y;
    private Color turnMessageColor = new Color(0.7F, 0.7F, 0.7F, 0.0F);

    public TurnShiftEffect() {
        this.duration = 2.0F;
        this.bgColor = new Color(AbstractDungeon.fadeColor.r / 2.0F, AbstractDungeon.fadeColor.g / 2.0F, AbstractDungeon.fadeColor.b / 2.0F, 0.0F);
        this.color = Settings.GOLD_COLOR.cpy();
        this.color.a = 0.0F;
        this.scale=Settings.scale;
        if (Settings.usesOrdinal) {
            this.turnMessage = Integer.toString(GameActionManager.turn) + getOrdinalNaming(GameActionManager.turn) + BattleStartEffect.TURN_TXT;
        } else if (Settings.language == Settings.GameLanguage.VIE) {
            this.turnMessage = BattleStartEffect.TURN_TXT + " " + Integer.toString(GameActionManager.turn);
        } else {
            this.turnMessage = Integer.toString(GameActionManager.turn) + BattleStartEffect.TURN_TXT;
        }
    }
    @Override
    public void update() {
        if (this.duration > 1.5F) {
            this.currentHeight = MathUtils.lerp(this.currentHeight, TARGET_HEIGHT, Gdx.graphics.getDeltaTime() * 3.0F);
        } else if (this.duration < 0.5F) {
            this.currentHeight = MathUtils.lerp(this.currentHeight, 0.0F, Gdx.graphics.getDeltaTime() * 3.0F);
        }

        if (this.duration > 1.5F) {
            this.scale = Interpolation.exp10In.apply(1.0F, 3.0F, (this.duration - 1.5F) * 2.0F);
            this.color.a = Interpolation.exp10In.apply(1.0F, 0.0F, (this.duration - 1.5F) * 2.0F);
        } else if (this.duration < 0.5F) {
            this.scale = Interpolation.pow3In.apply(0.9F, 1.0F, this.duration * 2.0F);
            this.color.a = Interpolation.pow3In.apply(0.0F, 1.0F, this.duration * 2.0F);
        }

        this.bgColor.a = this.color.a * 0.75F;
        this.turnMessageColor.a = this.color.a;
        if (Settings.FAST_MODE) {
            this.duration -= Gdx.graphics.getDeltaTime()*0.5F;
        }

        this.duration -= Gdx.graphics.getDeltaTime()*0.5F;
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (!this.isDone) {
            sb.setColor(this.bgColor);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, HEIGHT_DIV_2 - this.currentHeight / 2.0F, (float)Settings.WIDTH, this.currentHeight);
            sb.setBlendFunction(770, 1);
            FontHelper.renderFontCentered(sb, FontHelper.bannerNameFont, BattleStartEffect.PLAYER_TURN_MSG, WIDTH_DIV_2, HEIGHT_DIV_2 + MAIN_MSG_OFFSET_Y, this.color, this.scale);
            FontHelper.renderFontCentered(sb, FontHelper.turnNumFont, this.turnMessage, WIDTH_DIV_2, HEIGHT_DIV_2 + TURN_MSG_OFFSET_Y, this.turnMessageColor, this.scale);
            sb.setBlendFunction(770, 771);
        }
    }

    @Override
    public void dispose() {

    }

    static {
        HEIGHT_DIV_2 = (float)Settings.HEIGHT * 0.75F;
        WIDTH_DIV_2 = (float)Settings.WIDTH / 2.0F;
        TARGET_HEIGHT = 150.0F * Settings.scale;
        MAIN_MSG_OFFSET_Y = 20.0F * Settings.scale;
        TURN_MSG_OFFSET_Y = -30.0F * Settings.scale;
    }
}
