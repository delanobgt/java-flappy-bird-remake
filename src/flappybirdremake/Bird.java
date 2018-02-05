package flappybirdremake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JPanel;

public class Bird {
    
    private final int WIDTH;
    private final int HEIGHT;
    private int posX;
    private double posY;
    private int radius = 12;
    private final double maxVelY = -2.75;
    private final double minVelY = 4;
    private double velY = 2;
    private double accY = 0.09;
    private boolean dead = false;
    private double angle = 0;
    private double angleVel = 0;
    private double angleAcc = 0.03;
    
    
    public Bird(int xPos, int yPos, JPanel canvas) {
        this.posX = xPos;
        this.posY = yPos;
        this.WIDTH = canvas.getWidth();
        this.HEIGHT = canvas.getHeight();
    }
    
    public void update() {
        posY += velY;
        velY = Math.min(velY+accY, minVelY);
        
        angle = Math.min(90, angle+angleVel);
        angleVel += angleAcc;
    }
    
    public void jump() {
        if (!dead) {
            velY = maxVelY;
            angleVel = 0;
            angle = -30;
        }
    }
    
    public void die() {
        dead = true;
    }
    public boolean isDead() {
        return dead;
    }
    
    public void draw(Graphics2D g) {
        g.setColor(Color.YELLOW);
//        g.fillOval(posX-radius, (int)(posY-radius), 2*radius, 2*radius);
        Image birdImg = SpriteLoader.getBirdAnimation().getImage();
        g.translate(posX, (int)(posY));
        g.rotate(Math.toRadians(angle));
        g.drawImage(birdImg, -radius, -radius, null);
        g.rotate(-Math.toRadians(angle));
        g.translate(-posX, -(int)(posY));
    }
    
    public boolean hasCrashedPillar(Pillar pillar) {
        Rectangle[] rects = pillar.getNextBounds();
        int top = (int)(posY+radius+velY);
        int bottom = (int)(posY-radius+velY);
        int left = posX-radius;
        int right = posX+radius;
        for (Rectangle rect : rects) {
            if (rect.x <= posX && posX <= rect.x+rect.width) {
                if ((rect.y <= top && top <= rect.y+rect.height) ||
                    (rect.y <= bottom && bottom <= rect.y+rect.height))
                    return true;
            } else if (rect.y <= posY && posY <= rect.y+rect.height) {
                if ((rect.x <= left && left <= rect.x+rect.width) ||
                    (rect.x <= right && right <= rect.x+rect.width))
                    return true;
            }
        }
        return false;
    }
}

