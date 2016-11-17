package sample.lesson1;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

/**
 * Created by oleh on 06.06.16.
 */
public class CheeseLevel implements Screen {
    public Stage mainStage,uiStage;
    public BaseActor cheese,floor,wintext;
    public AnimatedActor mouse;
    public boolean win;
    public float timeElapsed;
    public final int mapWidth =800;
    public final int mapHeight = 800;
    public final int viewWidth = 640;
    public final int viewHeiht = 480;
    public Label label;

    public Game game;


    public CheeseLevel(Game game){
        this.game = game;
        create();
    }


    public void create() {


        mainStage = new Stage();
        uiStage = new Stage();
        floor = new BaseActor();
        floor.setTexture(new Texture(Gdx.files.internal("cheeseGame/tiles-800-800.jpg")));
        floor.setPosition(0, 0);
        mainStage.addActor(floor);


        cheese = new BaseActor();
        cheese.setTexture(new Texture(Gdx.files.internal("cheeseGame/cheese.png")));
        cheese.setPosition(400, 300);
        cheese.setOrigin(cheese.getWidth() / 2, cheese.getHeight() / 2);
        mainStage.addActor(cheese);


        wintext = new BaseActor();
        wintext.setTexture(new Texture(Gdx.files.internal("cheeseGame/you-win.png")));
        wintext.setPosition(170, 60);
        wintext.setOrigin(wintext.getWidth()/2,wintext.getHeight()/2);
        wintext.setVisible(false);
        uiStage.addActor(wintext);


        TextureRegion[] frames = new TextureRegion[4];
        for (int n = 0; n < 4; n++) {
            String filename = "cheeseGame/mouse" + n + ".png";
            Texture texture = new Texture(Gdx.files.internal(filename));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            frames[n] = new TextureRegion(texture);
        }
        Array<TextureRegion> framesArray = new Array<>(frames);
        Animation animation = new Animation(0.1f,framesArray,Animation.PlayMode.LOOP_PINGPONG);

        mouse = new AnimatedActor();
        mouse.setAnimation(animation);
        mouse.setOrigin(mouse.getWidth()/2,mouse.getHeight()/2);
        mouse.setPosition(20, 20);
        mainStage.addActor(mouse);

        BitmapFont bitmapFont = new BitmapFont();
        String text = "Time: 0";
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,Color.NAVY);

        label = new Label(text,labelStyle);

        label.setPosition(500,400);

        uiStage.addActor(label);

    }

    @Override
    public void show() {

    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render(float delta) {


        mouse.velocityX = 0;
        mouse.velocityY = 0;

        checkInput();


        mainStage.act(delta);
        uiStage.act(delta);
        Camera camera = mainStage.getCamera();


        camera.position.set(mouse.getX() + mouse.getOriginX(), mouse.getY() + mouse.getOriginY(),0);
        Rectangle mouseRect = mouse.getBoundingRect();
        Rectangle cheeseRect = cheese.getBoundingRect();
        mouse.setX(MathUtils.clamp(mouse.getX(),0,mapWidth - mouse.getWidth()));
        mouse.setY(MathUtils.clamp(mouse.getY(),0,mapHeight - mouse.getHeight()));

        camera.position.x = MathUtils.clamp(camera.position.x,viewWidth/2,mapWidth - viewWidth/2);
        camera.position.y = MathUtils.clamp(camera.position.y,viewHeiht/2,mapHeight - viewHeiht/2);

        if (!win && cheeseRect.contains(mouseRect)) {
            win = true;
        }

        if (win) {

            Action parallel = Actions.parallel(
                    Actions.alpha(1),
                    Actions.rotateTo(360, 1),
                    Actions.scaleTo(0, 0, 1),
                    Actions.fadeOut(1)
            );

            cheese.addAction(parallel);

            Action cycleWinAnimation =Actions.sequence(
                    Actions.rotateBy(10,3),
                    Actions.scaleTo(2,2,3),
                    Actions.alpha(0,2),
                    Actions.show(),
                    Actions.alpha(1,2),
                    Actions.scaleTo(1,1,3));

            wintext.setVisible(true);
            wintext.addAction(cycleWinAnimation);

        }else{

            timeElapsed += delta;
            label.setText("Time : " +(int)timeElapsed);
        }


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.draw();
        uiStage.draw();
    }

    private void checkInput() {

        if(Gdx.input.isKeyPressed(Input.Keys.M)){
            game.setScreen(new CheeseMenu(game));
        }


        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            mouse.velocityY += 100;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            mouse.velocityY -= 100;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            mouse.velocityX -= 100;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            mouse.velocityX += 100;
        }

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
