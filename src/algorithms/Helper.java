package algorithms;

import java.util.List;
import java.util.Stack;

public class Helper {

    public static String[][] goal ={{"1", "2","3"}, {"4", "5","6"} ,{"7","8","0"} };


    public static boolean listContain(List<Node> list, Node currChild) {
        boolean contain = false;
        //   System.out.println("list size : "+ list.size());
        for (int i = 0; i < list.size(); i++) {
            if (isSame(list.get(i), currChild)) {
                contain = true;
                //          System.out.println("contain: ture");
                return contain;
            }
        }
        //  System.out.println("contain: false");
        return contain;
    }

    public static boolean isSame(Node node, Node child) {
        boolean same = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!node.grid[i][j].equals(child.grid[i][j])) {
                    same = false;
                    return same;
                }
            }
        }
        return same;
    }

    public static int trace(List<DIRECTION>resList , Node lastNode) {
        Node temp = lastNode;
        while (temp.parent != null) {
            resList.add(temp.direction);
           /* print(temp);
            System.out.println(temp.direction);*/
            temp = temp.parent;
        }
      /* print(temp);
        System.out.println(temp.direction);*/

        int resSize = resList.size();
        return resSize;
    }

    public static void print(Node n) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(n.grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int manhatan(String [][] node)
    {
        int dif=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x=j; int y=i ;
                for (int k = 0; k < 3; k++) {
                    for (int m = 0; m < 3; m++) {
                        if(node[i][j].equals(goal[k][m]) && !node[i][j].equals("0")) {
                            int x2 = m;
                            int y2 = k;
                            dif = dif + (Math.abs(x2 - x) + Math.abs(y2 - y));
                        }
                    }
                }
            }
        }
        return dif ;
    }

    public static int fvalueCal(Node n)
    {
        int f=n.gvalue + n.hvalue ;
        return f ;
    }
}
