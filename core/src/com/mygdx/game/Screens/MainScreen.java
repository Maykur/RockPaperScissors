package com.mygdx.game.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class MainScreen extends ApplicationAdapter implements Screen {
    //INSTANCE VARIABLES
    static Game game;
    Stage stage;
    Viewport viewport;
    Label label;
    SpriteBatch batch;
    static Sound ds;

    //CONSTRUCTOR (Main Screen)
    public MainScreen(final Game game){
        this.game = game;
        ds = Gdx.audio.newSound(Gdx.files.internal("Terraria Music - Day.mp3"));
        ds.play(0.25f);
        ds.loop();
        viewport = new FitViewport(800, 800, new OrthographicCamera());
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        BitmapFont font2 = new BitmapFont();
        label = new Label("Rock, Paper, Scissors!", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        label.setScale(100,100);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("button", Color.WHITE);
        textButtonStyle.down = skin.newDrawable("button-down", Color.WHITE);
        textButtonStyle.font = font2;

        TextButton btn = new TextButton("Player vs Player!", textButtonStyle);
        btn.setPosition(345, 350);
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                game.setScreen(new TutorialScreen(game));
                return false;
            }
        });

        TextButton btn2 = new TextButton("Player vs Computer!", textButtonStyle);
        btn2.setPosition(335, 250);
        btn2.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                ds.stop();
                game.setScreen(new GameScreen(game));
                return false;
            }
        });

        TextButton btn3 = new TextButton("Exit", textButtonStyle);
        btn3.setPosition(370, 150);
        btn3.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                ds.stop();
                System.exit(0);
                return false;
            }
        });

        Image backGround = new Image(new TextureRegion(new Texture(Gdx.files.internal("adad.png"))));

        Table background = new Table();
        background.add(backGround).center();


        Table text = new Table();
        text.setPosition(400, 750);
        text.add(label);

        stage.addActor(backGround);
        stage.addActor(btn);
        stage.addActor(btn2);
        stage.addActor(btn3);
    }


    //IMPLEMENTED METHOD
    @Override
    public void show() {

    }

    //IMPLEMENTED METHOD THAT RENDERS THE GAME SCREEN
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    //IMPLEMENTED METHOD
    @Override
    public void hide() {

    }
}
