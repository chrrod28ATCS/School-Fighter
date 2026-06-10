import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class TitleScreen extends World {
    private GitHubLink ghl;
    private GreenfootSound music = new GreenfootSound("TitleScreen.mp3");
    
    public TitleScreen() {    
        super(800, 600, 1); 
        
        Leaderboard.loadScores();
        
        ghl = new GitHubLink();
        addObject(new GitHubLink(), 750, 25);
        
        addObject(new Title(), 390, 200);
        addObject(new TitleButton("Play"), 150, 450);
        addObject(new TitleButton("HowToPlay"), 375, 450); 
        addObject(new TitleButton("Credits"), 625, 450);
        showText("2026 Midyear and Year End Project by Christian Rodriguez", 400, 575);
        showText("Version 1.3.0 (11 June 2026)", 145, 25);
        
        music.play();
    }
    
    public void stopped() {music.stop();}
}
