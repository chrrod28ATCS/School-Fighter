import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Victory extends World {
    private GreenfootSound music = new GreenfootSound("2OutOf3Falls-Results.mp3");

    public Victory(int victorNum, int loserNum, boolean bestOf3, String imgName, int p1Score, int p2Score) {    
        super(800, 600, 1);
        
        addObject(new VictoryMessage(imgName), 400, 275);
        
        if (bestOf3) {
            showText("Player " + victorNum + " has won 2 Out Of 3 Falls! Congratulations!!", 400, 475);
        } else {
            showText("Player " + victorNum + " has won! Congratulations!!", 400, 475);
        }
        showText("Player " + victorNum + "'s score: " + p1Score, 400, 500);
        showText("Player " + loserNum + "'s score: " + p2Score, 400, 525);
        
        music.playLoop();
    }
}
