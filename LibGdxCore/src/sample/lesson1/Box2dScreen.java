package sample.lesson1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import sample.lesson2.BaseScreen;

/**
 * Created by Makhobey Oleh on 11/17/16.
 * email: tajcig@ya.ru
 */
public class Box2dScreen extends BaseScreen {

    private Box2DDebugRenderer mRenderer;
    private OrthographicCamera mCamera;
    private World mWorld;
    private Body body;
    private Label label;
    public Box2dScreen(Game game) {
        super(game);
    }

    @Override
    public void create() {
        mRenderer = new Box2DDebugRenderer();

        mCamera = new OrthographicCamera();

        float coef =  Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
        mCamera.setToOrtho(false,20,20*coef);
        mWorld = new World(new Vector2(0,-9.8f),true);
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(1);
        circleShape.setPosition(new Vector2(10,10));


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0.9f;
        fixtureDef.restitution  = 0.5f;
        fixtureDef.density = 1;
        fixtureDef.shape = circleShape;

        body =mWorld.createBody(def);
        body.createFixture(fixtureDef);


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        ChainShape shape = new ChainShape();
        shape.createChain(new float[]{0,1,20,1});
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.friction = 0.9f;
        fixtureDef1.restitution = 0.5f;
        fixtureDef1.density = 1;
        fixtureDef1.shape = shape;

        mWorld.createBody(bodyDef).createFixture(fixtureDef1);



      setupLabel();
    }

    private void setupLabel(){
        BitmapFont font = new BitmapFont();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;
        label = new Label("Hello world",labelStyle);
        uiStage.addActor(label);
    }

    @Override
    public void update(float dt) {


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        mWorld.step(1/60f,8,3);
        mRenderer.render(mWorld,mCamera.combined);
        label.setText(String.format("x = %f y = %f \n",body.getPosition().x,body.getPosition().y));

    }
}
