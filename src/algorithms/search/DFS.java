package algorithms.search;

import algorithms.Algorithm;
import algorithms.DIRECTION;
import algorithms.Helper;
import algorithms.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFS implements Algorithm {
    public boolean flag = true;
    public Node lastNode;
    public int resSize = 0;
    public Stack<Node> openList = new Stack<>();
    public List<Node> closeList = new ArrayList<>();
    public List<DIRECTION> resList = new ArrayList<>();
    private int depth = 0;

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


             //System.out.println("hhh");
            DFSearch(grid);
            flag = false;
        }
        /*System.out.println("kkk");
        System.out.println(resSize);*/
      //  System.out.println(depth);
        if (resSize >0) {
            DIRECTION dir = resList.get(resSize-1);
           /* System.out.println(dir);*/
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
/*2	5	1
        4	3
        7	6	8*/
/*5	1	6
        7	 	2
        3	4	8*/
    private void DFSearch(String[][] grid) {
        Node root = new Node(null, grid, DIRECTION.Root);
        openList.push(root);
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
            //  System.out.println(blank_x +" "+ blank_y);
            root.setEmptyX(blank_x);
            root.setEmptyY(blank_y);
        }
        flag=false;

        // System.out.println("while");
        while (!openList.isEmpty() & !isFinish & depth <=30000) {
            Node currNode = openList.pop();
            // openList.remove(0);
            closeList.add(currNode);


            if (!currNode.isGoal(currNode.grid)) {
              //  System.out.println("jjjj");
                currNode.expandNode();

                for (int i = 0; i < currNode.children.size(); i++) {
                    Node currChild = currNode.children.get(i);
                    // System.out.println(currChild.direction);
                    // System.out.println();

                    if (/*!Helper.listContain(openList, currChild) && */!Helper.listContain(closeList, currChild)) {
                        //      System.out.println("add child");
                        openList.push(currChild);
                    }

                }
                depth++;
            } else {
                /*System.out.println("goal found");*/
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