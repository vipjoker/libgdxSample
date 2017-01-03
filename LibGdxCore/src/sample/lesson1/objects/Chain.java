package sample.lesson1.objects;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Administrator on 1/3/2017.
 */
public class Chain extends BaseShape {


    public Chain(World world, float... points) {
        super(world, points);
    }

    @Override
    protected BodyDef getBodyDef() {
        BodyDef def = new BodyDef();

        def.type = BodyDef.BodyType.StaticBody;
        return def;
    }

    @Override
    protected FixtureDef getFixtureDef() {
        ChainShape shape = new ChainShape();
        shape.createChain(points);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        return fixtureDef;
    }
}
