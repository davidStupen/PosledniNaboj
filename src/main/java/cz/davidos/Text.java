package cz.davidos;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

public class Text {
    public BitmapText shoot(BitmapFont guiFont, AssetManager assetManager, AppSettings settings, Node guiNode, float shoot){
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText countSHoot = new BitmapText(guiFont);
        countSHoot.setText(Float.toString(shoot));
        countSHoot.setColor(ColorRGBA.White);
        countSHoot.setLocalTranslation(25, settings.getHeight(), 0);
        countSHoot.setSize(55);
        guiNode.attachChild(countSHoot);
        return countSHoot;
    }
    public BitmapText warming(BitmapFont guiFont, AssetManager assetManager, AppSettings settings, Node guiNode, int warming){
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText countWarming = new BitmapText(guiFont);
        countWarming.setText(Integer.toString(warming));
        countWarming.setColor(ColorRGBA.White);
        countWarming.setLocalTranslation(25, settings.getHeight() - 50, 0);
        countWarming.setSize(55);
        guiNode.attachChild(countWarming);
        return countWarming;
    }
}
