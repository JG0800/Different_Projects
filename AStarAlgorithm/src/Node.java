import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {

    DemoPanel dp;
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
    boolean empty;

    public Node (int col, int row, DemoPanel dp){
        this.dp = dp;
        this.col = col;
        this.row = row;
        setBackground(Color.white);
        setForeground(Color.black);
        addActionListener(this);
    }

    public void setAsEmpty(){
        setBackground(Color.white);
        setForeground(Color.black);
        start = false;
        solid = false;
        goal = false;
        empty = true;
    }

    public void setAsStart() {
        setBackground(Color.blue);
        setForeground(Color.white);
        start = true;
        dp.startSet = true;
    }

    public void setAsGoal() {
        setBackground(Color.yellow);
        setForeground(Color.black);
        goal = true;
    }
    public void setAsSolid(){
        setBackground(Color.black);
        setForeground(Color.black);
        solid = true;
    }

    public void setAsOpen(){
        open = true;
    }

    public void setAsChecked(){
        if (start == false && goal ==false){
            setBackground(Color.orange);
            setForeground(Color.black);
            dp.repaint();
            }
    checked = true;
    }
    public void setAsPath(){
        setBackground(Color.green);
        setForeground(Color.black);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (start == true ){
            setAsEmpty();

        }
        else if(solid == true) {
            setAsEmpty();
        }
        else if(goal == true){
            dp.restart();
        }
        else if (goal == false){
            setAsSolid();
        }
        else if (empty == true){
            setAsStart();
        }
        dp.requestFocusInWindow();
    }

}
