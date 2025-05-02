package Play;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKey extends KeyAdapter {
	private SnakePanel snakepanel;
	public MyKey(SnakePanel snakepanel) {
		this.snakepanel=snakepanel;
	}
	public void keyPressed(KeyEvent e) {
		char currentDirection=snakepanel.getDirection();
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if(currentDirection!='R') {
				snakepanel.setDirection('L');
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(currentDirection!='L') {
				snakepanel.setDirection('R');
			}
			break;
		case KeyEvent.VK_UP:
			if(currentDirection!='D') {
				snakepanel.setDirection('U');
			}
			break;	
		case KeyEvent.VK_DOWN:
			if(currentDirection!='U') {
				snakepanel.setDirection('D');
			}
			break;
		}
	}
}
 
