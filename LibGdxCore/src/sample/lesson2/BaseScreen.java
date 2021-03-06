package sample.lesson2;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by Makhobey Oleh on 7/27/16.
 * email: tajcig@ya.ru
 */
public abstract class BaseScreen implements Screen, InputProcessor {

    protected Game mGame;
    protected final int viewWidth = 480;
    protected final int viewHeight = 800;
    private ShapeRenderer renderer;
    protected Stage mainStage, uiStage;
    private boolean paused;

    public BaseScreen(Game game) {
        this.mGame = game;
        mainStage = new Stage(new FitViewport(viewWidth, viewHeight));
        uiStage = new Stage(new FitViewport(viewWidth, viewHeight));
        renderer = new ShapeRenderer();
        paused = false;

        InputMultiplexer multiplexer = new InputMultiplexer(this, uiStage, mainStage);
        Gdx.input.setInputProcessor(multiplexer);

        create();
    }


    public abstract void create();

    public abstract void update(float dt);


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        uiStage.act(delta);
        if (!paused) {
            mainStage.act(delta);
            update(delta);
        }
//
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                new Color(14 / 255f, 95 / 255f, 118 / 255f, 1),
                new Color(14 / 255f, 95 / 255f, 118 / 255f, 1),
                new Color(215f / 255, 223f / 255, 113f / 255, 1),
                new Color(215f / 255, 223f / 255, 113f / 255, 1)
        );
        renderer.end();

//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
//        Gdx.gl.glEnable(GL20.GL_BLEND);
//        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        mainStage.draw();
        uiStage.draw();
        uiStage.act(delta);
    }


    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height, true);
        uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void togglePaused() {
        paused = !paused;
    }

    public boolean isPaused() {
        return paused;
    }
}
