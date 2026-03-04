import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CharSelect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharSelect extends World
{
    private String player1Color = null;
    private String player2Color = null;
    
    private int startTimer = -1;
    
    private GreenfootSound music = new GreenfootSound("CharSelect.mp3");
    
    /**
     * Constructor for objects of class CharSelect.
     * 
     */
    public CharSelect() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        showText("Choose a color to play as!", 400, 100);
        addObject(new CharButton("Red"), 200, 300);
        addObject(new CharButton("Orange"), 300, 300);
        addObject(new CharButton("Green"), 400, 300);
        addObject(new CharButton("Blue"), 500, 300);
        addObject(new CharButton("Purple"), 600, 300);
        
        Greenfoot.playSound("Choose.mp3");
        music.playLoop();
    }
    
    public void characterChosen(String color)
    {
        if (player1Color == null) {
            player1Color = color;
            showText("Player 1 selected: " + color, 200, 200);
        }
        else if (player2Color == null) {
            player2Color = color;
            showText("Player 2 selected: " + color, 600, 200);

            showText("Get ready to fight!", 400, 500);

            startTimer = 120;
        }
    }
    
    public void act() {
        if (startTimer > 0) {
            startTimer--;

            if (startTimer == 0) {
                stopped();
                Greenfoot.setWorld(new MyWorld(player1Color, player2Color));
            }
        }
    }
    
    public void stopped() {
        music.stop();
    }
}
