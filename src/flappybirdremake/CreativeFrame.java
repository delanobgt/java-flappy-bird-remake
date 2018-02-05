package flappybirdremake;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreativeFrame extends JFrame {

    private int offsetX;
    private int offsetY;
    private static final int TITLE_BAR_HEIGHT = 20;
    
    public CreativeFrame(JPanel canvas) {
        setLayout(null);
        setUndecorated(true);
        setSize(canvas.getWidth(), TITLE_BAR_HEIGHT+canvas.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addAll(canvas);
        
        JPanel pnlTitle = new JPanel();
        pnlTitle.setLayout(null);
        pnlTitle.setBounds(0, 0, canvas.getWidth(), TITLE_BAR_HEIGHT);
        pnlTitle.setBackground(new Color(255, 255, 255));
        add(pnlTitle);
        pnlTitle.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                offsetX = e.getX();
                offsetY = e.getY();
            }
        });
        pnlTitle.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(
                        e.getLocationOnScreen().x-offsetX,
                        e.getLocationOnScreen().y-offsetY
                );
            }
        });
        
        JLabel lblClose = new JLabel("X");
        lblClose.setBounds(pnlTitle.getWidth()-(int)(pnlTitle.getHeight()*1.5), 0, (int)(pnlTitle.getHeight()*1.5), pnlTitle.getHeight());
        lblClose.setForeground(Color.WHITE);
        lblClose.setBackground(Color.RED);
        lblClose.setOpaque(true);
        lblClose.setHorizontalAlignment(JLabel.CENTER);
        lblClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) { lblClose.setBackground(Color.RED); }
            @Override
            public void mouseEntered(MouseEvent e) { lblClose.setBackground(new Color(180, 0, 0)); }
            @Override
            public void mouseReleased(MouseEvent e) { System.exit(0); }
        });
        pnlTitle.add(lblClose);
    }
    
    private void addAll(JPanel canvas) {
        canvas.setBounds(0, TITLE_BAR_HEIGHT, canvas.getWidth(), canvas.getHeight());
        add(canvas);
    }
}
