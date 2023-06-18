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

    public Node (int col, int row, DemoPanel dp){
        this.dp = dp;
        this.col = col;
        this.row = row;
        setBackground(Color.white);
        setForeground(Color.black);
        addActionListener(this);
    }

    public void setAsStart() {
        setBackground(Color.blue);
        setForeground(Color.white);
        start = true;
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
            dp.autoSearch();
        }
        else if(solid == true){
            setBackground(Color.white);
            setForeground(Color.black);
            solid = false;
        }
        else if(goal == true){
            dp.restart();
        }
        else if (goal == false){
            setAsSolid();
        }
        dp.requestFocusInWindow();
    }
}
