package sample.lesson1;

/**
 * Created by Administrator on 1/4/2017.
 */
public class Interpolate {
    public static float map(float value , float origFrom, float origTo, float endFrom , float endTo){

        float scaledValue = value/(origTo - origFrom);

       return (endFrom + scaledValue * endTo);

    }
}
