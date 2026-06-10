import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Credits extends World {
    private GreenfootSound music = new GreenfootSound("Credits.mp3");
    
    public Credits() {    
        super(800, 600, 1); 
        
        showText("All programming: Christian Rodriguez", 400, 75);
        showText("Made in Greenfoot", 400, 100);
        showText("Backgrounds and sprites made in Piskelapp.com", 400, 125);
        showText("SFX: Pixabay.com, Super Smash Bros. Melee, Super Mario Bros., Mario Kart 8", 400, 150);
        showText("Music: Sonic the Hedgehog 2, Sonic 3 & Knuckles, Mario Kart Wii, Sonic Mania Plus", 400, 175);
        showText("Playtesters:", 400, 225);
        showText("Andrew Goddard, Sammy Shin", 400, 250);
        showText("Special Thanks:", 400, 300);
        showText("Mr. Sen", 400, 325);
        showText("Nicholas Bobo", 400, 350);
        showText("Shahzaib Maqsud", 400, 375);
        showText("Ayush Kar", 400, 400);
        showText("Thomas 'Big Tommy' Torossian", 400, 425);
        showText("ChatGPT", 400, 450);
        showText("The Spriters Resource (spriters-resource.com)",400, 450);
        showText("Greenfoot.org Forums", 400, 475);
        
        addObject(new LeaderboardLink(), 400, 525);
        addObject(new YouTubeLink(), 400, 550);
        
        music.playLoop();
    }
    
    public void stopped() {
        music.stop();
    }
}
