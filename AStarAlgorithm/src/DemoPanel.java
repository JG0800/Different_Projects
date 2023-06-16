import javax.swing.*;
import java.awt.*;

public class DemoPanel extends JPanel {
    //SCREEN SETTINGS
    final int maxCol = 15;
    final int maxRow = 10;
    final int nodeSize = 70;
    final int screenWidth = nodeSize *maxCol;
    final int screenHeight = nodeSize * maxRow;

    //NODE
    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;

    public DemoPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxRow,maxCol));

        //PLACE NODES
        int col = 0;
        int row = 0;

        while(col < maxCol && row < maxRow){
            node[col][row] = new Node(col, row);
            this.add(node[col][row]);
            col++;
            if (col == maxCol){
                col = 0;
                row++;
            }
        }
        //SET START AND GOAL NODE
        setStartNode(3,6);
        setGoalNode(11,3);

        //PLACE SOLID NODES
        setSolidNode(10,2);
        setSolidNode(10,3);
        setSolidNode(10,4);
        setSolidNode(10,5);
        setSolidNode(10,6);
        setSolidNode(10,7);

        //SET COSTS
        setCostOnNodes();
    }
    private void setStartNode(int col, int row){
        node[col][row].setAsStart();
        startNode = node [col][row];
        currentNode = startNode;
    }
    private void setGoalNode(int col, int row){
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }
    private void setSolidNode(int col, int row){
        node[col][row].setAsSolid();
    }
    private void setCostOnNodes(){
        int col = 0;
        int row = 0;

        while (col < maxCol && row < maxRow){
            getCost(node[col][row]);
            col++;
            if(col == maxCol){
                col = 0;
                row++;
            }
        }
    }
    private void getCost(Node node){
        //GET G COST (the distance from the start Node)
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        //GET H COST (The distance from the goal node)
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        //GET F COST (The total cost)
        node.fCost = node.gCost + node.hCost;

        //DISPLAY THE COST ON NODE
        if (node != startNode && node != goalNode){
            node.setText("<html>F: "+node.fCost+"<br>G: "+node.gCost+"<br>H: "+node.hCost+"</html>");
        }
    }
}
