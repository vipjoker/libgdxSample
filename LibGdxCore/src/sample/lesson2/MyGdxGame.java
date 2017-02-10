package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Arrays;


public class MyGdxGame extends ApplicationAdapter {

    Box2DDebugRenderer renderer;
    OrthographicCamera camera;
    TiledMap load;
    World mWorld;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(new InputProcessor() {
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
                System.out.println(amount);
                camera.zoom +=(amount/10f);
                camera.update();
                return false;
            }
        });
        camera = new OrthographicCamera(Gdx.graphics.getWidth()*2, Gdx.graphics.getHeight()*2);
        renderer = new Box2DDebugRenderer();
        load = new TmxMapLoader().load("mobile.tmx");
        mWorld = new World(new Vector2(0, -10f), false);
        parseMap(load);



    }

    private void parseMap(TiledMap map) {
        for (MapLayer layer : map.getLayers())
            if ("Object Layer 1".equals(layer.getName()))
                for (MapObject object : layer.getObjects())
                    if (object instanceof PolylineMapObject) {
                        Polyline polyline = ((PolylineMapObject) object).getPolyline();
                        float x = (Float) object.getProperties().get("x");
                        float y = (Float) object.getProperties().get("y");

                        float[] vertices = polyline.getVertices();
                        createChain(x,y,vertices);
                        System.out.println(Arrays.toString(vertices));
                    }
    }

    private void createChain(float x, float y,float[] points){

        BodyDef def = new BodyDef();
        def.position.set(x,y);
        def.type = BodyDef.BodyType.StaticBody;
        ChainShape shape = new ChainShape();
        shape.createChain(points);

        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.shape = shape;
        mWorld.createBody(def).createFixture(fixtureDef);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mWorld.step(1/60f,6,2);
        renderer.render(mWorld, camera.combined);
    }

    @Override
    public void dispose() {

    }
}
