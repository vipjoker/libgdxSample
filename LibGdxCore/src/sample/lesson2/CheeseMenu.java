package sample.lesson2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import sample.lesson1.BaseActor;

/**
 * Created by Makhobey Oleh on 7/27/16.
 * email: tajcig@ya.ru
 */
public class CheeseMenu extends BaseScreen {

    public CheeseMenu(Game game) {
        super(game);
    }
    World world;

    @Override
    public void create() {
        initBox2d();
        BaseActor backGround = new BaseActor();
        backGround.setTexture(new Texture(Gdx.files.internal("cheeseGame/tiles-menu.jpg")));
        uiStage.addActor(backGround);

        BaseActor titleText = new BaseActor();
        titleText.setTexture(new Texture(Gdx.files.internal("cheeseGame/cheese-please.png")));
        titleText.setPosition(20, 100);
        uiStage.addActor(titleText);

        BitmapFont bitmapFont = new BitmapFont();

        String text = "Press S to start, press M for menu";
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont, Color.YELLOW);
        Label label = new Label(text, labelStyle);
        label.setPosition(100, 50);
        label.setFontScale(2);


        titleText.setOrigin(titleText.getWidth() / 2, titleText.getHeight() / 2);

        titleText.addAction(Actions.forever(
                Actions.sequence(
                        Actions.scaleTo(0.8f, 0.8f, 1f),
                        Actions.delay(0.5f),
                        Actions.scaleTo(1, 1, 0.5f)

                )));


        label.addAction(
                Actions.forever(
                        Actions.sequence(
                                Actions.color(new Color(1, 1, 0, 1), 1f),

                                Actions.delay(0.5f),
                                Actions.color(new Color(0.5f, 0.5f, 0, 1), 0.5f)
                        )
                )
        );


        uiStage.addActor(label);


    }


    private void initBox2d(){
        Camera camera = uiStage.getCamera();
        world = new World(new Vector2(9.8f,0),true);

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(1);
        circleShape.setPosition(new Vector2(0.5f,0.5f));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.restitution = 0.8f;
        fixtureDef.friction = 0.5f;
        fixtureDef.shape = circleShape;

        world.createBody(def).createFixture(fixtureDef);
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            mGame.setScreen(new CheeseLevel(mGame));
        }

        return false;
    }
}
