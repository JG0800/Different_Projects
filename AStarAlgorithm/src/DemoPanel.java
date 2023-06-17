import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DemoPanel extends JPanel {
    //SCREEN SETTINGS
    final int maxCol = 25;
    final int maxRow = 13;
    final int nodeSize = 75;
    final int screenWidth = nodeSize *maxCol;
    final int screenHeight = nodeSize * maxRow;

    //NODE
    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();

    //OTHERS
    boolean goalReached = false;

    public DemoPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxRow,maxCol));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);

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
        setSolidNode(12,4);
        setSolidNode(12,3);
        setSolidNode(12,2);
        setSolidNode(11,2);
        setSolidNode(10,2);
        setSolidNode(10,3);
        setSolidNode(10,4);
        setSolidNode(10,5);
        setSolidNode(10,6);
        setSolidNode(10,7);
        setSolidNode(11,7);
        setSolidNode(11,8);
        setSolidNode(11,9);

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

    public void search(){

        if (goalReached == false){

            int col = currentNode.col; //3
            int row = currentNode.row; //6

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            //OPEN THE UP NODE
            if(row -1 >= 0){   
                openNode(node[col][row-1]);
            }

            //OPEN THE LEFT NODE
            if(col -1 >= 0){
                openNode(node[col-1][row]);
            }

            //OPEN THE DOWN NODE
            if(row +1 <= maxRow){
                openNode(node[col][row+1]);
            }

            //OPEN THE RIGHT NODE
            if(col +1 <= maxCol){
                openNode(node[col+1][row]);
            }

            //FIND THE BEST NODE
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); i++){
                //Check if this Nodes's F cost is better

                if(openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost; //=11

                }
                //if F cost is equal, check the G cost
                else if(openList.get(i).fCost == bestNodefCost){
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }
            //After the Loop, we get the best node which is our next stop
            currentNode = openList.get(bestNodeIndex);
            
            if(currentNode == goalNode){
               goalReached = true;
            }
        }
    }

    private void openNode(Node node){
        if (node.open == false && node.checked == false && node.solid == false){
            //if the node is not opened yet, add it to the open List
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }
}

