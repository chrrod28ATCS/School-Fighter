import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CharButton extends Actor {
    public Character character;
    
    public CharButton(Character character) {
        this.character = character;
        
        GreenfootImage image = new GreenfootImage("Select" + character.getName() + ".png");
        image.scale(200, 200);
        setImage(image);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            CharSelect world = (CharSelect)getWorld(); 
            world.characterChosen(character);
        }
    }
}
