import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World {
    Player1 p1;
    Player2 p2;
    
    private int timer = 240;
    public boolean fightStarted = false;
    
    private GreenfootSound music = new GreenfootSound("Fight.mp3");
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld(String p1Color, String p2Color) {    
        super(800, 600, 1); 
        
        int rand = (int)(Math.random()*7);
        GreenfootImage bg = new GreenfootImage("bg" + (rand+1) + ".png");
        bg.scale(800, 600);
        setBackground(bg);
        
        p1 = new Player1(p1Color); // add Player1 and their HP
        addObject(p1, 200, 455);
        addObject(new HPBar(p1), 200, 40);
        
        p2 = new Player2(p2Color); // add Player2 and their HP
        addObject(p2, 600, 455);
        addObject(new HPBar(p2), 600, 40);
        
        addObject(new Floor(), 800/2, 562);
        
        addObject(new Intro(), 800/2, 600/2);
        Greenfoot.playSound("3 2 1.mp3");
    }
    
    public void act() {
        if (!fightStarted) {
            timer--;
            
            showText("WAD to control", 200, 350);
            showText("F to punch, S to kick", 200, 375);
            
            showText("Arrows to control", 600, 350);
            showText("/ to punch, down to kick", 600, 375);
            if (timer <= 60) {
                showText("", 200, 350);
                showText("", 200, 375);
                showText("", 600, 350);
                showText("", 600, 375);
            }
            if (timer <= 0) {
                fightStarted = true;
                removeObjects(getObjects(Intro.class));
                music.playLoop();
            }
        }
        showText("Player 1", 200, 20);
        showText("Player 2", 600, 20);
    }
    
    public boolean fightStarted() {
        return fightStarted;
    }
    
    public void stopped() {
        music.stop();
    }
}
