package sample.lesson1.objects;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Administrator on 1/3/2017.
 */
public abstract class BaseShape {

    private Body body;
    protected float x,y;
    protected float [] points;
    protected  boolean isStatic;
    protected float width,height;

    public BaseShape (World world, float x, float y){

        this.x = x;
        this.y = y;
        this.body = world.createBody(getBodyDef());
        this.body.createFixture(getFixtureDef());
    }

    public BaseShape(World world,float x, float y , float width,float height){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width =width;
        this.body = world.createBody(getBodyDef());
        this.body.createFixture(getFixtureDef());
    }

    public BaseShape (World world, float x, float y,boolean isStatic){

        this.x = x;
        this.y = y;
        this.isStatic = isStatic;
        this.body = world.createBody(getBodyDef());
        this.body.createFixture(getFixtureDef());
    }

    public BaseShape(World world,float... points){
        this.points = points;
        this.body = world.createBody(getBodyDef());
        this.body.createFixture(getFixtureDef());
    }


    public Body getBody() {
        return body;
    }

    protected abstract BodyDef getBodyDef();


    protected abstract FixtureDef getFixtureDef();
}
