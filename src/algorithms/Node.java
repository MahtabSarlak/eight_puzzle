package algorithms;

import com.sun.javafx.scene.traversal.Direction;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public Node parent;
    public String[][] grid;
    public DIRECTION direction;
    public List<Node> children=new ArrayList<>();
    public  int gvalue;
    public int fvalue;
    public int hvalue;

    public int emptyY;
    public int emptyX;

    public void setEmptyY(int emptyY) {
        this.emptyY = emptyY;
    }

    public void setEmptyX(int emptyX) {
        this.emptyX = emptyX;
    }

    public Node(String[][] grid) {
        this.grid = grid;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String[][] getGrid() {
        return grid;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

    public DIRECTION getDirection() {
        return direction;
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    public Node(Node parent, String[][] grid, DIRECTION direction) {
        this.parent = parent;
        this.grid = grid;
        this.direction = direction;
    }

    public void expandNode( )
    {
    makeMove(DIRECTION.Left);
    makeMove(DIRECTION.Right);
    makeMove(DIRECTION.Up);
    makeMove(DIRECTION.Down);

    }

    public void makeMove(DIRECTION dir )  {
        switch (dir) {
            case Right:
                this.moveEmptyCell(this.emptyY, this.emptyX + 1 , dir);
                break;
            case Left:
                this.moveEmptyCell(this.emptyY, this.emptyX - 1,dir);
                break;
            case Down:
                this.moveEmptyCell(this.emptyY + 1, this.emptyX,dir);
                break;
            case Up:
                this.moveEmptyCell(this.emptyY - 1, this.emptyX,dir);
                break;
        }
    }

    private void moveEmptyCell(int y, int x , DIRECTION dir){
        if (!isCellOutOfBounds(y, x))
            swapCell(y, x , dir);
    }

    private void swapCell(int y, int x , DIRECTION dir) {
        String[][] newGrid = new String[3][3];
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j < 3; j++) {
                newGrid[i][j]=this.grid[i][j];
            }
        }

        String temp = newGrid[y][x];
        newGrid[y][x] = newGrid[this.emptyY][this.emptyX];
        newGrid[this.emptyY][this.emptyX] = temp;

       /* System.out.println(dir);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(newGrid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();*/
        Node chid=new Node(this , newGrid , dir);
        chid.emptyX=x;
        chid.emptyY=y;
        this.children.add(chid);
    }

    private boolean isCellOutOfBounds(int y, int x) {
        return y >= 3 || x >= 3 || y < 0 || x < 0;
    }
    public boolean isGoal(String[][] board)
    {
       /* for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();*/
        boolean flag=true ;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i==2 & j==2)
                {
                    if(!board[i][j].equals("0"))
                    {
                        flag=false;
                        return flag ;
                    }
                }
                else
                {
                    int t=(j+1)+i*3;
                    String s =Integer.toString(t);
                    if(!board[i][j].equals(s))
                    {
                        flag=false;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }
}
