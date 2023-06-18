import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Nodes extends JButton implements ActionListener {

    GamePanel gp;
    Nodes parent;
    int col;
    int row;
    int gCost;
    int hCost;
    int fCost;
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;

    public Nodes(int col, int row, GamePanel gp) {
        this.gp = gp;
        this.col = col;
        this.row = row;
    }

    public void setAsStart() {
        start = true;
    }

    public void setAsGoal() {
        setBackground(Color.yellow);
        setForeground(Color.black);
        setText("B");
        goal = true;
    }

    public void setAsSolid() {
        setBackground(Color.black);
        setForeground(Color.black);
        solid = true;
    }

    public void setAsOpen() {
        open = true;
    }

    public void setAsChecked() {
        if (start == false && goal == false) {
            setBackground(Color.orange);
            setForeground(Color.black);
        }
        checked = true;
    }

    public void setAsPath() {
        setBackground(Color.green);
        setForeground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (start == true) {
        } else if (solid == true) {
            setBackground(Color.white);
            setForeground(Color.black);
            solid = false;
        } else if (goal == true) {

        } else if (goal == false) {
            setAsSolid();
        }
    }
}