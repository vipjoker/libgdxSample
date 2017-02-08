package sample.lesson1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import sample.lesson1.objects.Ball;
import sample.lesson1.objects.Box;
import sample.lesson1.objects.Bucket;
import sample.lesson1.objects.Chain;
import sample.lesson2.BaseScreen;

import static sample.lesson1.Box2dUtil.fromBox2d;

/**
 * Created by Makhobey Oleh on 11/17/16.
 * email: tajcig@ya.ru
 */
public class Box2dScreen extends BaseScreen {

    BaseActor paper;
    float time = 0;
    Vector2 begin, end;
    private Box2DDebugRenderer mRenderer;
    private OrthographicCamera mCamera;
    private World mWorld;
    private Body circle;
    private Label label;
    private ShapeRenderer shapeRenderer;
    private Stage mStage;
    private float b2Height, b2Width;
    private final float ballHeight = .6f;
    private final float ballWidth = .6f;

    public Box2dScreen(Game game) {
        super(game);
    }

    @Override
    public void create() {
        mRenderer = new Box2DDebugRenderer();
        shapeRenderer = new ShapeRenderer();
        mCamera = new OrthographicCamera();
        mStage = new Stage();
        paper = new BaseActor();
        Texture texture = new Texture("paper.png");
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        paper.setTexture(texture );
//        paper.setWidth(fromBox2d(ballWidth));
//        paper.setHeight(fromBox2d(ballHeight));
        paper.setOrigin(paper.getWidth() / 2, paper.getHeight() / 2);

        mStage.addActor(paper);


        b2Width = Box2dUtil.toBox2d(Gdx.graphics.getWidth());
        b2Height = Box2dUtil.toBox2d(Gdx.graphics.getHeight());

        mCamera.setToOrtho(false, b2Width, b2Height);
        shapeRenderer.setProjectionMatrix(mCamera.combined);

        mWorld = new World(new Vector2(0, -9.8f), true);
        circle = new Ball(mWorld, 10, 10, ballWidth, ballHeight).getBody();

        new Chain(mWorld, 0, 0, b2Width, 0, b2Width, b2Height, 0, b2Height, 0, 0);

        new Bucket(mWorld, 15f, 0f);


        Box box = new Box(mWorld, 12, 5, true);
        Box box2 = new Box(mWorld, 8, 5);


        setupLabel();
    }

    private void setupLabel() {
        BitmapFont font = new BitmapFont();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;
        label = new Label("Hello world", labelStyle);
        uiStage.addActor(label);
    }



    @Override
    public void update(float dt) {


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        processInput();
        time += delta;
        //  if(time > .1) {
        time = 0;
        //  new Ball(mWorld, 10, 10).getBody().applyForceToCenter(new Vector2(MathUtils.random(-10f,10f),0),true);
        //}
        mWorld.step(1 / 60f, 6, 2);

        mRenderer.render(mWorld, mCamera.combined);

        paper.setPostionWithOrigin( fromBox2d(circle.getPosition().x),fromBox2d( circle.getPosition().y ));
        paper.setRotation(circle.getAngle() * MathUtils.radiansToDegrees);
        if(circle.getLinearVelocity().len() > 0){
           mStage.addActor(new Particle(paper.getX(),paper.getY(),"circle.png"));
        }
        drawLine();

        mStage.draw();

        label.setText(String.format("x = %f y = %f \n", circle.getPosition().x, circle.getPosition().y));


    }

    private void processInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            circle.applyForceToCenter(new Vector2(0, 10), true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            circle.applyForceToCenter(new Vector2(0, -10), true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            circle.applyForceToCenter(new Vector2(-10, 0), true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            circle.applyForceToCenter(new Vector2(10, 0), true);
        }
    }

    private void drawLine() {
        if (Gdx.input.isTouched()) {
            startDrawing();

            if (begin != null && end != null) {

                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);


                System.out.printf("Line drawn %f,%f - %f,%f\n", begin.x, begin.y, end.x, end.y);
                shapeRenderer.line(begin.x, begin.y, end.x, end.y);
                shapeRenderer.end();
            }

        } else {

            endDrawing();
        }
    }

    private void startDrawing() {

        float b2x = Interpolate.map(Gdx.input.getX(), 0, Gdx.graphics.getWidth(), 0, b2Width);
        float b2y = b2Height - Interpolate.map(Gdx.input.getY(), 0, Gdx.graphics.getHeight(), 0, b2Height);

        if (begin == null) begin = new Vector2(b2x, b2y);
        if (end == null) end = new Vector2(b2x, b2y);
        else end.set(b2x, b2y);


    }

    private void endDrawing() {
        if (begin != null && end != null) {
            Vector2 sub = begin.sub(end);
            sub.scl(10);
            circle.applyForceToCenter(sub, true);
        }
        begin = null;
        end = null;
    }


    @Override
    public void dispose() {
        super.dispose();
        mRenderer.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        ZoomParticle particle = new ZoomParticle(screenX, Gdx.graphics.getHeight() - screenY,"border_circle.png",true);
        particle.addAction(Actions.fadeOut(1));
        mStage.addActor(particle);
        return true;
    }
}
