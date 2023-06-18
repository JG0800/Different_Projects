import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JFrame {

    GameFrame() {
        //set up the GameFrame
        GamePanel GP = new GamePanel();
        this.add(GP);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        pack();
        this.setLocationRelativeTo(null);

    }
}

