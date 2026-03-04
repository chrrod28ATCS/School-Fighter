import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    private GreenfootSound music = new GreenfootSound("TitleScreen.mp3");
    
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        addObject(new Title(), 390, 200);
        addObject(new TitleButton("Play"), 800/2, 400);
        addObject(new TitleButton("Credits"), 800/2, 500);
        showText("2026 Midyear Project by Christian Rodriguez", 800/2, 575);
        showText("Version 1.1.0", 75, 25);
        
        music.play();
    }
    
    public void stopped() {
        music.stop();
    }
}
