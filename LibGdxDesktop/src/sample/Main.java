package sample;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import sample.lesson1.CheeseGame;

/**
 * Created by oleh on 06.06.16.
 */
public class Main {
    public static void main(String[] args) {


//        new LwjglApplication(new CheeseGame());
        LwjglApplicationConfiguration configuration =new LwjglApplicationConfiguration();
        configuration.width = 480;
        configuration.height = 800;
        configuration.samples = 2;
        new LwjglApplication(new CheeseGame(),configuration);



    }
}
