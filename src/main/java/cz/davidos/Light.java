package cz.davidos;

import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class Light {
    private boolean create = false;
    private PointLight pointLight = null;
    public void lightPlayer(Vector3f posPlayer, Node rootNode){
        if (!create){
            pointLight = new PointLight(posPlayer.add(0,2,0), ColorRGBA.White, 25);
            rootNode.addLight(pointLight);
            create = true;
        }
        pointLight.setPosition(posPlayer.add(0,2,0));
    }
}
