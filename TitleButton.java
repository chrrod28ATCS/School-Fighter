import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class TitleButton extends Actor {
    private String buttonType;
    private TitleScreen titleScreen;
    private ModeSelect modeSelect;
    
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
        if (buttonType.equals("HowToPlay")) {
            setImage("HowToPlay.png");
        }
        if (buttonType.equals("Normal")) {
            setImage("Normal.png");
        }
        if (buttonType.equals("2OutOf3Falls")) {
            setImage("2OutOf3Falls.png");
        }
    }
    
    public void act() {
        // TitleScreen buttons
        if (Greenfoot.mouseClicked(this) && getWorld() instanceof TitleScreen) {
            titleScreen = (TitleScreen)getWorld();
            titleScreen.stopped();
            if (buttonType.equals("Play")) { 
                Greenfoot.setWorld(new ModeSelect());
            }
            if (buttonType.equals("Credits")) {
                Greenfoot.setWorld(new Credits());
            }
            if (buttonType.equals("HowToPlay")) {

                Greenfoot.setWorld(new HowToPlay());
            }
        }
        
        // ModeSelect buttons
        if (Greenfoot.mouseClicked(this) && getWorld() instanceof ModeSelect) {
            modeSelect = (ModeSelect)getWorld();
            modeSelect.stopped();
            if (buttonType.equals("Normal")) { 
                Greenfoot.setWorld(new CharSelect(false));
            }
            if (buttonType.equals("2OutOf3Falls")) {
                Greenfoot.setWorld(new CharSelect(true));
            }
        }
    }
}
