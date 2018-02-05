package flappybirdremake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyCanvas extends JPanel {
    
    private int width;
    private int height;
    private final int PILLAR_CREATE_DELAY = 160;
    private final int ANIMATION_DELAY = 10;
    private Timer timer;
    private Bird bird;
    private List<Pillar> pillarList = new ArrayList<>();
    private int pillarCounter = 0;
    
    public FlappyCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        setSize(width, height);
        setBackground(Color.MAGENTA);
        
        this.bird = new Bird(width/5, height/2, this);
        this.timer = new Timer(ANIMATION_DELAY, e -> {
            if (pillarCounter == 0)
                pillarList.add(new Pillar(this));
            pillarCounter = (pillarCounter+1)%PILLAR_CREATE_DELAY;
            update();
            repaint();
        });
        this.timer.start();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bird.jump();
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                    bird.jump();
            }
            
        });
    }
    
    public void addKeyListenerToFrame(JFrame frame) {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                    bird.jump();
            }
        });
    }
    
    private void update() {
        // delete off-screen pillars
        for (int i = pillarList.size()-1; i >= 0; i--) {
            if (pillarList.get(i).isOffScreen())
                pillarList.remove(i);
        }
        // update pillars
        if (!bird.isDead()) {
            pillarList.stream().forEach(Pillar::update);
        }
        // check for crash
        if (!bird.isDead()) {
            for (Pillar p : pillarList) {
                if (bird.hasCrashedPillar(p)) {
                    System.out.println("lost!!");
                    bird.die();
                    break;
                }
            }
        }
        //update dat bird
        bird.update();
    }

    @Override
    protected void paintComponent(Graphics oldG) {
        super.paintComponent(oldG);
        Graphics2D g = (Graphics2D) oldG;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.drawImage(SpriteLoader.getBackground().getImage(), 0, 0, this);
        
        pillarList.stream().forEach(e -> e.draw(g));
        bird.draw(g);
    }
}
