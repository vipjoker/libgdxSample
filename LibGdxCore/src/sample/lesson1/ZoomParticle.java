package sample.lesson1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Makhobey Oleh on 2/7/17.
 * email: tajcig@ya.ru
 */
public class ZoomParticle extends BaseActor {
    float time = 0;
    float rotSpeed;
    public ZoomParticle(float x , float y,String path,boolean random){
        super();
        Texture texture = new Texture(path);
        if(random)setColor(new Color(.7f,MathUtils.random(),0.7f,1f));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        setWidth(50);
        setHeight(50);
        setTexture(texture);
        setOrigin(texture.getWidth()/2,texture.getHeight()/2);
        setPostionWithOrigin(x,y);
        rotSpeed = MathUtils.random(-1,1);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        rotateBy(rotSpeed);

        time += Gdx.graphics.getDeltaTime();
        if(time > 1 ){
            time = 0;
            remove();
        }else {
            setScale(1+time);
            getColor().a = 1 - time;

            super.draw(batch, parentAlpha);
        }
    }

}
