import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player1 extends Actor
{
    private GreenfootImage punchImageLeft;
    private GreenfootImage punchImageRight;
    private GreenfootImage kickImageLeft;
    private GreenfootImage kickImageRight;
    
    private GreenfootImage[] moveFrames;
    private int frameIndex = 0;
    private int frameTimer = 0;

    private boolean facingRight = true;
    
    private boolean moving = false;
    private int animationSpeed = 8;

    private int punchTimer = 0;
    private int lingerTimer = 0;
    
    private int vSpeed = 0;    // vertical speed
    private int gravity = 1;   // how fast the player falls
    private boolean onGround = true;  // true if player is standing
    
    public int hp = 1000;
    final int MAX_HP = 1000;
    boolean punching = false;
    
    public Player1(String color) {
        punchImageLeft = new GreenfootImage(color + "PunchLeft.png");
        punchImageRight = new GreenfootImage(color + "Punch.png");
        kickImageLeft = new GreenfootImage(color + "KickLeft.png");
        kickImageRight = new GreenfootImage(color + "Kick.png");
        
        moveFrames = new GreenfootImage[4];

        moveFrames[0] = new GreenfootImage(color + "Move1.png");
        moveFrames[1] = new GreenfootImage(color + "Stand.png");
        moveFrames[2] = new GreenfootImage(color + "Move2.png");
        moveFrames[3] = new GreenfootImage(color + "Stand.png");

        setImage(moveFrames[1]);
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        MyWorld world = (MyWorld)getWorld();
        if (!world.fightStarted()) {
            return;
        }
        
        checkP2Death();
        
        if (!punching) {
            animateMovement();
        }
        
        checkPunch();
        updatePunch();
        
        jump();
        fall();
        
        checkKeyPress();
    }
    
    /**
     * Checks key presses and translates them to movement.
     */
    private void checkKeyPress() {
        moving = false;
        int speed = 4;
        
        if (Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("d")) {
            setLocation(getX() - speed, getY());
            moving = true;
            
            facingRight = false;
        }
        else if (Greenfoot.isKeyDown("d") && !Greenfoot.isKeyDown("a")) {
            setLocation(getX() + speed, getY());
            moving = true;
            
            facingRight = true;
        }
    }
    
    /**
     * Checks key press for punching.
     * Sets punching variable to true, image to punchImage, and activates punchTimer.
     */
    private void checkPunch() {
        if (Greenfoot.isKeyDown("f") && !punching) {
            punching = true;
            Greenfoot.playSound("punch.mp3");
            
            if (!facingRight) {
                setImage(punchImageLeft);
            } else {
                setImage(punchImageRight);
            }
            
            punchTimer = 10; // how long the punch lasts (frames)
            lingerTimer = 12;
        }
        if (Greenfoot.isKeyDown("s") && !punching) {
            punching = true;
            Greenfoot.playSound("punch.mp3");
            
            if (!facingRight) {
                setImage(kickImageLeft);
            } else {
                setImage(kickImageRight);
            }
            
            punchTimer = 10;
            lingerTimer = 12;
        }
    }

    /** 
     * Checks damage while punching and decreases punchTimer.
     * If punchTimer is 0, go back to normal.
    */
    private void updatePunch() {
        if (!punching) return;
        
        if (punchTimer > 0) {
            punchTimer--;
            checkHit();
        }
        
        if (lingerTimer > 0) {
            lingerTimer--;
        } else {
            punching = false;
            GreenfootImage img = new GreenfootImage(moveFrames[1]);
            if (!facingRight) img.mirrorHorizontally();
            setImage(img);
        }
    }
    
    /**
     * Checks if Player1's punch landed on Player2.
    */
    private void checkHit() {
        Player2 other = (Player2)getOneIntersectingObject(Player2.class);

        if (other != null) {
            other.takeDamage(10);
        }
    }
    
    /**
     * Takes damage if being attacked by Player2.
    */
    public void takeDamage(int amt) {
        hp -= amt;
        if (hp < 0) hp = 0;
    }
    
    private void checkP2Death() {
        Player2 other = (Player2)getOneIntersectingObject(Player2.class);
        if (isTouching(Player2.class) && isPunching() && other.hitPoints() <= 0) {
            removeTouching(Player2.class);
                        
            GreenfootImage img = new GreenfootImage("Player1Wins.png");
            GreenfootImage bg = getWorld().getBackground();
            bg.drawImage(img, (800/2)-img.getWidth()/2, 600/2-img.getHeight()/2);
            
            Greenfoot.playSound("Kill.mp3");
            Greenfoot.playSound("fanfare.wav");
            Greenfoot.stop();
        }
    }
    
    private void animateMovement() {
        if (moving && !punching) {
            frameTimer--;

            if (frameTimer <= 0) {
                frameIndex = (frameIndex + 1) % moveFrames.length;
                GreenfootImage img = new GreenfootImage(moveFrames[frameIndex]);
                if (!facingRight) {
                    img.mirrorHorizontally();
                }
                setImage(img);
                frameTimer = animationSpeed;
            }
        } else {
            GreenfootImage img = new GreenfootImage(moveFrames[1]);
            if (!facingRight) {
                img.mirrorHorizontally();
            }
            setImage(img);
        }
    }
    
    /** 
     * Makes Player1 jump. 
    */
    private void jump() {
        if (Greenfoot.isKeyDown("w") && onGround) {
            moving = true;
            vSpeed = -22; // negative because it needs to go up, which means the value of y decreases
            onGround = false;
            Greenfoot.playSound("jump.mp3");
        }
    }

    /**
     * Applies gravity after jump.
    */
    private void fall() {
        vSpeed += gravity;
        setLocation(getX(), getY() + vSpeed); 

        // check if player hits the ground
        if (getY() >= 454) {
            setLocation(getX(), 455);
            vSpeed = 0;
            onGround = true;
        }
    }
    
    public boolean isPunching() {
        return punching;
    }
    
    public int hitPoints() {
        return hp;
    }
    
    public int maxHP() {
        return MAX_HP;
    }
}
