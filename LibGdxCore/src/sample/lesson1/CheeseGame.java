package sample.lesson1;
import sample.lesson2.CheeseMenu;
import com.badlogic.gdx.Game;

/**
 * Created by Makhobey Oleh on 7/27/16.
 * email: tajcig@ya.ru
 */
public class CheeseGame extends Game{

    @Override
    public void create() {
//        CheeseMenu menu = new CheeseMenu(this);

        MenuScreen menu = new MenuScreen(this);

        setScreen(menu);
    }
}
