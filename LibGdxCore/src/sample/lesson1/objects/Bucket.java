package sample.lesson1.objects;

import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Administrator on 1/3/2017.
 */
public class Bucket extends Chain{
    public Bucket(World world, float x, float y) {



        super(world, -1f   +x,   3f + y,
                     -.7f +x,    y,
                       .7f +x,    y,
                     1f   +x,    3f +y );
    }
}
