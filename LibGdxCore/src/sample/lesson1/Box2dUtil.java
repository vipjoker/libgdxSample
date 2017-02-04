package sample.lesson1;

/**
 * Created by Makhobey Oleh on 2/2/17.
 * email: tajcig@ya.ru
 */
public class Box2dUtil {

    public static int BOX2D_CONSTANT = 32;

    public static float fromBox2d(float box2dDim){
        return BOX2D_CONSTANT * box2dDim;
    }
    public static float toBox2d(float pixelDim){
        return pixelDim / BOX2D_CONSTANT;
    }

}
