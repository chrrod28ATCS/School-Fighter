import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleButton extends Actor
{
    private String buttonType;
    private TitleScreen titleScreen;
    
    public TitleButton(String type) {
        this.buttonType = type;
        
        purpose();
        GreenfootImage img = getImage();
        img.scale(img.getWidth()/2, 164);
        setImage(img);
    }
    
    public void purpose() {
        if (buttonType.equals("Play")) {
            setImage("Play.png");
        }
        if (buttonType.equals("Credits")) {
            setImage("Credits.png");
        }
    }
    
    /**
     * Act - do whatever the TitleButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            titleScreen = (TitleScreen)getWorld();
            titleScreen.stopped();
            if (buttonType.equals("Play")) { 
                Greenfoot.setWorld(new CharSelect());
            }
            if (buttonType.equals("Credits")) {
                Greenfoot.setWorld(new Credits());
            }
        }
    }
}
