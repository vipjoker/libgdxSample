package sample.lesson1.objects;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Administrator on 1/3/2017.
 */
public class Box extends BaseShape{

    public Box(World world, float x, float y) {
        super(world, x, y);
    }

    @Override
    protected BodyDef getBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(this.x,this.y);
        def.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        return def;
    }

    public Box(World world,float x, float y , boolean isStatic){
        super(world,x,y,isStatic);
    }

    @Override
    protected FixtureDef getFixtureDef() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,0.1f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        return fixtureDef;
    }
}
