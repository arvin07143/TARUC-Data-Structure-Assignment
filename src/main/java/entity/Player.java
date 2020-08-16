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
        reset();
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
    public String getColor() {
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
    public void SetColor(String color) {
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
    
    public void pass(Player currentPlayer,QueueInterface<Player> player){
        currentPlayer = player.peek();
        boolean isPass = true;
        System.out.println("Pls choose ur movement");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch(choice){
            case 1 : 
                 isPass = true;
                 System.out.println("You have pass ur movement.");
                 break;
            case 2 : 
                isPass = false; 
                System.out.println("Pls choose ur next movement");
                break;
        }
    }
    
    public void afterLastMovement(Player currentPlayer,Player nextPlayer,QueueInterface<Player> player){
        player.enqueue(currentPlayer); 
        player.enqueue(nextPlayer); 
        Player p1 = player.peek();
        System.out.print(p1);
        boolean isWon = true;
        if (!isWon){
        player.dequeue();
        player.enqueue(player.dequeue());
        }
        else{
            player.dequeue();
        }
    }
    
    public void leftLastPlayer(QueueInterface<Player> player){
       if(player.getSize() == 1){
           reset();
        }
       
    }
    
    public void addPlayer(Player playerArr[]){
        for (int i = 0; i < player.getSize();i++){
            player.enqueue(playerArr[i]);
        }
    }
    
    public final void reset() {
    player.clear();
    }

}
