package entity;
import adt.ArrayQueue;
import adt.QueueInterface;
import java.util.Scanner;
public class Player {

    private String color;
    private String id;
    private static int hedgehogCount;
    private Hedgehog[] hedgehogs;
    private QueueInterface<Player> player;
    private static int MAX_PLAYER = 4;

    //Constructors
    public Player() {
        player = new ArrayQueue<Player>(MAX_PLAYER) {};
    }

    public Player(String color, String id, int hedgehogCount) {
        this.color = color;
        this.id = id;
        this.hedgehogCount = hedgehogCount;
        hedgehogs = new Hedgehog[hedgehogCount];
        for (int i = 0; i < hedgehogCount; i++) {
            hedgehogs[i] = new Hedgehog();
        }
    }

    //Get methods
    public String getName() {
        return color;
    }

    public String getId() {
        return id;
    }

    public int getHedgehogCount() {
        return hedgehogCount;
    }

    public Hedgehog[] getHedgehogs() {
        return hedgehogs;
    }

    public Hedgehog getHedgehogs(int hedgehogNo){
        return hedgehogs[hedgehogNo-1];
    }
    
    //Set methods
    public void SetName(String name) {
        this.color = color;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHedgehogCount(int hedgehogCount) {
        this.hedgehogCount = hedgehogCount;
    }

    public void setHedgeHog(int hedgehogNo, int row, int column) {
        hedgehogs[hedgehogNo-1].setRow(row);
        hedgehogs[hedgehogNo-1].setColumn(column);
    }
    
    //toString
    @Override
    public String toString(){
        String outputStr = "";
        for (int i = 0; i < hedgehogCount; i++) {
            
            outputStr += "Hedgehog " + (i + 1) + ":" + hedgehogs[i] + "\n\n";
        }
        return outputStr;
    }
    
    
    public void turn(Player currentPlayer, Player nextPlayer){
        boolean isPass = true ;
        if (!(player.isEmpty())){
            currentPlayer = player.peek();
        }
        System.out.println("Pls chose ur movement.");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        if (choice == 1){
            isPass = true;
        }
        else {
            isPass = false;
        }
        System.out.println("Pls choose ur next movement");
        int choice1 = input.nextInt();
        if(choice1 == 1){
            player.dequeue();
            player.enqueue(currentPlayer);
            currentPlayer = player.peek();
            System.out.print(currentPlayer);
        }
    }
}
