import greenfoot.*;

public class LeaderboardLink extends Actor {
    private Credits credits;
    
    public LeaderboardLink() {
        setImage(new GreenfootImage("Leaderboard", 24, Color.GREEN, null));
    }

    public void act() {
        if (Greenfoot.mouseClicked(this) && getWorld() instanceof Credits) {
            credits = (Credits)getWorld();
            credits.stopped();
            Greenfoot.setWorld(new LeaderboardScreen());
        }
    }
}
