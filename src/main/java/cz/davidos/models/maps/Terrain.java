package cz.davidos.models.maps;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class Terrain {
    public void createTerrain(AssetManager assetManager, BulletAppState bulletAppState, Node rootNode){
        Spatial terrain = assetManager.loadModel("models/simpleWorld.glb");
        terrain.setName("terrain");
        rootNode.attachChild(terrain);
        RigidBodyControl terrainPhy = new RigidBodyControl(0);
        terrain.addControl(terrainPhy);
        bulletAppState.getPhysicsSpace().add(terrainPhy);
        terrainPhy.setPhysicsLocation(new Vector3f(0,-5,0));
    }

}
