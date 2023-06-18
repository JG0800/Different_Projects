import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    DemoPanel dp;


    public KeyHandler(DemoPanel dp){
        this.dp = dp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (e.getKeyCode() == 32){
            dp.autoSearch();
        }
        else if (e.getKeyCode() == KeyEvent.VK_F){
            dp.restart();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
