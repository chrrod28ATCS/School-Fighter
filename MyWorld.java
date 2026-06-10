import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World {
    Player1 p1;
    Player2 p2;
    Intro intro;
    Paused pauseSprite;
    
    private Character p1Character;
    private Character p2Character;
    
    private int timer = 240;
    private int matchTimer = 1800;
    
    public boolean fightStarted = false;
    private boolean timeUp = false;
    private boolean roundOver = false;
    public boolean paused = false;
    private boolean escHeld = false;
    
    private int p1Falls = 0;
    private int p2Falls = 0;
    private boolean bestOf3;
    
    private int p1Score;
    private int p2Score;
    
    private GreenfootSound music1 = new GreenfootSound("Fight.mp3");
    private GreenfootSound music2 = new GreenfootSound("2OutOf3Falls-FinalRound.mp3");
    private GreenfootSound count = new GreenfootSound("3 2 1.mp3");
    
    public MyWorld(Character p1Character, Character p2Character, boolean bestOf3, 
        int p1Falls, int p2Falls, int p1Score, int p2Score) {    
        super(800, 600, 1); 
        
        this.p1Character = p1Character;
        this.p2Character = p2Character;
        this.bestOf3 = bestOf3;
        this.p1Falls = p1Falls;
        this.p2Falls = p2Falls;
        this.p1Score = p1Score;
        this.p2Score = p2Score;
        
        int rand = (int)(Math.random()*6);
        GreenfootImage bg = new GreenfootImage("bg" + (rand+1) + ".png");
        bg.scale(800, 600);
        setBackground(bg);
        
        p1 = new Player1(p1Character); // add Player1
        p1.score = p1Score;
        addObject(p1, 200, 455);
        addObject(new HPBar(p1), 200, 40);
        
        p2 = new Player2(p2Character); // add Player2
        p2.score = p2Score;
        addObject(p2, 600, 455);
        addObject(new HPBar(p2), 600, 40);
                
        showText("Player 1", 200, 20);
        showText("Player 2", 600, 20);
        
        if (bestOf3) {
            showText("Falls: " + p1Falls, 200, 80);
            showText("Falls: " + p2Falls, 600, 80);
        }
        
        intro = new Intro();
        pauseSprite = new Paused();
        
        addObject(intro, 400, 300);
        count.play();
    }
    
    public void act() {
        showText("Score: " + p1.score, 200, 60);
        showText("Score: " + p2.score, 600, 60);
        
        pauseMenu();
        if (paused) return;
        
        if (!fightStarted) {
            timer--;
            
            showText("WAD to control", 200, 350);
            showText("F to punch, S to kick", 200, 375);
            showText("Arrows to control", 600, 350);
            showText("/ to punch, down to kick", 600, 375);
            if (timer <= 60) {
                showText("", 200, 350);
                showText("", 200, 375);
                showText("", 600, 350);
                showText("", 600, 375);
            }
            if (timer <= 0) {
                fightStarted = true;
                removeObject(intro);
                if ((bestOf3) && (p1Falls == 1) && (p2Falls == 1)) {
                    music2.playLoop();
                } else {
                    music1.playLoop();
                }
                            }
        } else if (!timeUp) {
            matchTimer--;
            int seconds = matchTimer/60;
            showText("" + seconds, 400, 40);
            
            if (matchTimer <= 0) {
                timeUp = true;
                GreenfootImage img = new GreenfootImage("Draw.png");
                getBackground().drawImage(img, 400 - img.getWidth()/2, 300 - img.getHeight()/2);
                Greenfoot.playSound("Kill.mp3");
                if (!bestOf3) {
                    Greenfoot.stop();
                } else {
                    startNextRound();
                }
            }
        }
    }
    
    public void pauseMenu() {
        if (Greenfoot.isKeyDown("escape")) {
            if (!escHeld) {
                escHeld = true;
                paused = !paused;
                
                if (paused) {
                    addObject(pauseSprite, 400, 300);
                } else {
                    removeObject(pauseSprite);
                }
                Greenfoot.playSound("SMBPause.mp3");
            }
        } else {
            escHeld = false;
        }
    }
    
    public boolean fightStarted() {
        return fightStarted;
    }
    
    private void showWinner(String imgName) {
        GreenfootImage img = new GreenfootImage(imgName);
        addObject(new VictoryMessage(imgName), 400, 300);
        Greenfoot.playSound("Kill.mp3");
        Greenfoot.playSound("fanfare.wav");
        stopped();
    }
    
    private void startNextRound() {
        Greenfoot.delay(240);
        
        Greenfoot.setWorld(new MyWorld(p1Character, p2Character, true, p1Falls, p2Falls, p1.score, p2.score));
    }
    
    public void p1ScoresFall() {
        if (roundOver) return;
        roundOver = true;
        
        p1Falls++;
        if (!bestOf3) {
            showWinner("Player1Wins.png");
            Greenfoot.delay(240);
            Greenfoot.setWorld(new Victory(1, 2, false, "Player1Wins.png", p1.score, p2.score));
            return;
        }
        if (p1Falls >= 2) {
            showWinner("Player1Wins.png");
            Greenfoot.delay(240);
            Greenfoot.setWorld(new Victory(1, 2, true, "Player1Wins.png", p1.score, p2.score));
            if (p1.score > 1000 || p2.score > 1000) {
                Leaderboard.addScore(Math.max(p1.score, p2.score));
            }
            return;
        } else {
            showWinner("Player1Wins.png");
            startNextRound();
        }
    }
    
    public void p2ScoresFall() {
        if (roundOver) return;
        roundOver = true;
        
        p2Falls++;
        if (!bestOf3) {
            showWinner("Player2Wins.png");
            Greenfoot.delay(240);
            Greenfoot.setWorld(new Victory(2, 1, false, "Player2Wins.png", p2.score, p1.score));
            return;
        }
        if (p2Falls >= 2) {
            showWinner("Player2Wins.png");
            Greenfoot.delay(240);
            Greenfoot.setWorld(new Victory(2, 1, true, "Player2Wins.png", p2.score, p1.score));
            if (p2.score > 1000 || p1.score > 1000) {
                Leaderboard.addScore(Math.max(p1.score, p2.score));
            }
            return;
        } else {
            showWinner("Player2Wins.png");
            startNextRound();
        }
    }
    
    public void stopped() {
        music1.stop();
        music2.stop();
    }
}
