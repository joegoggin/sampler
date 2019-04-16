package com.joegoggin;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.joegoggin.common.SampleBase;
import com.joegoggin.common.SampleInfo;

import java.util.Arrays;

public class GdxReflectionSample extends SampleBase {

    private static final Logger log = new Logger(GdxReflectionSample.class.getName(), Logger.DEBUG);

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(GdxReflectionSample.class);

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        camera = new OrthographicCamera();
        viewport = new FitViewport(1080, 720, camera);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

        debugReflection(GdxReflectionSample.class);
    }

    @Override
    public void resize(int i, int i1) {
        viewport.update(i, i1, true);
    }

    @Override
    public void render() {

    }

    private void draw() {
        // mouse / touch x/y
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();

        // buttons
        boolean leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        boolean rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);

        font.draw(batch, "Mouse/Touch : X = " + mouseX + " Y = " + mouseY, 20f, 720 - 20f);

        font.draw(batch, leftPressed ? "Left button pressed" : "Left button not pressed", 20f, 720 - 50f);

        font.draw(batch, rightPressed ? "right button pressed" : "right button not pressed", 20f, 720 - 80f);

        // keys
        boolean wPressed = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean sPressed = Gdx.input.isKeyPressed(Input.Keys.S);

        font.draw(batch, wPressed ? "W is pressed" : "W is not pressed", 20f, 720 - 110f);

        font.draw(batch, sPressed ? "S is pressed" : "S is not pressed", 20f, 720 - 140f);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    private static void debugReflection(Class<?> clazz) {
        Field[] fields = ClassReflection.getDeclaredFields(clazz);
        Method[] methods = ClassReflection.getDeclaredMethods(clazz);

        log.debug("== debug relfection class == " + clazz.getName());

        log.debug("fields-count = " + fields.length);

        for (Field field : fields) {
            log.debug("name = " + field.getName() + "type = " + field.getType());
        }

        log.debug("method-count = " + methods.length);

        for (Method method : methods) {
            log.debug("name = " + method.getName() + " parameterTypes = " + Arrays.asList(method.getParameterTypes()));
        }

        log.debug("====");
    }
}