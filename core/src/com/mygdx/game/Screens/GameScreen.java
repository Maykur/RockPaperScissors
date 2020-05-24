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


public class GameScreen extends ApplicationAdapter implements Screen {
    //INSTANCE VARIABLES
    SpriteBatch batch;
    Texture img;
    TextureRegion textRe;
    Image cardImage;
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
    Table computerChoiceLabel;
    Label computerChoiceHere;
    List keylist;
    HashMap<String,String> cards;
    Sound wavSound;
    int loss = 0;
    int wins =0;
    int score = 0;
    ArrayList<Integer> highScore;
    Sound sands;

    /*
   CONSTRUCTOR (ENTIRE COMPUTER VS PLAYER GAME) - sets up all the instance variables,
    and plays through the never ending game. Each round is 3 matches, and first to 3 matches won gets a point and resets the match score and gives that player a point for the round.
    If the player is on a win streak and loses, their high score is updated into a their high score list and will stay there until they exit the game. The game doesn't end
    until the player playing chooses to exit the game, allowing the player to play endlessly with no interruptions. The game plays using strings that are compared together between player and computer
    choice to see who'd win the round. After each play, an image will display representing the play.
     */
    public GameScreen(final Game game) {
        sands = Gdx.audio.newSound(Gdx.files.internal("Minor Circuit [Original] Super Smash Bros. Ultimate.mp3"));
        sands.play(0.25f);
        sands.loop();
        this.game = game;
        highScore = DataStorage.highScoreComputer;
        viewport = new StretchViewport(800, 800, new OrthographicCamera());
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        table = new Table();
        table2 = new Table();
        table10 = new Table();
        computerChoiceLabel = new Table();

        cards = new HashMap<>();
        cards.put("paperTrans2.png", "Paper");
        cards.put("sciNewTrans.png", "Scissors");
        cards.put("rockNewTrans.png", "Rock");

        keylist = new ArrayList(cards.keySet());
        Collections.shuffle(keylist);

        table3 = new Table();
        table3.bottom();
        table3.pad(0,800,200,0);


        label = new Label("Player 1's Score: " + wins, new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        label2= new Label("Computer's Score: " + loss, new Label.LabelStyle(new BitmapFont(), Color.RED));
        label4 = new Label("Rounds Won: " + score, new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        computerChoiceHere = new Label("Computer Choice: ", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        table.setPosition(100, 750);
        table2.setPosition(100, 730);
        table10.setPosition(100,710);

        computerChoiceLabel.bottom();
        computerChoiceLabel.pad(0,800,100,0);

        Gdx.input.setInputProcessor(stage);
        BitmapFont font2 = new BitmapFont();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("button", Color.WHITE);
        textButtonStyle.down = skin.newDrawable("button-down", Color.WHITE);
        textButtonStyle.font = font2;

        TextButton Rock = new TextButton("Rock!", textButtonStyle);
        Rock.setPosition(225, 50);
        Rock.addListener(new ClickListener(){
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                String rock = "Rock";
                String computer = computerChoice();
                if (rock.equals(computer)){
                    //do nothing
                }
                else if(computer.equals("Scissors")){
                    //computerChoice == scissors so player gets point\
                    wins++;
                    label.setText("Player 1's Score: " + wins);
                    if(wins == 3){
                        score += 1;
                        label4.setText("Rounds Won: " + score);
                        wins = 0;
                        loss = 0;
                        label.setText("Player 1's Score: " + wins);
                        label2.setText("Computer's Score: " + loss);
                    }
                }
                else {
                    //computerChoice == paper so player losses round
                    loss++;
                    label2.setText("Computer's Score: " + loss);
                    if(loss == 3){
                        updateList(score);
                        score = 0;
                        label4.setText("Rounds Won: " + score);
                        wins = 0;
                        loss = 0;
                        label2.setText("Computer's Score: " + loss);
                        label.setText("Player 1's Score: " + wins);
                    }

                }
                return false;
            }
        });

        TextButton Paper = new TextButton("Paper!", textButtonStyle);
        Paper.setPosition(375, 50);
        Paper.addListener(new ClickListener(){
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                String pap = "Paper";
                String computer = computerChoice();
                if (pap.equals(computer)){
                    //do nothing
                }
                else if(computer.equals("Rock")){
                    //computerChoice == scissors so player gets point
                    wins++;
                    label.setText("Player 1's Score: " + wins);
                    if(wins == 3){
                        score += 1;
                        label4.setText("Rounds Won: " + score);
                        wins = 0;
                        loss = 0;
                        label.setText("Player 1's Score: " + wins);
                        label2.setText("Computer's Score: " + loss);
                    }
                    }
                else {
                    //computerChoice == paper so player losses round
                    loss++;
                    label2.setText("Computer's Score: " + loss);
                    if(loss == 3){
                        updateList(score);
                        score = 0;
                        label4.setText("Rounds Won: " + score);
                        wins = 0;
                        loss = 0;
                        label2.setText("Computer's Score: " + loss);
                        label.setText("Player 1's Score: " + wins);
                    }
                }
                return false;
            }
        });

        TextButton Scissors = new TextButton("Scissors!", textButtonStyle);
        Scissors.setPosition(525, 50);
        Scissors.addListener(new ClickListener(){
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                String sci = "Scissors";
                String computer = computerChoice();
                if (sci.equals(computer)){
                    //do nothing
                }
                else if(computer.equals("Paper")){
                    //computerChoice == paper so player gets point
                    wins++;
                    label.setText("Player 1's Score: " + wins);
                    if(wins == 3){
                        score += 1;
                        label4.setText("Rounds Won: " + score);
                        wins = 0;
                        loss = 0;
                        label.setText("Player 1's Score: " + wins);
                        label2.setText("Computer's Score: " + loss);
                    }
                }
                else {
                    //computerChoice == rock so player losses round3
                    loss++;
                    label2.setText("Computer's Score: " + loss);
                    if(loss == 3){
                        updateList(score);
                        score = 0;
                        label4.setText("Rounds Won: " + score);
                        wins = 0;
                        loss = 0;
                        label2.setText("Computer's Score: " + loss);
                        label.setText("Player 1's Score: " + wins);
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

        TextButton.TextButtonStyle highscorelist = new TextButton.TextButtonStyle();
        highscorelist.up = skin.newDrawable("button", Color.WHITE);
        highscorelist.down = skin.newDrawable("button-down", Color.WHITE);
        highscorelist.font = font2;

        TextButton highscorelist2 = new TextButton("High Scores", textButtonStyle);
        highscorelist2.setPosition(690, 650);
        highscorelist2.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                JOptionPane.showMessageDialog(null, "High Scores!\n" + highScore);
                return false;
            }
        });

        TextButton.TextButtonStyle wipeOut = new TextButton.TextButtonStyle();
        wipeOut.up = skin.newDrawable("button", Color.WHITE);
        wipeOut.down = skin.newDrawable("button-down", Color.WHITE);
        wipeOut.font = font2;

        TextButton wipe = new TextButton("Wipe ALL scores!", textButtonStyle);
        wipe.setPosition(670, 570);
        wipe.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                loss = 0;
                wins = 0;
                score = 0;
                label2.setText("Computer's Score: " + loss);
                label.setText("Player 1's Score: " + wins);
                label4.setText("Rounds Won: " + score);
                highScore = new ArrayList<Integer>();
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
                DataStorage.getHighScoreComputer(highScore);
                DataStorage.getlossComputer(loss);
                DataStorage.getwinsComputer(wins);
                DataStorage.getscoreComputer(score);
                sands.stop();
                game.setScreen(new MainScreen(game));
                return false;
            }
        });


