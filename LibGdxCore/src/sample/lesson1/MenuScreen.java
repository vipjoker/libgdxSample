package sample.lesson1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import sample.lesson2.BaseScreen;
import sample.lesson2.CheeseMenu;

/**
 * Created by Makhobey Oleh on 11/17/16.
 * email: tajcig@ya.ru
 */
public class MenuScreen extends BaseScreen {
    private Skin skin;
    private Button button;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void create() {

        setUpSkin();

//"p{color:#ffc000}";

        Table table = setUpTable();
        Table topAction = setupTopInfo();
        uiStage.addActor(table);


        button = new Button(skin, "btnStyle");
        button.setPosition(100, 100);
        mainStage.addActor(button);
        uiStage.addActor(button);

    }

    private void setUpSkin() {
        skin = new Skin();
        skin.add("white", new Texture("white4px.png"));


        //button style
        Button.ButtonStyle style = new Button.ButtonStyle();
        Texture btnPressed = new Texture("buttons/btn_pressed.png");
        Texture btnNormal = new Texture("buttons/btn_normal.png");
        btnPressed.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        btnNormal.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        skin.add("normal", btnNormal);
        skin.add("pressed", btnPressed);
        style.up = skin.getDrawable("normal");
        style.down = skin.getDrawable("pressed");
        skin.add("btnStyle", style);


        //label style
        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/green.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        labelStyle.font = font;
        labelStyle.fontColor =  new Color(14 / 255f, 95 / 255f, 118 / 255f, 1);
        skin.add("lblStyle", labelStyle);

    }


    private Table setUpTable() {


        Label lblStart = new Label("Start game", skin, "lblStyle");

        lblStart.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int btn) {
                mGame.setScreen(new CheeseMenu(mGame));
                return true;
            }
        });

        Label lblBox2d = new Label("Box2d", skin, "lblStyle");

        lblBox2d.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                uiStage.addAction(Actions.sequence(
                        Actions.moveBy(0,Gdx.graphics.getHeight(), 4,Interpolation.pow3),
                        Actions.after(Actions.run(() -> mGame.setScreen(new Box2dScreen(mGame))))
                ));

                return true;
            }
        });


        Label lblOptions = new Label("Optoins", skin, "lblStyle");
        lblOptions.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int btn) {
                button.addAction(
                        Actions.sequence(
                                Actions.moveBy(0, 100, 1, Interpolation.exp5),
                                Actions.delay(1),
                                Actions.moveBy(0, -100, 1, Interpolation.exp5)
                        )
                );
                return true;
            }
        });
        Label lblAbout = new Label("Ovarlap", skin, "lblStyle");
        lblAbout.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mGame.setScreen(new OverlapScreen(mGame));
                return true;
            }
        });


        Label lblExit = new Label("Exit", skin, "lblStyle");

        Table table = new Table();
        table.setFillParent(true);

//        Drawable white = skin.newDrawable("white", Color.valueOf("#137cb4"));
//        table.setBackground(white);
        table.setDebug(true);

        table.add(lblStart).expandX().left();
        table.row();
        table.add(lblBox2d);
        table.row();
        table.add(lblOptions);
        table.row();
        table.add(lblAbout);
        table.row();
        table.add(lblExit);
        return table;
    }


    private Table setupTopInfo() {
        Table table = new Table();

        Button button = new Button(skin, "btnStyle");
        table.add(button).expandX().pad(20);
//        Drawable yellow = skin.newDrawable("white", Color.valueOf("#ffc000"));
//        table.background(yellow);
        table.setX(100);
        table.setY(100);
        return table;
    }


    @Override
    public void update(float dt) {


    }
    float counter ;
    @Override
    public void render(float delta) {
        counter+=delta;
        super.render(delta);
        if(counter > 0.5) {
            mainStage.addActor(
                    new ZoomParticle(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight()), "border_circle.png", true)
            );
            counter =0;
        }
    }
}
