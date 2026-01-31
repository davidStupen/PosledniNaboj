package cz.davidos.player;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class Player {
    private RigidBodyControl playerPhy;
    private float countShoot;

    public Player() {
        this.countShoot = 100;
    }

    public void createPlayer(AssetManager assetManager, BulletAppState bulletAppState, Node rootNode, InputManager inputManager, Camera cam){
        Node player = new Node("player");
        Spatial playerObj = assetManager.loadModel("models/player.glb");
        playerObj.setName("player");
        playerObj.setLocalScale(0.5f, 0.5f, 0.5f);
        player.attachChild(playerObj);
        CameraNode cameraPlayer = new CameraNode("cam", cam);
        cameraPlayer.setLocalTranslation(playerObj.getWorldTranslation().add(new Vector3f(0,2,-2)));
        cameraPlayer.lookAt(playerObj.getWorldTranslation().add(new Vector3f(0,1,3)), Vector3f.UNIT_Y);
        player.attachChild(cameraPlayer);
        rootNode.attachChild(player);
        this.playerPhy = new RigidBodyControl(1);
        player.addControl(this.playerPhy);
        bulletAppState.getPhysicsSpace().add(this.playerPhy);
        this.playerPhy.setAngularFactor(0);
        this.playerPhy.setCcdMotionThreshold(0.001f);
        this.playerPhy.setCcdSweptSphereRadius(0.5f);
        this.initKey(inputManager);
    }
    private void initKey(InputManager inputManager){
        inputManager.addMapping("forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("back", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("mouseLeft", new MouseAxisTrigger(0, false));
        inputManager.addMapping("mouseRight", new MouseAxisTrigger(0, true));
        inputManager.addListener(analogListener, "forward", "back", "mouseLeft", "mouseRight");
    }
    private final AnalogListener analogListener = (String var1, float var2, float var3) -> {
        if (var1.equals("forward")){
            Vector3f dir = playerPhy.getPhysicsRotation().mult(Vector3f.UNIT_Z);
            playerPhy.applyCentralForce(dir.mult(10));
        }
        if (var1.equals("back")){
            Vector3f dir = playerPhy.getPhysicsRotation().mult(Vector3f.UNIT_Z);
            playerPhy.applyCentralForce(dir.mult(-10));
        }
        if (var1.equals("mouseLeft")){
            playerPhy.setPhysicsRotation(playerPhy.getPhysicsRotation().mult(new Quaternion().fromAngles(0,-0.02f, 0)));
        }
        if (var1.equals("mouseRight")){
            playerPhy.setPhysicsRotation(playerPhy.getPhysicsRotation().mult(new Quaternion().fromAngles(0,0.02f,0)));
        }
    };

    public RigidBodyControl getPlayerPhy() {
        return playerPhy;
    }
}
