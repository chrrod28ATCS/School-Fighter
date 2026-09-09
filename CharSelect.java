import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CharSelect extends World {
    private Character player1Character = null;
    private Character player2Character = null;
    private boolean bestOf3;
    Character Fighter = new Character("Fighter", "Red", 4, 200, -22);
    Character Sprinter = new Character("Sprinter", "Blue", 7, 100, -22);
    Character Jumper = new Character("Jumper", "Green", 4, 100, -28);
    
    private int startTimer = -1;
    
    private GreenfootSound music1 = new GreenfootSound("CharSelect.mp3");
    private GreenfootSound music2 = new GreenfootSound("2OutOf3Falls-CharSelect.mp3");
    
    public CharSelect(boolean bestOf3) {
        super(800, 600, 1); 
        
        this.bestOf3 = bestOf3;
        
        if (!bestOf3) {
            showText("NORMAL BATTLE", 400, 60);
        } else {
            showText("2 OUT OF 3 FALLS", 400, 60);
        }
        
        showText("Choose your character!", 400, 100);
        addObject(new CharButton(Fighter), 200, 300);
        addObject(new CharButton(Sprinter), 400, 300);
        addObject(new CharButton(Jumper), 600, 300);
        
        Greenfoot.playSound("Choose.mp3");
        
        if (bestOf3) {
            music2.playLoop();
        } else {
            music1.playLoop();
        }
    }
    
    public void characterChosen(Character character) {
        if (player1Character == null) {
            player1Character = character;
            showText("Player 1 selected: " + character.getName(), 400, 150);
        }
        else if (player2Character == null) {
            player2Character = character;
            showText("Player 2 selected: " + character.getName(), 400, 170);
            showText("Get ready to fight!", 400, 500);
            startTimer = 120;
        }
    }
    
    public void act() {
        showText("FIGHTER", 200, 420);
        showText("SPRINTER", 400, 420);
        showText("JUMPER", 600, 420);
        if (startTimer > 0) {
            startTimer--;

            if (startTimer == 0) {
                stopped();
                Greenfoot.setWorld(new MyWorld(player1Character, player2Character, bestOf3, 0, 0, 0, 0));
            }
        }
    }
    
    public void stopped() {
        music1.stop();
        music2.stop();
    }
}
