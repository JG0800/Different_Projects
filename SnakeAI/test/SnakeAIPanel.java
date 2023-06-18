import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SnakeAIPanel extends JPanel {
    //SCREEN SETTINGS
    final int maxCol = 30;
    final int maxRow = 20;
    final int nodeSize = 50;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;
    int startCol = 0;
    int startRow = 0;

    //NODE
    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();

    //OTHERS
    boolean goalReached = false;

    public SnakeAIPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxRow,maxCol));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);


        //PLACE NODES ON EVERY ELEMENT
        int col = 0;
        int row = 0;

        while(col < maxCol && row < maxRow){
            node[col][row] = new Node(col, row, this);
            this.add(node[col][row]);
            col++;
            if (col == maxCol){
                col = 0;
                row++;
            }
        }
        //SET START AND GOAL NODE
        setStartNode(startCol,startRow);
        setGoalNode(23,14);

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
            //
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
        int xDistance = Math.abs(node.col - startNode.col); //Math.abs = only positive numbers
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        //GET H COST (The distance from the goal node)
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        //GET F COST (The total cost)
        node.fCost = node.gCost + node.hCost;

        //DISPLAY THE COST ON NODE
//        if (node != startNode && node != goalNode){
//            node.setText("<html>F: "+node.fCost+"<br>G: "+node.gCost+"<br>H: "+node.hCost+"</html>");
//        }
    }

//    public void search(){
//
//        if (goalReached == false){
//            int col = currentNode.col; //3
//            int row = currentNode.row; //6
//
//            currentNode.setAsChecked();
//            checkedList.add(currentNode);
//            openList.remove(currentNode);
//
//            //OPEN THE UP NODE
//            if(row -1 >= 0){
//                openNode(node[col][row-1]);
//            }
//
//            //OPEN THE LEFT NODE
//            if(col -1 >= 0){
//                openNode(node[col-1][row]);
//            }
//
//            //OPEN THE DOWN NODE
//            if(row +1 <= maxRow){
//                openNode(node[col][row+1]);
//            }
//
//            //OPEN THE RIGHT NODE
//            if(col +1 <= maxCol){
//                openNode(node[col+1][row]);
//            }
//            System.out.println(openList.size());
//            //FIND THE BEST NODE
//            int bestNodeIndex = 0;
//            int bestNodefCost = 999;
//
//            for (int i = 0; i < openList.size(); i++){
//                //Check if this Nodes's F cost is better
//
//                if(openList.get(i).fCost < bestNodefCost){
//                    bestNodeIndex = i;
//                    bestNodefCost = openList.get(i).fCost; //=11
//
//                }
//                //if F cost is equal, check the G cost
//                else if(openList.get(i).fCost == bestNodefCost){
//                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
//                        bestNodeIndex = i;
//                    }
//                }
//            }
//            //After the Loop, we get the best node which is our next stop
//            currentNode = openList.get(bestNodeIndex);
//
//            if(currentNode == goalNode){
//               goalReached = true;
//            }
//        }
//    }

    public void autoSearch(){
        while (goalReached == false){
            int col = currentNode.col; //3, because its right now the startNode
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
            if(row +1 < maxRow){
                openNode(node[col][row+1]);
            }

            //OPEN THE RIGHT NODE
            if(col +1 < maxCol){
                openNode(node[col+1][row]);
            }



            //FIND THE BEST NODE ??
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); i++){
                //Check if this Nodes's F cost is better

                if(openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i; //Index of the Node with best FCost
                    bestNodefCost = openList.get(i).fCost; // is now 11 e.g.

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
                setStartNode(startCol+1,startRow+1);
                trackThePath();
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
    private void trackThePath(){
        //Backtrack and draw the best path
        Node current = goalNode;
        while(current != startNode){
            current = current.parent;
            if(current != startNode){
                current.setAsPath();
            }
        }
    }
    public void restart(){
        int col = 0;
        int row = 0;

        while (col < maxCol && row < maxRow) {
            if (node[col][row] != startNode && node[col][row] != goalNode && node[col][row].solid == false)
                node[col][row].setBackground(Color.white);
                        col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }
    }
}

