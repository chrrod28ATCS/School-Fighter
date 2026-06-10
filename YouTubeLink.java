import greenfoot.*;
import java.awt.Desktop;
import java.net.URI;

public class YouTubeLink extends Actor {
    public YouTubeLink() {
        setImage(new GreenfootImage("My YouTube", 24, Color.RED, null));
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            openLink("https://www.youtube.com/channel/UCJkvzCdUuA5q3SGIbhY5v7g");
        }
    }

    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {e.printStackTrace();}
    }
}