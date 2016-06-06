package sample;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by oleh on 06.06.16.
 */
public class Platformer implements ApplicationListener{
    public Stage mainStage;
    public MyActor actor;
    public  Environment environment;

    @Override
    public void create() {


        mainStage = new Stage();
        actor = new MyActor();




        Texture texture = new Texture(Gdx.files.internal("zeroxsheet.gif"));
        actor.setTexture(texture);
        actor.setHeight(100);
        actor.setWidth(100);
        actor.setPosition(0,0);




        Texture entTexture = new Texture(Gdx.files.internal("TilesMisc.png"));
        environment = new Environment();
        environment.setTexture(entTexture);
        environment.setHeight(1000);
        environment.setWidth(1000);
        environment.setPosition(0,0);

        mainStage.addActor(environment);
        mainStage.addActor(actor);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
