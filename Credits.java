import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Credits here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Credits extends World
{
    private GreenfootSound music = new GreenfootSound("Credits.mp3");
    
    /**
     * Constructor for objects of class Credits.
     * 
     */
    public Credits()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        showText("All programming: Christian Rodriguez", 400, 75);
        showText("Made in Greenfoot", 400, 125);
        showText("Backgrounds from various Google Searches", 400, 175);
        showText("Sprites made in Piskelapp.com and Canva", 400, 200);
        showText("Sound effects from Pixabay, Super Smash Bros. Melee, and Mario Kart 8", 400, 225);
        showText("Music by Masato Nakamura (Sonic the Hedgehog 2)", 400, 250);
        showText("and Tomonori Sawada, Michael Jackson and Brad Buxer (Sonic the Hedgehog 3)", 400, 275);
        
        showText("Special Thanks:", 800/2, 325);
        showText("Mr. Sen", 800/2, 350);
        showText("Nicholas Bobo", 800/2, 375);
        showText("Shahzaib Maqsud", 800/2, 400);
        showText("Ayush Kar", 800/2, 425);
        showText("Thomas 'Big Tommy' Torossian", 800/2, 450);
        showText("ChatGPT", 800/2, 475);
        
        showText("Check me out at @CXLeonidas on YouTube!", 800/2, 525);
        
        music.playLoop();
    }
}
