package entity;
<<<<<<< Updated upstream
import adt.ArrayQueue;
import adt.QueueInterface;
import java.util.Scanner;
=======

import adt.ArrayQueue;
import adt.QueueInterface;
import java.util.Scanner;

>>>>>>> Stashed changes
public class Player {

    private String color;
    private String id;
    private static int hedgehogCount;
    private Hedgehog[] hedgehogs;
<<<<<<< Updated upstream
    private QueueInterface<Player> player;
    private static int MAX_PLAYER = 4;
=======
    private ArrayQueue<Player> player;
    private static int MAX_PLAYER = 10;
>>>>>>> Stashed changes

    //Constructors
    public Player() {
        player = new ArrayQueue<Player>(MAX_PLAYER) {};
<<<<<<< Updated upstream
        reset();
=======
>>>>>>> Stashed changes
    }

    public Player(String color, String id, int hedgehogCount) {
        this.color = color;
        this.id = id;
        this.hedgehogCount = hedgehogCount;
        for (int i = 0; i < hedgehogCount; i++) {
            hedgehogs[i] = new Hedgehog();
        }
    }

    //Get methods
    public String getPlayerColor() {
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

    //Set methods
    public void SetPlayerColor(String color) {
        this.color = color;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHedgehogCount(int hedgehogCount) {
        this.hedgehogCount = hedgehogCount;
    }

    public void setHedgeHogs(int hedgehogNo, int row, int column) {
        hedgehogs[hedgehogNo].setRow(row);
        hedgehogs[hedgehogNo].setColumn(column);
    }
    
<<<<<<< Updated upstream
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
        leftLastPlayer(player);
        }
        else{
            player.dequeue();
            leftLastPlayer(player);
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

=======
   public void addPlayer(Player Player){
           player.enqueue(Player);
    }
   
   public void turn(){
        Scanner input = new Scanner(System.in);
        Player currentPlayer = null;
      if(!(player.isEmpty())){
       currentPlayer = player.peek();
      }
      
      System.out.print("Pls do ur action(pass/continue)");
      int choice = input.nextInt();
      boolean isPass = true;
      if(choice == 1){
      isPass = true;
      }
      else {
          isPass = false ;
      }
      if (isPass){
          player.dequeue();
      }
      else {
          System.out.println("Pls continue the game");
          System.out.println("Pls choose ur next movement.");
          int choice1 = input.nextInt();
          if (choice1 == 1){
              System.out.println("ur turn has end"); 
              player.dequeue();
              player.enqueue(currentPlayer);
          }
      }
      
      
      
   }    
   
   
>>>>>>> Stashed changes
}
    
