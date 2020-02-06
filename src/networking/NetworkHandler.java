package networking;

import game.Solver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkHandler {
    private DataInputStream dis;
    private DataOutputStream dos;

    public NetworkHandler() {
        try {
            Socket socket = new Socket("localhost", 8963);
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException e) {
            this.printCouldNotConnectMessage();
        }
    }

    private String getMoveFromClient(String serverMessage, Solver solver) throws IOException {
        solver.fillGrid(serverMessage);
        this.dos.writeUTF(solver.solve());
        serverMessage = this.dis.readUTF();
        return serverMessage;
    }

    public String tryGetMoveFromClient(String serverMessage, Solver solver){
        try {
            return this.getMoveFromClient(serverMessage, solver);
        }
        catch (IOException e){
            this.printDisconnectionMessage();
        }
        return null;
    }


    public String initializeGame(int size)   {
        try {
            this.getDos().writeUTF(String.valueOf(size));
            return this.getDis().readUTF();
        } catch (IOException e) {
            this.printDisconnectionMessage();
        }
        return null;
    }

    private DataInputStream getDis() {
        return dis;
    }

    private DataOutputStream getDos() {
        return dos;
    }

    private void printDisconnectionMessage(){
        System.err.println("Server connection lost!");
        System.exit(1);
    }
    private void printCouldNotConnectMessage(){
        System.err.println("Couldn't connect to server!");
        System.exit(1);
    }
}
