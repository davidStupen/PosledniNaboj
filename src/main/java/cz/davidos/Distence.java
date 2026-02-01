package cz.davidos;

import com.jme3.scene.Node;

public class Distence {
    private Node rootNode;

    public Distence(Node rootNode) {
        this.rootNode = rootNode;
    }

    public void distenceMon(){
        if (rootNode.getChild("player").getLocalTranslation().distance(rootNode.getChild("monstrum").getLocalTranslation()) < 60){
            System.out.println("active");
        }
    }
}
