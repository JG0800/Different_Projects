import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JPanel {

    SnakeAIPanel dp;
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

    public Node (int col, int row, SnakeAIPanel dp){
        this.dp = dp;
        this.col = col;
        this.row = row;
        setBackground(Color.black);
    }

    public void setAsStart() {
        setBackground(Color.green);
        start = true;
    }

    public void setAsGoal() {
        setBackground(Color.red);
        goal = true;
    }
    public void setAsSolid(){
        setBackground(Color.black);
        solid = true;
    }

    public void setAsOpen(){
        open = true;
    }

    public void setAsChecked(){
    checked = true;
    }

    public void setAsPath(){
        setBackground(Color.blue);
    }

}
