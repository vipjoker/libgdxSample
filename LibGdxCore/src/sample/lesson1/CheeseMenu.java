package sample.lesson1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Makhobey Oleh on 7/27/16.
 * email: tajcig@ya.ru
 */
public class CheeseMenu implements Screen {

    public Stage uiStage;
    private Game game;


    public CheeseMenu (Game game){
        this.game =game;
        create();
    }

    private void create() {
        uiStage = new Stage();

        BaseActor backGround  = new BaseActor();
        backGround.setTexture(new Texture(Gdx.files.internal("cheeseGame/tiles-menu.jpg")));
        uiStage.addActor(backGround);

        BaseActor titleText = new BaseActor();
        titleText.setTexture(new Texture(Gdx.files.internal("cheeseGame/cheese-please.png")));
        titleText.setPosition(20,100);
        uiStage.addActor(titleText);

        BitmapFont bitmapFont =new BitmapFont();

        String text = "Press S to start, press M for menu";
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont, Color.YELLOW);
        Label label = new Label(text,labelStyle);
        label.setPosition(100,50);
        label.setFontScale(2);

        label.addAction(
                Actions.forever(
                        Actions.sequence(
                                Actions.color(new Color(1,1,0,1),0.5f),
                                Actions.delay(0.5f),
                                Actions.color(new Color(0.5f,0.5f,0,1),0.5f)
                        )
                )
        );


        uiStage.addActor(label);



    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            game.setScreen(new CheeseLevel(game));
        }

        uiStage.act(delta);


        Gdx.gl.glClearColor(0.8f,0.8f,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
