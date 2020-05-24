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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class TutorialScreen extends ApplicationAdapter implements Screen {
    //INSTANCE VARIABLES
    Game game;
    Stage stage;
    Viewport viewport;
    Label label;
    SpriteBatch batch;

    //CONSTRUCTOR (HAS TUTORIAL TEXT TO TEACH PLAYER HOW PVP MODE WORKS)
    public TutorialScreen(final Game game){
        this.game = game;
        viewport = new FitViewport(800, 800, new OrthographicCamera());
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        BitmapFont font2 = new BitmapFont();
        font2.getData().setScale(1.6f,1.6f);
        label = new Label("                                        Welcome to Rock, Paper, Scissors!\n" +
                "                               This is a little rundown on how PVP mode works.\n" +
                "                 Keep in mind, this is turn based (pass computer between players)\n" +
                "                 Player One is on the left side (Player one must make the first move)\n" +
                "                       Player Two is on the right side (Player two must go second)\n" +
                "                       You must play rock, paper, scissors against another player,\n" +
                "                                     getting three points will give you a win.\n" +
                "                                    Win multiple rounds to get a highscore!\n" +
                "                                  Every loss makes you drop your win streak", new Label.LabelStyle(font2, Color.SCARLET));
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("button", Color.WHITE);
        textButtonStyle.down = skin.newDrawable("button-down", Color.WHITE);
        textButtonStyle.font = font2;

        TextButton btn3 = new TextButton("Continue!", textButtonStyle);
        btn3.setPosition(370, 150);
        btn3.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                MainScreen.ds.stop();
                game.setScreen(new GameScreenPVP(game));
                return false;
            }
        });

        Image backGround = new Image(new TextureRegion(new Texture(Gdx.files.internal("rpc.png"))));

        Table background = new Table();
        background.add(backGround).center();


        Table text = new Table();
        text.setPosition(355, 450);
        text.add(label);

        stage.addActor(backGround);
        stage.addActor(btn3);
        stage.addActor(text);
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
