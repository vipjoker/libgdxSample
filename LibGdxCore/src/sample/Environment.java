package sample;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by oleh on 06.06.16.
 */
public class Environment extends Actor {

    private TextureRegion region;
    public Rectangle boundary;

    public float velocityX;
    public float velocityY;

    public Environment() {
        super();

        region = new TextureRegion();
        boundary = new Rectangle();
        velocityX = 0;
        velocityY = 0;
    }

    public void setTexture(Texture t) {
        int w = t.getWidth();
        int h = t.getHeight();

        setWidth(w);
        setHeight(h);
        region.setRegion(t);//!
        region.setRegion(0, 0, 100, 100);
    }


    public Rectangle getBoundingRectangle() {
        boundary.set(getX(), getY(), getWidth(), getHeight());
        return boundary;
    }

    public void act(float dt) {
        super.act(dt);
        moveBy(velocityX * dt, velocityY * dt);
    }


    public void draw(Batch batch, float parentAlpha) {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        batch.draw(region,
                getX(),
                getY(),
                getOriginX(),
                getOriginY(),
                getWidth(),
                getHeight(),
                getScaleX(),
                getScaleY(),
                getRotation());
    }
}