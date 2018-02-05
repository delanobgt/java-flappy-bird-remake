package flappybirdremake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Pillar {
    
    private static final int TALLNESS = 85;
    private static final int FATNESS = 50;
    private int width;
    private int height;
    private int x;
    private int y;
    private JPanel canvas;
    
    public Pillar(JPanel canvas) {
       this.canvas = canvas;
       this.width = canvas.getWidth();
       this.height = canvas.getHeight();
       this.x = width;
       this.y = (height/3) + (int)(Math.random()*height/3);
    }
    
    public void update() {
        this.x--;
    }
    
    public void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
//        g.fillRect(x, 0, FATNESS, y);
//        g.fillRect(x, y+TALLNESS, FATNESS, height-y-TALLNESS);
        ImageIcon pillarTop = SpriteLoader.getPillarTop();
        g.drawImage(pillarTop.getImage(), x, -pillarTop.getIconHeight()+y, null);
        g.drawImage(SpriteLoader.getPillarBottom().getImage(), x, y+TALLNESS, null);
    }
    
    public boolean isOffScreen() {
        return x+FATNESS < 0;
    }
    
    public Rectangle[] getNextBounds() {
        return new Rectangle[] {
            new Rectangle(x-1, 0, FATNESS, y),
            new Rectangle(x-1, y+TALLNESS, FATNESS, height-y-TALLNESS)
        };
    }
}
