package com.mygdx.game;

import com.mygdx.game.Screens.MainScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;

public class Rock_Paper_Scissors extends Game {
    //INSTANCE VARIABLE
    MainScreen mainscreen;

    /*
    CONSTRUCTOR METHOD WHICH CREATES THE MAIN SCREEN AND SETS THE CURRENT SCREEN TO THE MAIN SCREEN
     */
    @Override
    public void create(){
        mainscreen = new MainScreen(this);
        setScreen(mainscreen);
    }

    //IMPLEMENTED METHOD
    public void render(){
        super.render();
    }

    //IMPLEMENTED METHOD
    public void dispose(){
        super.dispose();
    }

}
