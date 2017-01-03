package sample.lesson1.objects;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Administrator on 1/3/2017.
 */
public class Ball extends BaseShape{

    public Ball(World world, float x, float y) {
        super(world, x, y);
    }

    @Override
    protected BodyDef getBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(this.x,this.y);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    @Override
    protected FixtureDef getFixtureDef() {
        CircleShape shape = new CircleShape();
        shape.setRadius(.2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.9f;
        fixtureDef.restitution  = 0.2f;
        fixtureDef.density = .1f;
        return fixtureDef;
    }

}
