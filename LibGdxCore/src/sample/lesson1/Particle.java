package sample.lesson1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Makhobey Oleh on 2/4/17.
 * email: tajcig@ya.ru
 */
public class Particle extends BaseActor{
    float time = 0;
    float rotSpeed;
    public Particle(float x , float y){
        super();
        Texture texture = new Texture("star.png");

        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        setWidth(50);
        setHeight(50);
        setTexture(texture);
        setOrigin(texture.getWidth()/2,texture.getHeight()/2);
        setPosition(x,y);
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
            getColor().a = 1 - time;

            super.draw(batch, parentAlpha);
        }
    }

}
