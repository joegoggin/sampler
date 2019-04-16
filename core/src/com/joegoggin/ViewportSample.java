package com.joegoggin;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.joegoggin.common.SampleBase;
import com.joegoggin.common.SampleInfo;

public class ViewportSample extends SampleBase {

    public static final Logger log = new Logger(ViewportSample.class.getName(), Logger.DEBUG);

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(ViewportSample.class);

    private static final float WORLD_WIDTH = 800.0f;
    private static final float WORLD_HEIGHT = 600.0f;

    private OrthographicCamera camera;
    private Viewport currentViewport;
    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;

    private ArrayMap<String, Viewport> viewports = new ArrayMap<String, Viewport>();

    private int currentViewportIndex;
    private String currentViewportName;


    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("raw/level-bg-small.png"));
        font = new BitmapFont(Gdx.files.internal("font/oswald-32.fnt"));

        createViewports();
        selectNextViewport();
    }

    @Override
    public void resize(int width, int height) {
        currentViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        font.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        selectNextViewport();
        return true;
    }

    private void createViewports() {
        viewports.put(StretchViewport.class.getSimpleName(), new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
        viewports.put(FitViewport.class.getSimpleName(), new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
        viewports.put(FillViewport.class.getSimpleName(), new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
        viewports.put(ScreenViewport.class.getSimpleName(), new ScreenViewport(camera));
        viewports.put(ExtendViewport.class.getSimpleName(), new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));

        currentViewportIndex = -1;
    }

    private void selectNextViewport() {
        currentViewportIndex = (currentViewportIndex + 1) % viewports.size;

        currentViewport = viewports.getValueAt(currentViewportIndex);
        currentViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        currentViewportName = viewports.getKeyAt(currentViewportIndex);

        log.debug("selected viewport = " + currentViewportName);
    }
}
