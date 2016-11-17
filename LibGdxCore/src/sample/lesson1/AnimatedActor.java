package sample.lesson1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Makhobey Oleh on 7/22/16.
 * email: tajcig@ya.ru
 */
public class AnimatedActor extends BaseActor {
    public float elapsedTime;
    public Animation animation;

    public AnimatedActor(){
        super();
        elapsedTime =0;
    }

    public void setAnimation(Animation animation){
        Texture texture = animation.getKeyFrame(0).getTexture();
        setTexture(texture);
        this.animation = animation;
    }

    public void act(float dt){
        super.act(dt);

        elapsedTime +=dt;
        if(velocityY !=0 || velocityX !=0){
            setRotation(MathUtils.atan2(velocityY,velocityX) * MathUtils.radiansToDegrees);
        }
    }

    public void draw(Batch batch , float parentAlpha){
        region.setRegion(animation.getKeyFrame(elapsedTime));
        super.draw(batch,parentAlpha);
    }

}
