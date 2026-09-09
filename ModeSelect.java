import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ModeSelect extends World {
    private GreenfootSound music = new GreenfootSound("ModeSelect.mp3");
    
    public ModeSelect() {    
        super(800, 600, 1); 

        showText("Choose a mode!", 400, 100);
        addObject(new TitleButton("Normal"), 400, 200);
        showText("Standard one-match fight. Deplete your enemy's HP!", 400, 250);
        addObject(new TitleButton("2OutOf3Falls"), 400, 400);
        showText("A challenge to win the best of 3 battles!", 400, 500);
        
        music.play();
    }
    
    public void stopped() {music.stop();}
}
