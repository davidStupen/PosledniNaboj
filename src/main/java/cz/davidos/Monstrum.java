package cz.davidos;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class Monstrum {
    private boolean createMonstrum = false;
    private RigidBodyControl monstrumPhy;
    private long privious = System.currentTimeMillis();
    private int priviousRanNum = 0;

    private void createMon(AssetManager assetManager, BulletAppState bulletAppState, Node rootNode){
        Box box = new Box(1,1,1);
        Geometry geometry = new Geometry("monstrum", box);
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Brown);
        geometry.setMaterial(material);
        rootNode.attachChild(geometry);
        monstrumPhy = new RigidBodyControl(1);
        geometry.addControl(monstrumPhy);
        bulletAppState.getPhysicsSpace().add(monstrumPhy);
        monstrumPhy.setPhysicsLocation(new Vector3f(0,10,0));
    }
    public void moveMonster(AssetManager assetManager, BulletAppState bulletAppState, Node rootNode){
        if (!createMonstrum){
            createMon(assetManager, bulletAppState, rootNode);
            createMonstrum = true;
        }
        long now = System.currentTimeMillis();
        if (privious < now - 500){
            privious = now;
            int ranNumForDir = (int) (Math.random() * 4) + 1;
            while (ranNumForDir == priviousRanNum){
                ranNumForDir = (int) (Math.random() * 4) + 1;
            }
            priviousRanNum = ranNumForDir;
            Vector3f dir = monstrumPhy.getPhysicsRotation().mult(Vector3f.UNIT_Z);
            switch (ranNumForDir){
                case 1 -> monstrumPhy.applyCentralForce(dir.mult(300));
                case 2 -> monstrumPhy.applyCentralForce(dir.mult(-300));
                case 3 -> monstrumPhy.setPhysicsRotation(new Quaternion().fromAngles(0,100,0));
                case 4 -> monstrumPhy.setPhysicsRotation(new Quaternion().fromAngles(0,-100,0));
            }
            System.out.println(ranNumForDir);
        }
    }
}
