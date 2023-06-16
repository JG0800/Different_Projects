import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {

    Node parent;
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

    public Node (int col, int row){
        this.col = col;
        this.row = row;
        setBackground(Color.white);
        setForeground(Color.black);
        addActionListener(this);
    }

    public void setAsStart() {
        setBackground(Color.blue);
        setForeground(Color.white);
        setText("start");
        start = true;
    }

    public void setAsGoal() {
        setBackground(Color.yellow);
        setForeground(Color.black);
        setText("Goal");
        goal = true;
    }
    public void setAsSolid(){
        setBackground(Color.black);
        setForeground(Color.black);
        solid = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setBackground(Color.orange);
    }
}
