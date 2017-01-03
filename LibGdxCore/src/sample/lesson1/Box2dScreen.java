package sample.lesson1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import sample.lesson1.objects.Ball;
import sample.lesson1.objects.Box;
import sample.lesson1.objects.Bucket;
import sample.lesson1.objects.Chain;
import sample.lesson2.BaseScreen;

/**
 * Created by Makhobey Oleh on 11/17/16.
 * email: tajcig@ya.ru
 */
public class Box2dScreen extends BaseScreen {

    private Box2DDebugRenderer mRenderer;
    private OrthographicCamera mCamera;
    private World mWorld;
    private Body circle;
    private Label label;
    private ShapeRenderer shapeRenderer;

    private float b2Height ,b2Width;

    public Box2dScreen(Game game) {
        super(game);
    }

    @Override
    public void create() {
        mRenderer = new Box2DDebugRenderer();
        shapeRenderer = new ShapeRenderer();
        mCamera = new OrthographicCamera();
        float coef =  Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
        b2Width = 20;
        b2Height = 20*coef;
        mCamera.setToOrtho(false,b2Width,b2Height);
        shapeRenderer.setProjectionMatrix(mCamera.combined);

        mWorld = new World(new Vector2(0,-9.8f),true);
        circle =new Ball(mWorld,10,10).getBody();

        new Chain(mWorld,0,0,20,0,20,20*coef,0,20*coef,0,0);

        new Bucket(mWorld,15f,0f);


        Box box = new Box(mWorld, 12, 5 ,true);
        Box box2 = new Box(mWorld, 8, 5 );





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

    float time = 0;

    @Override
    public void render(float delta) {
        super.render(delta);
        processInput();
        time+=delta;
      //  if(time > .1) {
            time =0;
          //  new Ball(mWorld, 10, 10).getBody().applyForceToCenter(new Vector2(MathUtils.random(-10f,10f),0),true);
        //}
        mWorld.step(1/60f,6,2);

        mRenderer.render(mWorld,mCamera.combined);
        drawLine();

        label.setText(String.format("x = %f y = %f \n",circle.getPosition().x,circle.getPosition().y));

    }

    private void processInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            circle.applyForceToCenter(new Vector2(0,10),true);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            circle.applyForceToCenter(new Vector2(0,-10),true);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            circle.applyForceToCenter(new Vector2(-10,0),true);
        }if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            circle.applyForceToCenter(new Vector2(10,0),true);
        }
    }


    Vector2 begin ,end;
    private void drawLine(){
        if(Gdx.input.isTouched()){
            startDrawing();

            if(begin != null && end != null){

                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);


                System.out.printf("Line drawn %f,%f - %f,%f\n" ,begin.x,begin.y,end.x,end.y);
                shapeRenderer.line(begin.x,begin.y,end.x,end.y);
                shapeRenderer.end();
            }

        }else {

            endDrawing();
        }
    }

    private void startDrawing() {

        float b2x  = Interpolate.map(Gdx.input.getX() , 0, Gdx.graphics.getWidth(), 0, b2Width);
        float b2y = b2Height - Interpolate.map(Gdx.input.getY(), 0, Gdx.graphics.getHeight(),  0,b2Height);

        if(begin == null)begin = new Vector2(b2x,b2y);
        if(end == null)end = new Vector2(b2x,b2y);
        else end.set(b2x,b2y);


    }

    private void endDrawing() {
        if(begin != null && end != null) {
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
}
