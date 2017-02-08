package sample.lesson1;

import com.badlogic.gdx.Game;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.IResourceRetriever;
import com.uwsoft.editor.renderer.resources.ResourceManager;
import sample.lesson2.BaseScreen;

/**
 * Created by oleh on 08.02.17.
 */
public class OverlapScreen extends BaseScreen {

    private SceneLoader loader;
    public OverlapScreen(Game game) {
        super(game);
    }

    @Override
    public void create() {
        IResourceRetriever resourceRetriever = new ResourceManager();

        loader = new SceneLoader();



        loader.loadScene("MainScene");
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(float delta) {

        super.render(delta);
//        com.badlogic.ashley.core.Engine engine = loader.getEngine();


    }
}
