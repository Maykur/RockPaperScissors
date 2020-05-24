package com.mygdx.game.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class GameScreenPVP extends ApplicationAdapter implements Screen {
    //INSTANCE VARIABLES
    SpriteBatch batch;
    Texture img;
    TextureRegion textRe;
    Image cardImage;
    Image Black;
    Label label5;
    TextureRegion textRe2;
    TextureRegion textReBlack;
    Image cardImage2;
    Game game;
    Stage stage;
    Table table;
    Viewport viewport;
    Table table2;
    Table table3;
    Table table10;
    Label label;
    Label label2;
    Label label4;
    List keylist;
    HashMap<String,String> cards;
    Sound wavSound;
    Table table11;
    Table table24;
    Table table25;
    Table table28;
    Table table32;
    Table tableRock;
    Table scissorsP1;
    Table scissorsP2;
    int loss = 0;
    int wins = 0;
    int score = 0;
    int scorep2 = 0;
    int redo = 0;
    int redo2 = 0;
    String rockR;
    String pap;
    String sci;
    ArrayList<Integer> highScoreP1;
    ArrayList<Integer> highScoreP2;

    /*
    CONSTRUCTOR (ENTIRE PLAYER VS PLAYER GAME) - sets up all the instance variables,
    and plays through the never ending game. Each round is 3 matches, and first to 3 matches won gets a point and resets the match score and gives that player a point for the round.
    If a player is on a win streak and loses, their high score is updated into a their own high score list and will stay there until they exit the game. The game doesn't end
    until the player playing chooses to exit the game, allowing the two players to play endlessly with no interruptions. The game plays as a pass-and-play where one person makes a play
    and their play is saved as a string and is compared to the second player's play. After each play, an image will display representing their play.
     */
    public GameScreenPVP(final Game game) {
        wavSound = Gdx.audio.newSound(Gdx.files.internal("Minor Circuit [Original] Super Smash Bros. Ultimate.mp3"));
        wavSound.play(0.25f);
        wavSound.loop();
        this.game = game;
        highScoreP1 = DataStorage.highScorePV1;
        highScoreP2 = DataStorage.highScorePV2;
        viewport = new StretchViewport(800, 800, new OrthographicCamera());
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        table = new Table();
        table2 = new Table();
        table10 = new Table();
        table11 = new Table();
        table24 = new Table();
        table25 = new Table();
        table28 = new Table();
        table32 = new Table();
        tableRock = new Table();
        scissorsP1 = new Table();;
        scissorsP2 = new Table();;

        cards = new HashMap<>();
        cards.put("paperTrans2.png", "Paper");
        cards.put("sciNewTrans.png", "Scissors");
        cards.put("rockNewTrans.png", "Rock");

        keylist = new ArrayList(cards.keySet());
        Collections.shuffle(keylist);

        table25.bottom();
        table25.pad(0,560,300,0);

        scissorsP1.bottom();
        scissorsP1.pad(0,560,300,0);

        scissorsP2.bottom();
        scissorsP2.pad(0,1060,300,0);

        table28 = new Table();
        table28.bottom();
        table28.pad(0,560,200,0);

        table24.bottom();
        table24.pad(0,1060,200,0);

        tableRock.bottom();
        tableRock.pad(0,1060,300,0);

        table3 = new Table();
        table3.bottom();
        table3.pad(0,560,200,0);

        table32 = new Table();
        table32.bottom();
        table32.pad(0,560,200,0);

        textReBlack = new TextureRegion(new Texture(Gdx.files.internal("Black_colour.jpg")));
        Black = new Image(textReBlack);

        label = new Label("Player 1's Score: " + wins, new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        label2= new Label("Player 2's Score: " + loss, new Label.LabelStyle(new BitmapFont(), Color.RED));
        label4 = new Label("Rounds Won P1: " + score, new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        label5 = new Label("Rounds Won P2: " + scorep2, new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.setPosition(100, 750);
        table2.setPosition(100, 730);
        table10.setPosition(100,710);
        table11.setPosition(100,690);

        Gdx.input.setInputProcessor(stage);
        BitmapFont font2 = new BitmapFont();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("button", Color.WHITE);
        textButtonStyle.down = skin.newDrawable("button-down", Color.WHITE);
        textButtonStyle.font = font2;

        TextButton Rock = new TextButton("Rock!", textButtonStyle);
        Rock.setPosition(50, 500);
        Rock.addListener(new ClickListener(){
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                if(redo == 0) {
                    tableRock.clear();
                    table24.clear();
                    table25.clear();
                    scissorsP1.clear();
                    scissorsP2.clear();
                    redo++;
                    rockR = "Rock";
                    textRe = new TextureRegion(new Texture(Gdx.files.internal("rockTrans.png")));
                    cardImage = new Image(textRe);
                    table3.clear();
                    table25.clear();
                    table25.add(cardImage).width(253).height(226);
                    table28.add(Black).width(280).height(400);
                    stage.draw();
                }
                return false;
            }
        });

        TextButton Paper = new TextButton("Paper!", textButtonStyle);
        Paper.setPosition(50, 375);
        Paper.addListener(new ClickListener(){
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                if(redo == 0) {
                    tableRock.clear();
                    table24.clear();
                    table25.clear();
                    scissorsP2.clear();
                    scissorsP1.clear();
                    redo++;
                    pap = "Paper";
                    textRe = new TextureRegion(new Texture(Gdx.files.internal("paperNewTrans2.png")));
                    cardImage = new Image(textRe);
                    table3.clear();
                    table3.add(cardImage).width(280).height(400);
                    table32.add(Black).width(280).height(400);
                    stage.draw();
                }
                return false;
            }
        });

        TextButton Scissors = new TextButton("Scissors!", textButtonStyle);
        Scissors.setPosition(50, 225);
        Scissors.addListener(new ClickListener(){
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                if(redo == 0) {
                    tableRock.clear();
                    table24.clear();
                    table25.clear();
                    scissorsP1.clear();
                    scissorsP2.clear();
                    redo++;
                    sci = "Scissors";
                    textRe = new TextureRegion(new Texture(Gdx.files.internal("sciTrans.png")));
                    cardImage = new Image(textRe);
                    table3.clear();
                    table25.clear();
                    scissorsP1.add(cardImage).width(253).height(226);
                    table28.add(Black).width(280).height(400);
                    stage.draw();
                }
                return false;
            }
        });


        TextButton Rock2 = new TextButton("Rock!", textButtonStyle);
        Rock2.setPosition(700, 500);
        Rock2.addListener(new ClickListener(){
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                if(redo2 == 0 && redo == 1) {
                    redo++;
                    String rock = "Rock";
                    if (rockR != null || pap != null || sci != null) {
                            textRe2 = new TextureRegion(new Texture(Gdx.files.internal("rockNewTrans.png")));
                            cardImage2 = new Image(textRe2);
                            table24.clear();
                            tableRock.add(cardImage2).width(253).height(226);
                            stage.draw();
                            redo = 0;
                            redo2 = 0;
                        table32.clear();
                        table28.clear();
                        if (rockR != null && rock.equals(rockR)) {
                            //do nothing
                            redo = 0;
                            redo2 = 0;
                            rockR = null;
                        } else if (sci != null && sci.equals("Scissors")) {
                            //computerChoice == scissors so player gets point
                            loss++;
                            label2.setText("Player 2's Score: " + loss);
                            redo = 0;
                            redo2 = 0;
                            sci=null;
                            if (loss == 3) {
                                updateListP1(score);
                                score = 0;
                                label4.setText("Rounds Won P1: " + score);
                                scorep2 += 1;
                                label5.setText("Rounds Won P2: " + scorep2);
                                wins = 0;
                                loss = 0;
                                label.setText("Player 1's Score: " + wins);
                                label2.setText("Player 2's Score: " + loss);
                                redo = 0;
                                redo2 = 0;
                            }
                        } else if (pap.equals("Paper")) {
                            //computerChoice == paper so player losses round
                            wins++;
                            label.setText("Player 1's Score: " + wins);
                            redo = 0;
                            redo2 = 0;
                            pap = null;
                            if (wins == 3) {
                                updateListP2(scorep2);
                                scorep2 = 0;
                                label5.setText("Rounds Won P2: " + scorep2);
                                score += 1;
                                label4.setText("Rounds Won P1: " + score);
                                wins = 0;
                                loss = 0;
                                label2.setText("Player 2's Score: " + loss);
                                label.setText("Player 1's Score: " + wins);
                            }

                        }
                    } else {
                        redo2 = 0;
                        JOptionPane.showMessageDialog(null, "Choose an option from Player one's side first!");
                    }
                }
                return false;
            }
        });

        TextButton Paper2 = new TextButton("Paper!", textButtonStyle);
        Paper2.setPosition(700, 375);
        Paper2.addListener(new ClickListener(){
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                if (redo2 == 0 && redo == 1) {
                    redo2++;
                    String paper = "Paper";
                    if (rockR != null || pap != null || sci != null) {
                        textRe2 = new TextureRegion(new Texture(Gdx.files.internal("paperTrans2.png")));
                        cardImage2 = new Image(textRe2);
                        table24.clear();
                        table24.add(cardImage2).width(280).height(400);
                        stage.draw();
                        redo = 0;
                        redo2 = 0;
                        table32.clear();
                        table28.clear();
                        if (pap != null && paper.equals(pap)) {
                            //do nothing
                            redo = 0;
                            redo2 = 0;
                            pap = null;
                        } else if (rockR != null && rockR.equals("Rock")) {
                            //computerChoice == scissors so player gets point\
                            loss++;
                            label2.setText("Player 2's Score: " + loss);
                            redo = 0;
                            redo2 = 0;
                            rockR = null;
                            if (loss == 3) {
                                updateListP1(score);
                                score = 0;
                                label4.setText("Rounds Won P1: " + score);
                                scorep2 += 1;
                                label5.setText("Rounds Won P2: " + scorep2);
                                wins = 0;
                                loss = 0;
                                label.setText("Player 1's Score: " + wins);
                                label2.setText("Player 2's Score: " + loss);
                            }
                        } else if (sci.equals("Scissors")) {
                            //computerChoice == paper so player losses round
                            wins++;
                            label.setText("Player 1's Score:" + wins);
                            redo = 0;
                            redo2 = 0;
                            sci = null;
                            if (wins == 3) {
                                updateListP2(scorep2);
                                scorep2 = 0;
                                label5.setText("Rounds Won P2: " + scorep2);
                                score += 1;
                                label4.setText("Rounds Won P1: " + score);
                                wins = 0;
                                loss = 0;
                                label2.setText("Player 2's Score: " + loss);
                                label.setText("Player 1's Score: " + wins);
                            }

                        }
                    } else {
                        redo2=0;
                        JOptionPane.showMessageDialog(null, "Choose an option from Player one's side first!");
                    }
                }
                return false;
            }
        });

        TextButton Scissors2 = new TextButton("Scissors!", textButtonStyle);
        Scissors2.setPosition(700, 225);
        Scissors2.addListener(new ClickListener(){
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                if(redo2 == 0 && redo == 1) {
                    redo2++;
                    String scis = "Scissors";
                    if (rockR != null || pap != null || sci != null) {
                        textRe2 = new TextureRegion(new Texture(Gdx.files.internal("sciNewTrans.png")));
                        cardImage2 = new Image(textRe2);
                        table24.clear();
                        scissorsP2.add(cardImage2).width(253).height(226);
                        stage.draw();
                        redo = 0;
                        redo2 = 0;
                        table32.clear();
                        table28.clear();
                        if (sci != null && scis.equals(sci)) {
                            //do nothing
                            redo = 0;
                            redo2 = 0;
                            sci = null;
                        } else if (pap != null && pap.equals("Paper")) {
                            //computerChoice == scissors so player gets point\
                            loss++;
                            label2.setText("Player 2's Score: " + loss);
                            redo = 0;
                            redo2 = 0;
                            pap = null;
                            if (loss == 3) {
                                updateListP1(score);
                                score = 0;
                                label4.setText("Rounds Won P1: " + score);
                                scorep2 += 1;
                                label5.setText("Rounds Won P2: " + scorep2);
                                wins = 0;
                                loss = 0;
                                label.setText("Player 1's Score: " + wins);
                                label2.setText("Player 2's Score: " + loss);
                            }
                        } else if (rockR.equals("Rock")) {
                            //computerChoice == paper so player losses round
                            wins++;
                            label.setText("Player 1's Score: " + wins);
                            redo = 0;
                            redo2 = 0;
                            rockR = null;
                            if (wins == 3) {
                                updateListP2(scorep2);
                                scorep2 = 0;
                                label5.setText("Rounds Won P2: " + scorep2);
                                score += 1;
                                label4.setText("Rounds Won P1: " + score);
                                wins = 0;
                                loss = 0;
                                label2.setText("Player 2's Score: " + loss);
                                label.setText("Player 1's Score: " + wins);
                            }

                        }
                    } else {
                        redo2 = 0;
                        JOptionPane.showMessageDialog(null, "Choose an option from Player one's side first!");
                    }
                }
                return false;
            }
        });

        TextButton.TextButtonStyle help = new TextButton.TextButtonStyle();
        help.up = skin.newDrawable("button", Color.WHITE);
        help.down = skin.newDrawable("button-down", Color.WHITE);
        help.font = font2;

        TextButton halp = new TextButton("Exit", textButtonStyle);
        halp.setPosition(700, 730);
        halp.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                System.exit(0);
                return false;
            }
        });

        TextButton.TextButtonStyle back = new TextButton.TextButtonStyle();
        back.up = skin.newDrawable("button", Color.WHITE);
        back.down = skin.newDrawable("button-down", Color.WHITE);
        back.font = font2;

        TextButton backs = new TextButton("<- Back", textButtonStyle);
        backs.setPosition(700, 50);
        backs.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                DataStorage.gethighScorePV1(highScoreP1);
                DataStorage.gethighScorePV2(highScoreP2);
                DataStorage.getwinP1(wins);
                DataStorage.getwinP2(loss);
                DataStorage.getScoreP1(score);
                DataStorage.getScoreP2(scorep2);
                wavSound.stop();
                game.setScreen(new MainScreen(game));
                return false;
            }
        });

        TextButton.TextButtonStyle wipeOut = new TextButton.TextButtonStyle();
        wipeOut.up = skin.newDrawable("button", Color.WHITE);
        wipeOut.down = skin.newDrawable("button-down", Color.WHITE);
        wipeOut.font = font2;

        TextButton wipe = new TextButton("Wipe ALL scores!", textButtonStyle);
        wipe.setPosition(25, 50);
        wipe.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                loss = 0;
                wins = 0;
                score = 0;
                scorep2 = 0;
                label2.setText("Player 2's Score: " + loss);
                label.setText("Player 1's Score: " + wins);
                label5.setText("Rounds Won P2: " + scorep2);
                label4.setText("Rounds Won P1: " + score);
                highScoreP1 = new ArrayList<Integer>();
                highScoreP2 = new ArrayList<Integer>();
                return false;
            }
        });

        TextButton.TextButtonStyle highscorelist = new TextButton.TextButtonStyle();
        highscorelist.up = skin.newDrawable("button", Color.WHITE);
        highscorelist.down = skin.newDrawable("button-down", Color.WHITE);
        highscorelist.font = font2;

        TextButton highscorelist2 = new TextButton("High Scores P1", textButtonStyle);
        highscorelist2.setPosition(35, 650);
        highscorelist2.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                JOptionPane.showMessageDialog(null, "P1 High Scores!\n" + highScoreP1);
                return false;
            }
        });

        TextButton highscorelist32 = new TextButton("High Scores P2", textButtonStyle);
        highscorelist32.setPosition(680, 650);
        highscorelist32.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                JOptionPane.showMessageDialog(null, "P2 High Scores!\n" + highScoreP2);
                return false;
            }
        });


        Image backGround = new Image(new TextureRegion(new Texture(Gdx.files.internal("pap.png"))));
        Table background = new Table();
        background.add(backGround).center();


        cardImage=new Image(textRe);
        table.add(label);
        table2.add(label2);
        table10.add(label4);
        table11.add(label5);
        stage.addActor(backGround);
        stage.addActor(halp);
        stage.addActor(table);
        stage.addActor(table2);
        stage.addActor(table3);
        stage.addActor(table32);
        stage.addActor(scissorsP2);
        stage.addActor(scissorsP1);
        stage.addActor(table24);
        stage.addActor(table10);
        stage.addActor(table25);
        stage.addActor(table28);
        stage.addActor(table11);
        stage.addActor(tableRock);
        stage.addActor(highscorelist2);
        stage.addActor(highscorelist32);
        stage.addActor(Rock);
        stage.addActor(Paper);
        stage.addActor(Scissors);
        stage.addActor(Rock2);
        stage.addActor(Paper2);
        stage.addActor(Scissors2);
        stage.addActor(backs);
        stage.addActor(wipe);

    }

    /*
    This method updates player one's high score list with whatever their high score was before losing that round.
    If the number is less than 0 or equal to 0, it wont be added to the list, but if it is greater than 0, it'll be added to the list and the list
    will be sorted to have the scores be from greatest to least.
     */
    public void updateListP1(int number){
        if(number > 0) {
            highScoreP1.add(number);
            Collections.sort(highScoreP1);
            Collections.reverse(highScoreP1);
        }
    }

    /*
    This method updates player two's high score list with whatever their high score was before losing that round.
    If the number is less than 0 or equal to 0, it wont be added to the list, but if it is greater than 0, it'll be added to the list and the list
    will be sorted to have the scores be from greatest to least.
     */
    public void updateListP2(int number){
        if(number > 0) {
            highScoreP2.add(number);
            Collections.sort(highScoreP2);
            Collections.reverse(highScoreP2);
        }
    }

    //IMPLEMENTED METHOD
    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    //IMPLEMENTED METHOD
    @Override
    public void show() {

    }

    //IMPLEMENTED METHOD THAT RENDERS THE GAME SCREEN
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    //IMPLEMENTED METHOD
    @Override
    public void hide() {

    }

    //IMPLEMENTED METHOD THAT DISPOSES CONTENT
    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
        wavSound.dispose();
    }
}
