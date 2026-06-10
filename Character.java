import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Character {
    public String name;
    private String color;
    private int speed;
    private int damage;
    private int jumpPower;
    
    public Character(String name, String color, int speed, int damage, int jumpPower) {
        this.name = name;
        this.color = color;
        this.speed = speed;
        this.damage = damage;
        this.jumpPower = jumpPower;
    }
    
    public String getName() {return name;}
    public String getColor() {return color;}
    public int getSpeed() {return speed;}
    public int getDamage() {return damage;}
    public int getJumpPower() {return jumpPower;}
}
