package cz.davidos;

import com.jme3.scene.Node;

public class Distence {
    private Node rootNode;
    private int contWarming = 0;
    private long priviousTime = System.currentTimeMillis();

    public Distence(Node rootNode) {
        this.rootNode = rootNode;
    }

    public void distenceMon(){
        if (rootNode.getChild("player").getLocalTranslation().distance(rootNode.getChild("monstrum").getLocalTranslation()) < 60){
            long now = System.currentTimeMillis();
            if (priviousTime < now - 4000){
                contWarming++;
                priviousTime = now;
            }
        }
    }

    public int getContWarming() {
        return contWarming;
    }
}
