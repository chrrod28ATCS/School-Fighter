import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player2 extends Actor {
    private int speed;
    private int damage;
    private int jumpPower;
    
    private GreenfootImage punchImageLeft;
    private GreenfootImage punchImageRight;
    private GreenfootImage kickImageLeft;
    private GreenfootImage kickImageRight;
    
    private GreenfootImage[] moveFramesRight, moveFramesLeft;
    private int frameIndex = 0;
    private int frameTimer = 0;

    private boolean facingRight = false;
    private boolean moving = false;
    
    private int animationSpeed = 8;

    private int punchTimer = 0;
    private int lingerTimer = 0;
    
    private int vSpeed = 0;  
    private int gravity = 1;  
    private boolean onGround = true;  
    
    public int hp = 1000;
    final int MAX_HP = 1000;
    public int score = 0;
    boolean punching = false;
    boolean punchConnected = false;

    public Player2(Character character) {
        speed = character.getSpeed();
        damage = character.getDamage();
        jumpPower = character.getJumpPower();
        
        String color = character.getColor();
    
        punchImageRight = new GreenfootImage(color + "Punch.png");
        punchImageLeft = new GreenfootImage(punchImageRight);
        punchImageLeft.mirrorHorizontally();
        
        kickImageRight = new GreenfootImage(color + "Kick.png");
        kickImageLeft = new GreenfootImage(kickImageRight);
        kickImageLeft.mirrorHorizontally();
        
        // Load right movement frames 
        moveFramesRight = new GreenfootImage[4];
        moveFramesRight[0] = new GreenfootImage(color + "Move1.png");
        moveFramesRight[1] = new GreenfootImage(color + "Stand.png");
        moveFramesRight[2] = new GreenfootImage(color + "Move2.png");
        moveFramesRight[3] = new GreenfootImage(color + "Stand.png");
        
        // Create left versions
        moveFramesLeft = new GreenfootImage[4];
        for (int i = 0; i < moveFramesRight.length; i++) {
            moveFramesLeft[i] = new GreenfootImage(moveFramesRight[i]);
            moveFramesLeft[i].mirrorHorizontally();
        }

        setImage(moveFramesRight[1]);
    }
    
    public void act() {
        MyWorld world = (MyWorld)getWorld();
        if (!world.fightStarted() || world.paused) return;
        
        checkP1Death();
        
        if (!punching) {
            animateMovement();
        }
        
        checkPunch();
        updatePunch();
        
        jump();
        fall();
        
        checkKeyPress();
    }
    
    private void checkKeyPress() {
        moving = false;
        int moveSpeed = speed;
        
        if (Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right")) {
            setLocation(getX() - moveSpeed, getY());
            moving = true;
            facingRight = false;
        }
        if (Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("left")) {
            setLocation(getX() + moveSpeed, getY());
            moving = true;
            facingRight = true;
        }
    }
    
    private void checkPunch() {
        if (Greenfoot.isKeyDown("/") && !punching) {
            punching = true;
            punchConnected = false;
            Greenfoot.playSound("punch.mp3");
            
            setImage(facingRight ? punchImageRight : punchImageLeft);
            
            punchTimer = 10; 
            lingerTimer = 12;
        }
        if (Greenfoot.isKeyDown("down") && !punching) {
            punching = true;
            punchConnected = false;
            Greenfoot.playSound("punch.mp3");
            
            setImage(facingRight ? kickImageRight : kickImageLeft);
            
            punchTimer = 10;
            lingerTimer = 12;
        }
    }

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
            setImage(facingRight ? moveFramesRight[1] : moveFramesLeft[1]);
        }
    }
    
    private void checkHit() {
        if (punchConnected) return;
        Player1 other = (Player1)getOneIntersectingObject(Player1.class);

        if (other != null) {
            other.takeDamage(damage);
            score += damage;
            punchConnected = true;
        }
    }
    
    public void takeDamage(int amt) {
        hp -= amt;
        if (hp < 0) hp = 0;
    }
    
    private void checkP1Death() {
        Player1 other = (Player1)getOneIntersectingObject(Player1.class);
        
        if (other != null && isPunching() && other.hitPoints() <= 0) {
            ((MyWorld)getWorld()).p2ScoresFall();
        }
    }
    
    private void animateMovement() {
        if (moving && !punching) {
            frameTimer--;

            if (frameTimer <= 0) {
                frameIndex = (frameIndex + 1) % moveFramesRight.length;
                if (facingRight) {
                    setImage(moveFramesRight[frameIndex]);
                } else {
                    setImage(moveFramesLeft[frameIndex]);
                }
                frameTimer = animationSpeed;
            }
        } else {
            setImage(facingRight ? moveFramesRight[1] : moveFramesLeft[1]);
        }
    }

    private void jump() {
        if (Greenfoot.isKeyDown("up") && onGround) {
            moving = true;
            vSpeed = jumpPower;
            onGround = false;
            Greenfoot.playSound("jump.mp3");
        }
    }
    
    private void fall() {
        vSpeed += gravity;
        setLocation(getX(), getY() + vSpeed); 

        if (getY() >= 454) {
            setLocation(getX(), 455);
            vSpeed = 0;
            onGround = true;
        }
    }
    
    public boolean isPunching() {return punching;}
    
    public int hitPoints() {return hp;}
    
    public int maxHP() {return MAX_HP;}
    
    public void showScore() {
        getWorld().showText("Score: " + score, 600, 60);
    }
}
