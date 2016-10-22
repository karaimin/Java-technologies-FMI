package defpack;

import java.util.Scanner;

/**
 * Created by aleydin on 22.10.16.
 */
public class Game {
    private Player player1;
    private Player player2;
    private Board gameBoard;

    public Game(String firstPlayerName, String secondPlayerName){
        this.gameBoard = new Board();
        this.player1 = new Player(firstPlayerName, 'x', 1, this.gameBoard);
        this.player2 = new Player(secondPlayerName, 'o', 2, this.gameBoard);
    }

    private void stepPlayer(Player player){
        boolean canContinue = false;
        int pos = 0;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Player " + player.getNumber());
            pos = scanner.nextInt();
            if(player.canAdd(pos)){
                canContinue = true;
            } else {
                System.out.println("sorry you can't add here. Please try again...");
            }
        } while (!canContinue);
        player.push(pos);
    }

    public void start(){

        while (true){
            this.gameBoard.print();
            stepPlayer(this.player1);
            if(this.player1.isWin()){
                printWinner(this.player1);
                break;
            }
            this.gameBoard.print();
            if(this.gameBoard.isFilled()){
                System.out.println("Game finished with no winner...");
                break;
            }
            stepPlayer(this.player2);
            if (this.player2.isWin()){
                break;
            }
        }
    }

    private void printWinner(Player player){
        System.out.println("And the winner is: " + player.getName());
    }
}
