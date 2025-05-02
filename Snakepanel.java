package Play;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements ActionListener {
    static final int PANEL_HEIGHT = 500;
    static final int PANEL_WIDTH = 500;
    static final int UNIT_SIZE = 20;
    static final int NUM_OF_UNITS = (PANEL_HEIGHT * PANEL_WIDTH) / (UNIT_SIZE * UNIT_SIZE);

    final int x[] = new int[NUM_OF_UNITS];
    final int y[] = new int[NUM_OF_UNITS];

    int snakeLength = 5;
    int foodSwallowed = 0;
    private char direction = 'R'; // Start moving right
    int foodX;
    int foodY;
    Random random;
    Timer timer;
    boolean running = false;

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public SnakePanel() {
        random = new Random();
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        this.setBackground(Color.cyan);
        this.setFocusable(true);
        startGame();
    }

    public void move() {
        for (int i = snakeLength; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'L':
                x[0] -= UNIT_SIZE;
                break;
            case 'R':
                x[0] += UNIT_SIZE;
                break;
            case 'U':
                y[0] -= UNIT_SIZE;
                break;
            case 'D':
                y[0] += UNIT_SIZE;
                break;
        }
    }

    public void checkCollision() {
        // Check if the snake collides with itself
        for (int i = snakeLength; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }

        // Check if the snake collides with the borders
        if (x[0] < 0 || x[0] > PANEL_WIDTH || y[0] < 0 || y[0] > PANEL_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void spawnFood() {
        foodX = random.nextInt(PANEL_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        foodY = random.nextInt(PANEL_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    public void checkFood() {
        if (x[0] == foodX && y[0] == foodY) {
            snakeLength++;
            foodSwallowed++;
            spawnFood();
        }
    }

    public void startGame() {
        running = true;
        spawnFood();
        timer = new Timer(130, this);
        timer.start();
    }

    public void draw(Graphics graphics) {
        if (running) {
            graphics.setColor(new Color(214, 0, 0));
            graphics.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

            graphics.setColor(Color.white);
            graphics.fillRect(x[0], y[0], UNIT_SIZE, UNIT_SIZE);

            for (int i = 1; i < snakeLength; i++) {
                graphics.setColor(new Color(212, 100, 215));
                graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            // Display score
            graphics.setColor(Color.red);
            graphics.setFont(new Font("Sans Serif", Font.BOLD, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + foodSwallowed, (PANEL_WIDTH - metrics.stringWidth("Score: " + foodSwallowed)) / 2, 30);
        } else {
            gameOver(graphics);
        }
    }

    public void gameOver(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sans Serif", Font.BOLD, 25));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over", (PANEL_WIDTH - metrics.stringWidth("Game Over")) / 2, PANEL_HEIGHT / 2);

        graphics.setColor(Color.red);
        graphics.setFont(new Font("Sans Serif", Font.BOLD, 25));
        metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Score: " + foodSwallowed, (PANEL_WIDTH - metrics.stringWidth("Score: " + foodSwallowed)) / 2, PANEL_HEIGHT / 2 + 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkFood();
            checkCollision();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }
}
