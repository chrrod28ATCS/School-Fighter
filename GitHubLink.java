import greenfoot.*;
import java.awt.Desktop;
import java.net.URI;

public class GitHubLink extends Actor {
    public GitHubLink() {
        setImage(new GreenfootImage("GitHub", 24, Color.WHITE, null));
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            openLink("https://github.com/chrrod28ATCS/School-Fighter.git");
        }
    }

    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {e.printStackTrace();}
    }
}