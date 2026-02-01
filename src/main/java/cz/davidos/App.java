package cz.davidos;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import cz.davidos.models.maps.Terrain;
import cz.davidos.player.Player;

public class App extends SimpleApplication implements PhysicsCollisionListener {
    private BulletAppState bulletAppState = new BulletAppState();
    private Light light = new Light();
    private Monstrum monstrum = new Monstrum();
    private BackgroundMusic backgroundMusic = new BackgroundMusic(rootNode);
    private EffectSound effectSound = new EffectSound(rootNode);
    private Player player = new Player(effectSound);
    private Distence distence = new Distence(rootNode, effectSound);
    private Text text = new Text();
    private BitmapText shoot;
    private BitmapText warming;
    public static void main( String[] args ) {
        App app = new App();
        AppSettings settings1 = new AppSettings(true);
        settings1.setResolution(1900, 900);
        app.setSettings(settings1);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        renderManager.setSinglePassLightBatchSize(2);
        flyCam.setMoveSpeed(10);
        rootNode.addLight(new DirectionalLight(new Vector3f(1,-1,1), ColorRGBA.White));
        this.bulletAppState.setDebugEnabled(false);
        stateManager.attach(bulletAppState);
        bulletAppState.getPhysicsSpace().addCollisionListener(this);
        new Terrain().createTerrain(assetManager, this.bulletAppState, rootNode);
        player.createPlayer(assetManager, this.bulletAppState, rootNode, inputManager, cam);
        shoot = text.shoot(guiFont, assetManager, settings, guiNode, player.getCountShoot());
        warming = text.warming(guiFont, assetManager, settings, guiNode, distence.getContWarming());
        backgroundMusic.initAudio(assetManager);
        effectSound.loadEffects(assetManager);
    }

    @Override
    public void simpleUpdate(float tpf) {
        light.lightPlayer(player.getPlayerPhy().getPhysicsLocation(), rootNode);
        monstrum.moveMonster(assetManager, this.bulletAppState, rootNode);
        shoot.setText(Float.toString(player.getCountShoot()));
        distence.distenceMon();
        warming.setText(Integer.toString(distence.getContWarming()));
        backgroundMusic.playBackgroundAudio();
        if (player.getCountShoot() == -1){
            this.stop();
        }
    }

    @Override
    public void collision(PhysicsCollisionEvent physicsCollisionEvent) {
        Spatial a = physicsCollisionEvent.getNodeA();
        Spatial b = physicsCollisionEvent.getNodeB();
        if (b.getName().equals("terrain") && a.getName().equals("shoot")){
            bulletAppState.getPhysicsSpace().remove(a);
            a.removeFromParent();
        }
        if (b.getName().equals("monstrum") && a.getName().equals("shoot")){
            player.setCountShoot(player.getCountShoot());
            bulletAppState.getPhysicsSpace().remove(a);
            a.removeFromParent();
            bulletAppState.getPhysicsSpace().remove(b);
            b.removeFromParent();
        }
    }
}
