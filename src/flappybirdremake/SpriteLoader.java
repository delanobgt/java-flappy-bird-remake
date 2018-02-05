package flappybirdremake;

import java.awt.Image;
import javax.swing.ImageIcon;

public class SpriteLoader {
    
    private static final ClassLoader Loader = SpriteLoader.class.getClassLoader();
    private static ImageIcon background;
    private static ImageIcon pillarTop;
    private static ImageIcon pillarBottom;
    private static ImageIcon[] birds = {
        new ImageIcon(Loader.getResource("res/bird_1.png")),
        new ImageIcon(Loader.getResource("res/bird_2.png")),
        new ImageIcon(Loader.getResource("res/bird_3.png"))
    };
    private static int birdCounter = 0;
    
    static {
        background = new ImageIcon(Loader.getResource("res/morning_background.jpg"));
        pillarTop = new ImageIcon(Loader.getResource("res/pillar_top.png"));
        pillarBottom = new ImageIcon(Loader.getResource("res/pillar_bottom.png"));
    }
    
    public static ImageIcon getBackground() {
        return background;
    }
    public static ImageIcon getPillarTop() {
        return pillarTop;
    }
    public static ImageIcon getPillarBottom() {
        return pillarBottom;
    }
    
    public static ImageIcon getBirdAnimation() {
        birdCounter = (birdCounter+1) % (birds.length*10);
        return birds[birdCounter/10];
    }
    
    public static ImageIcon getBirdFalling() {
        return birds[1];
    }
}
