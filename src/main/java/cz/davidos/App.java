package cz.davidos;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.system.AppSettings;
import cz.davidos.models.maps.Terrain;
import cz.davidos.player.Player;

public class App extends SimpleApplication {
    private BulletAppState bulletAppState = new BulletAppState();
    private Player player = new Player();
    private Light light = new Light();
    private Monstrum monstrum = new Monstrum();
    private Distence distence = new Distence(rootNode);
    private Text text = new Text();
    private BitmapText shoot;
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
        rootNode.addLight(new DirectionalLight(new Vector3f(1,-1,1), ColorRGBA.White.mult(0.02f)));
        this.bulletAppState.setDebugEnabled(false);
        stateManager.attach(bulletAppState);
        new Terrain().createTerrain(assetManager, this.bulletAppState, rootNode);
        player.createPlayer(assetManager, this.bulletAppState, rootNode, inputManager, cam);
        shoot = text.shoot(guiFont, assetManager, settings, guiNode, player.getCountShoot());
    }

    @Override
    public void simpleUpdate(float tpf) {
        light.lightPlayer(player.getPlayerPhy().getPhysicsLocation(), rootNode);
        monstrum.moveMonster(assetManager, this.bulletAppState, rootNode);
        shoot.setText(Float.toString(player.getCountShoot()));
        distence.distenceMon();
        if (player.getCountShoot() == -1){
            this.stop();
        }
    }
}
