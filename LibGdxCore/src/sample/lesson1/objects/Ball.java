package sample.lesson1.objects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;


public class Ball extends BaseShape{
    public Ball(World world,float x, float y,float width,float height ) {
        super(world,x, y,width,height);

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
        PolygonShape shape = new PolygonShape();

        shape.set(generateRoundShape(8));




        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.9f;
        fixtureDef.restitution  = 0.2f;
        fixtureDef.density = .1f;

        return fixtureDef;
    }

    private float[] generateRoundShape(int corners){
        int angleStep = 360 / corners;
        int currentAngle = 0;
        float[] array = new float[corners*2];
        for (int i = 0; i < corners; i++) {
            array[2*i] = MathUtils.cosDeg(currentAngle) * width/2;
            array[2*i+1] = MathUtils.sinDeg(currentAngle) * height/2;
            currentAngle += angleStep;
        }
        return array;
    }

}
