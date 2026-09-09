import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class LeaderboardScreen extends World {
    private GreenfootSound music = new GreenfootSound("Leaderboard.mp3");
    
    public LeaderboardScreen() {    
        super(800, 600, 1); 
        
        showText("LEADERBOARD", 400, 50);
        int[] leaderboard = Leaderboard.getLB();
        String[] dates = Leaderboard.getDates();
        
        for (int i = 0; i < Leaderboard.getCount(); i++) {
            showText((i + 1) + ". " + leaderboard[i] + " (" + dates[i] + ")", 400, 120 + i * 40);
        }
        
        music.playLoop();
    }
}