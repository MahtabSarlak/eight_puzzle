package game;

import algorithms.Algorithm;

public class Solver {

    private Algorithm algorithm;
    private String[][] grid;
    private int size;

    public Solver(int size, Algorithm algorithm){
        this.size = size;
        this.grid = new String[size][size];
        this.algorithm = algorithm;
    }

    public void fillGrid(String gridString) {
        String[] lines = gridString.split("\n");
        for (int i = 0; i < size; i++) {
            grid[i] = lines[i].split("\t");
        }
    }

    public String solve(){
        return algorithm.makeMove(grid);
    }

}
