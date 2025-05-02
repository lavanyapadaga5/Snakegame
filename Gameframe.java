package Play;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Gameframe extends JFrame {
    public Gameframe() {
        SnakePanel gp = new SnakePanel();
        
        this.setTitle("Snakeladder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(gp);
        this.setPreferredSize(new Dimension(SnakePanel.PANEL_WIDTH, SnakePanel.PANEL_HEIGHT)); // Corrected
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); // Center the frame
    }

    public static void main(String[] args) {
        new Gameframe(); // Call the constructor properly
    }
}
