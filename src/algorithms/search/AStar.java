package algorithms.search;

import algorithms.Algorithm;
import algorithms.DIRECTION;
import algorithms.Helper;
import algorithms.Node;

import java.util.*;

public class AStar implements Algorithm {
    public boolean flag = true;
    public Node lastNode;
    public int resSize = 0;
    public PriorityQueue openList = new PriorityQueue(new NodeComp());
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

            AStarSearch(grid);
            flag = false;
        }

     /*   System.out.println(resSize);*/
        if (resSize >0) {
            DIRECTION dir = resList.get(resSize-1);
//            System.out.println(dir);
            resSize--;
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

    private void AStarSearch(String[][] grid) {

        Node root = new Node(null, grid, DIRECTION.Root);
        root.gvalue=0;
        root.hvalue=Helper.manhatan(root.grid);
        root.fvalue=Helper.fvalueCal(root);
        openList.add(root);
        boolean isFinish = false;

        int blank_x = 0, blank_y = 0;
        if (flag) {

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (root.grid[i][j].equals("0")) {
                        blank_y = i;
                        blank_x = j;
                        break;
                    }
                }
            }
           /*   System.out.println(blank_x +" "+ blank_y);*/
            root.setEmptyX(blank_x);
            root.setEmptyY(blank_y);
        }
        flag=false;

      //   System.out.println("while");

        while (!openList.isEmpty() & !isFinish ) {
            Node currNode = (Node) openList.poll();
            closeList.add(currNode);


            if (!currNode.isGoal(currNode.grid)) {

                currNode.expandNode();

                for (int i = 0; i < currNode.children.size(); i++) {
                    Node currChild = currNode.children.get(i);

                        currChild.gvalue=currNode.gvalue+1;
                        currChild.hvalue=Helper.manhatan(currChild.grid);
                        currChild.fvalue=Helper.fvalueCal(currChild);
                        openList.add(currChild);

                }
            } else {
               /* System.out.println("goal found");*/
                isFinish = true;
                lastNode = currNode;
                resSize= Helper.trace(resList, lastNode);
                return;
            }

        }
        System.out.println("goal not found");
        return;
    }



}

class NodeComp implements Comparator<Node> {

    public int compare(Node s1, Node s2) {
        if (s1.fvalue > s2.fvalue)
            return 1;
        else if (s1.fvalue < s2.fvalue)
            return -1;
        return 0;
    }
}