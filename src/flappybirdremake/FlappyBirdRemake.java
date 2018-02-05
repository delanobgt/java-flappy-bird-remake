package flappybirdremake;

import javax.swing.SwingUtilities;

public class FlappyBirdRemake {

    private static int WIDTH = 320;
    private static int HEIGHT = 480;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlappyCanvas canvas = new FlappyCanvas(WIDTH, HEIGHT);
            CreativeFrame frame = new CreativeFrame(canvas);
            canvas.addKeyListenerToFrame(frame);
            frame.setVisible(true);
        });
    }
    
}
