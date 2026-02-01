package cz.davidos;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class Shoot {
    public void classicShoot(AssetManager assetManager, Node rootNode, BulletAppState bulletAppState, Vector3f dir, RigidBodyControl playerPhy){
        Spatial geometry = assetManager.loadModel("models/naboj.glb");
        geometry.setName("shoot");
        rootNode.attachChild(geometry);
        RigidBodyControl shootPhy = new RigidBodyControl(0.5f);
        geometry.addControl(shootPhy);
        shootPhy.setPhysicsLocation(playerPhy.getPhysicsLocation().add(new Vector3f(0,1f,1.5f)));
        shootPhy.setLinearVelocity(dir.mult(25));
        bulletAppState.getPhysicsSpace().add(shootPhy);
        shootPhy.setGravity(new Vector3f(0,-10,0));
        shootPhy.setCcdMotionThreshold(0.001f);
        shootPhy.setCcdSweptSphereRadius(0.5f);
        shootPhy.setPhysicsRotation(playerPhy.getPhysicsRotation().mult(new Quaternion().fromAngles(dir.getX(), dir.getY() - 30, dir.getY())));
        shootPhy.setAngularFactor(0);
    }
}
