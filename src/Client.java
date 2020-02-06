import algorithms.*;
import algorithms.search.AStar;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.IDAStar;
import game.Solver;
import networking.NetworkHandler;


public class Client {

    private static final int MAP_SIZE = 3;

    public static void main(String[] args) {
        NetworkHandler networkHandler = new NetworkHandler();
        Solver solver = getSolver();
        String serverMessage = networkHandler.initializeGame(MAP_SIZE);
        startGame(networkHandler, serverMessage, solver);
    }

//  select your algorithm in this method (select one)
    private static Solver getSolver() {
     //   return new Solver(MAP_SIZE, new UserInputAlgorithm());
   //      return new Solver(MAP_SIZE, new DFS());
   //  return new Solver(MAP_SIZE, new BFS());
      return new Solver(MAP_SIZE, new AStar());
     // return new Solver(MAP_SIZE, new IDAStar());
    }

    private static void startGame(NetworkHandler networkHandler, String serverMessage, Solver solver) {
        while (!serverMessage.equals("Finished!")) {
            System.out.print(serverMessage);
            serverMessage = networkHandler.tryGetMoveFromClient(serverMessage, solver);
        }
        System.out.println(serverMessage);
    }

}
