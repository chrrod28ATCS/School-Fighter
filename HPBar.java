import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HPBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HPBar extends Actor
{
    private Actor player;
    
    public HPBar(Actor p) {
        player = p;
        updateBar();
    }
    
    /**
     * Act - do whatever the HPBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        updateBar();
    }
    
    private int hitPoints() {
        if (player instanceof Player1) {
            return ((Player1)player).hitPoints();
        } else {
            return ((Player2)player).hitPoints();
        }
    }
    
    private int maxHP() {
        if (player instanceof Player1) {
            return ((Player1)player).maxHP();
        } else {
            return ((Player2)player).maxHP();
        }
    }
    
    public void updateBar() {
        GreenfootImage img = new GreenfootImage(200, 20);
        img.setColor(greenfoot.Color.BLACK);
        img.fill();

        int blocks = hitPoints() / 100;

        for (int i = 0; i < blocks; i++) {
            img.setColor(greenfoot.Color.RED);
            img.fillRect(i * 20, 0, 18, 20);
        }

        setImage(img);
    }
}
