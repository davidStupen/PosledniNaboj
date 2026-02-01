package cz.davidos;

import com.jme3.scene.Node;

public class Distence {
    private Node rootNode;
    private EffectSound effectSound;
    private int contWarming = 0;
    private long priviousTime = System.currentTimeMillis();

    public Distence(Node rootNode, EffectSound effectSound) {
        this.rootNode = rootNode;
        this.effectSound = effectSound;
    }

    public void distenceMon(){
        if (rootNode.getChild("monstrum") == null){
            return;
        }
        if (rootNode.getChild("player").getLocalTranslation().distance(rootNode.getChild("monstrum").getLocalTranslation()) < 60){
            long now = System.currentTimeMillis();
            if (priviousTime < now - 6000){
                contWarming++;
                priviousTime = now;
                effectSound.playWarming();
            }
        }
    }

    public int getContWarming() {
        return contWarming;
    }
}
