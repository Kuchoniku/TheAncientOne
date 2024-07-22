package theancientonemod.theAncientOne;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theancientonemod.ancientOneMod;
import theancientonemod.util.TextureLoader;

public class LockIcon extends AbstractCustomIcon {
    public static final String ID = ancientOneMod.makeID("LockAmount");
    private static LockIcon self;
    public LockIcon() {
        super(ID, TextureLoader.getTexture(ancientOneMod.characterPath("LockIcon.png")));
    }
    public static LockIcon get() {
        if (self == null) {
            return new LockIcon();
        }
        else {
            return self;
        }
    }
}