        Image backGround = new Image(new TextureRegion(new Texture(Gdx.files.internal("pap.png"))));
        Table background = new Table();
        background.add(backGround).center();


        cardImage=new Image(textRe);
        table.add(label);
        computerChoiceLabel.add(computerChoiceHere);
        table2.add(label2);
        table10.add(label4);
        stage.addActor(backGround);
        stage.addActor(halp);
        stage.addActor(computerChoiceLabel);
        stage.addActor(table);
        stage.addActor(table2);
        stage.addActor(table3);
        stage.addActor(table10);
        stage.addActor(highscorelist2);
        stage.addActor(Rock);
        stage.addActor(Paper);
        stage.addActor(Scissors);
        stage.addActor(backs);
        stage.addActor(wipe);
    }

    /*
    This method updates the highscore list when the player loses against the computer.
    Depending on what the number is in the parameter, it'll be added to the ArrayList highScore and then
    sorted greatest to least.
     */
    public void updateList(int number){
        if(number > 0) {
            highScore.add(number);
            Collections.sort(highScore);
            Collections.reverse(highScore);
        }
    }

    /*
    IMPLEMENTED METHOD
     */
    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    //IMPLEMENTED METHOD
    @Override
    public void show() {

    }

    /*
    IMPLEMENTED METHOD THAT RENDERS THE GAME ON THE SCREEN
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    /*
    This method computes the computer's choice, shuffling through a list of choices
    "Rock" "Paper" and "Scissors", and picks the first choice on the list. It'll draw that image on screen
    and will display what they drew as both an image and in text. After each draw, they remove the previous card to show the new one.
     */
    public String computerChoice(){
        Collections.shuffle(keylist);
        String s = (String) keylist.get(0);
        textRe = new TextureRegion(new Texture(Gdx.files.internal((String) keylist.get(0))));
        cardImage = new Image(textRe);
        table3.clear();
        table3.add(cardImage).width(400).height(400).center();
        stage.draw();
        computerChoiceHere.setText("Computer Choice: " + cards.get(s));
        return cards.get(s);
    }

    /*
    IMPLEMENTED METHOD
     */
    @Override
    public void hide() {

    }

    /*
    IMPLEMENTED METHOD THAT DISPOSES CONTENT AT THE END
     */
    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
        wavSound.dispose();
    }


}
