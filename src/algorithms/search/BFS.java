package algorithms.search;

import algorithms.Algorithm;
import algorithms.DIRECTION;
import algorithms.Helper;
import algorithms.Node;

import java.util.ArrayList;
import java.util.List;

public class BFS implements Algorithm {
    public boolean flag = true;
    public Node lastNode;
    public int resSize = 0;
    public List<Node> openList = new ArrayList<>();
    public List<Node> closeList = new ArrayList<>();
    public List<DIRECTION> resList = new ArrayList<>();

    @Override
    public String makeMove(String[][] grid) {

        if (flag) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j].equals(" ")) {
                        grid[i][j] = "0";
                        break;
                    }
                }
            }



            BFSearch(grid);
            flag = false;
        }

/*        System.out.println(resSize);*/
        if (resSize > 0) {
            DIRECTION dir = resList.get(resSize-1);
            resSize -- ;
            switch (dir) {
                case Right:

                           return ("Right");
                case Left:

                            return ("Left");

                case Down:

                          return ("Down");

                case Up:

                           return ("Up");

            }
        }

        return null;
    }

    public void BFSearch(String[][] grid) {
        Node root = new Node(null, grid, DIRECTION.Root);
        openList.add(root);
        boolean isFinish = false;

        while (!openList.isEmpty() & !isFinish) {
            Node currNode = openList.get(0);
            openList.remove(0);
            closeList.add(currNode);

            int blank_x = 0, blank_y = 0;
            if(flag) {

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (currNode.grid[i][j].equals("0")) {
                            blank_y = i;
                            blank_x = j;
                            break;
                        }
                    }
                }
                currNode.setEmptyX(blank_x);
                currNode.setEmptyY(blank_y);
            }
            flag=false;

            currNode.expandNode();

            for (int i = 0; i < currNode.children.size(); i++) {
                Node currChild = currNode.children.get(i);
                if (currChild.isGoal(currChild.grid)) {
                    /*System.out.println("goal found");*/
                    isFinish = true;
                    lastNode = currChild;
                    resSize=Helper.trace(resList , lastNode);
                    return;
                }
                else if (!Helper.listContain(openList, currChild) && !Helper.listContain(closeList, currChild)) {
                    openList.add(currChild);
                }

            }

        }
        System.out.println("goal not found");
        return;
    }






}
