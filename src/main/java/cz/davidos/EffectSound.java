package cz.davidos;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.scene.Node;

public class EffectSound {
    private AudioNode warming;
    private AudioNode shoot;

    private Node rootNode;

    public EffectSound(Node rootNode) {
        this.rootNode = rootNode;
    }
    public void loadEffects(AssetManager assetManager){
        warming = new AudioNode(assetManager, "sound/Effeck/warming.wav");
        warming.setPositional(false);
        warming.setLooping(false);
        warming.setVolume(0.3f);
        rootNode.attachChild(warming);

        shoot = new AudioNode(assetManager, "sound/Effeck/gun.wav");
        shoot.setPositional(false);
        shoot.setLooping(false);
        shoot.setVolume(0.2f);
        rootNode.attachChild(shoot);
    }
    public void playWarming(){
        warming.playInstance();
    }
    public void playShoot(){
        shoot.playInstance();
    }
}
