import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class KeyHandler implements KeyListener {
    DemoPanel dp;
    Timer timer;
    TimerTask task;

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
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    dp.autoSearch();
                }
            };
            timer.scheduleAtFixedRate(task, 10,10);
        }
        else if (e.getKeyCode() == KeyEvent.VK_F){
            timer.cancel();
            dp.restart();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
