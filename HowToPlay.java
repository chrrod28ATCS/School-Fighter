import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class HowToPlay extends World {
    private GreenfootSound music = new GreenfootSound("HowToPlay.mp3");
    
    public HowToPlay() {    
        super(800, 600, 1); 
        
        showText("HOW TO PLAY SCHOOL FIGHTER", 400, 40);
        
        showText("CONTROLS:", 400, 80);
        
        showText("Player 1:", 300, 120);
        showText("A to move left", 300, 140);
        showText("D to move right", 300, 160);
        showText("W to jump", 300, 180);
        showText("F to punch", 300, 200);
        showText("S to kick", 300, 220);
        
        showText("Player 2:", 500, 120);
        showText("← to move left", 500, 140);
        showText("→ to move right", 500, 160);
        showText("↑ to jump", 500, 180);
        showText("/ to punch", 500, 200);
        showText("↓ to kick", 500, 220);
        
        showText("CHARACTERS:", 400, 260);
        
        showText("There are three characters to choose from:", 400, 300);
        showText("The SPRINTER moves the fastest.", 400, 320);
        showText("The FIGHTER deals the most damage.", 400, 340);
        showText("The JUMPER jumps the highest.", 400, 360);
        
        showText("MATCH RULES:", 400, 400);
        
        showText("Each round has a 30-second timer.", 400, 440);
        showText("Punch and kick your enemies.", 400, 460);
        showText("Move and jump to avoid your enemy's attacks.", 400, 480);
        showText("Whoever depletes their enemy's HP first wins!", 400, 500);
        showText("If the timer runs out but no one has died, it is a DRAW.", 400, 520);
        
        music.play();
    }
}
