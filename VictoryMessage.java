import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class VictoryMessage extends Actor {
    private String imgName;
    
    public VictoryMessage(String imgName) {
        this.imgName = imgName;
        GreenfootImage img = new GreenfootImage(imgName);
        setImage(img);
    }
}
