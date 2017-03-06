package sample.lesson1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import java.util.Arrays;

/**
 * Created by omak on 3/6/17.
 */
public class EditorScreen implements Screen, InputProcessor {


    OrthographicCamera mCamera;
    ShapeRenderer renderer;
    Vector3 cameraTarget;
    float[]vertices ;
    public EditorScreen(CheeseGame cheeseGame) {
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        mCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mCamera.position.add(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
        mCamera.update();
        renderer.setProjectionMatrix(mCamera.combined);

        Gdx.gl20.glClearColor(.2f, .2f, .2f, 1);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float v) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setProjectionMatrix(mCamera.combined);
        renderer.updateMatrices();
        renderer.circle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 10);


        if(vertices != null && vertices.length > 3) renderer.polyline(vertices);


        renderer.end();
        updateCamera();
    }

    private void updateCamera() {
        if (cameraTarget == null) return;
        mCamera.position.x = mCamera.position.x + (cameraTarget.x - mCamera.position.x) * 0.04f;
        mCamera.position.y = mCamera.position.y + (cameraTarget.y - mCamera.position.y) * 0.04f;
        mCamera.update();
    }

    @Override
    public void resize(int i, int i1) {

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

    @Override
    public boolean keyDown(int i) {

        switch (i) {
            case Input.Keys.UP:
                cameraTarget = mCamera.position.cpy();
                cameraTarget.add(0, -30, 0);
                return true;

            case Input.Keys.DOWN:
                cameraTarget = mCamera.position.cpy();
                cameraTarget.add(0, 30, 0);
                return true;
            case Input.Keys.LEFT:
                cameraTarget = mCamera.position.cpy();
                cameraTarget.add(30, 0, 0);
                return true;
            case Input.Keys.RIGHT:
                cameraTarget = mCamera.position.cpy();
                cameraTarget.add(-30,0,0);
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        i1 = Gdx.graphics.getHeight() -i1;
        if(vertices == null)vertices = new float []{(float)i,(float)i1};
        else{
            float[] newArray = new float[vertices.length +2];
            System.arraycopy(vertices,0,newArray,0,vertices.length);
            System.out.println("After array copy" +  Arrays.toString(newArray));
            newArray[newArray.length-2] = i;
            newArray[newArray.length-1] = i1;
            vertices = newArray;
        }

        System.out.println( Arrays.toString(vertices));

        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {

        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        if(vertices == null)return false;
        i1 = Gdx.graphics.getHeight() -i1;
        vertices[vertices.length-2] = i;
        vertices[vertices.length-1] = i1;
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        mCamera.zoom += i;
        mCamera.update();
        return false;
    }
}
