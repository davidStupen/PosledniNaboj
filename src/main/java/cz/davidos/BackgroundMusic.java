package cz.davidos;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.scene.Node;

public class BackgroundMusic {
    private AudioNode first;
    private AudioNode second;
    private AudioNode third;
    private Node rootNode;

    private boolean secondTrigger = false;
    private float timerSecond;
    private boolean firstTrigger = false;
    private float timerFirst;
    public BackgroundMusic(Node rootNode) {
        this.rootNode = rootNode;
    }

    public void initAudio(AssetManager assetManager){
        first = new AudioNode(assetManager, "sound/background/background-melody1.wav");
        first.setPositional(false);
        first.setLooping(false);
        first.setVolume(0.4f);
        rootNode.attachChild(first);

        second = new AudioNode(assetManager, "sound/background/backgroun-melody2.wav");
        second.setPositional(false);
        second.setLooping(false);
        second.setVolume(0.01f);
        rootNode.attachChild(second);

        third = new AudioNode(assetManager, "sound/background/background-melody3.wav");
        third.setPositional(false);
        third.setLooping(false);
        third.setVolume(0.2f);
        rootNode.attachChild(third);
    }
    public void playBackgroundAudio(){
        if (!secondTrigger){
            second.play();
            secondTrigger = true;
        }
        timerSecond = second.getPlaybackTime();
        timerFirst = first.getPlaybackTime();
        if (timerSecond > 27 && !firstTrigger){
            first.play();
            firstTrigger = true;
        }
        if (timerFirst > 178){
            third.play();
        }
    }
}
