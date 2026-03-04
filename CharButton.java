import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CharButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharButton extends Actor
{
    public String color;
    
    public CharButton(String color) {
        this.color = color;
        
        setImage("Select" + color + ".png");
    }
    
    /**
     * Act - do whatever the CharButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            CharSelect world = (CharSelect)getWorld(); 
            world.characterChosen(color);
        }
    }
}
