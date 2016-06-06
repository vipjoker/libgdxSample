package sample;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.scenes.scene2d.ui.List;

/**
 * Created by oleh on 06.06.16.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Main project ");
        LwjglApplication lwjglApplication = new LwjglApplication(new Basic3DTest());

    }
}
