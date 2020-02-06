package algorithms.search;

import algorithms.Algorithm;
import algorithms.DIRECTION;
import algorithms.Helper;
import algorithms.Node;

import java.awt.*;
import java.sql.Struct;
import java.util.*;
import java.util.List;

public class IDAStar implements Algorithm {
    public boolean flag = true;
    public Node lastNode;
    public int resSize = 0;
    public Stack<Node> openList;
    public List<Node> closeList;
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

            IDAStarSearch(grid);
            flag = false;
        }

        /*   System.out.println(resSize);*/
        if (resSize > 0) {
            DIRECTION dir = resList.get(resSize - 1);
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

    boolean goalfind = false;
    private Set<Integer> costs = new HashSet<>();

    private void IDAStarSearch(String[][] grid) {
        Node root = new Node(null, grid, DIRECTION.Root);
        root.gvalue = 0;
        root.hvalue = Helper.manhatan(root.grid);
        root.fvalue = Helper.fvalueCal(root);

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
        flag = false;

        int bound = root.fvalue;
        while (true) {
            myDfs(root, bound);
            if (goalfind == true) {
                resSize = Helper.trace(resList, lastNode);
                return;
            } else {
                bound = Collections.min(costs);
            }
            costs.clear();
        }
    }

    private void myDfs(Node root, int bound) {
        openList = new Stack<>();
        openList.add(root);
        closeList = new ArrayList<>();
        while (!openList.isEmpty()) {
            Node node = openList.pop();
            closeList.add(node);
            if (node.isGoal(node.grid)) {
                goalfind = true;
                lastNode = node;
                return;
            }
            if (node.fvalue > bound) {
                costs.add(node.fvalue);
            }
            else {
                node.expandNode();

                for (int i = 0; i < node.children.size(); i++) {
                    Node currChild = node.children.get(i);
                    // System.out.println(currChild.direction);
                    // System.out.println();
                    currChild.gvalue=node.gvalue+1;
                    currChild.hvalue=Helper.manhatan(currChild.grid);
                    currChild.fvalue=Helper.fvalueCal(currChild);
                /*    System.out.println(currChild.fvalue);*/
                    if (/*!Helper.listContain(openList, currChild) && */!Helper.listContain(closeList, currChild)) {
                        //      System.out.println("add child");
                        openList.push(currChild);
                    }
                }
            }
        }
        return;
    }
}
