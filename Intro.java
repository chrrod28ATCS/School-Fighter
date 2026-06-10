import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Intro extends Actor {
    private GreenfootImage[] frames;
    private int frame = 0;
    private int timer = 60;
    
    public Intro() {
        frames = new GreenfootImage[4];
        
        for (int i = 0; i < 4; i++) {
            frames[i] = new GreenfootImage("Intro" + i + ".png");
        }
        
        setImage(frames[0]);
    }
    
    public void act() {
        timer--;
        
        if (timer <= 0 && frame < 4) {
            frame++;
            setImage(frames[frame]);
            timer = 60;
        }
    }
}
