package defpack;

/**
 * Created by aleydin on 22.10.16.
 */
public class Player {
    private String name;
    private int number;
    private Board board;
    private char symbol;
    int numberOfWins;

    public Player(String name,char symbol, int number, Board board){
        this.name = name;
        this.symbol = symbol;
        this.number = number;
        this.board = board;
        numberOfWins = 0;
    }

    public boolean push(int number) {
        return board.add(symbol, number-1);
    }

    public boolean canAdd(int number){
        return board.canAdd(symbol, number-1);
    }

    public boolean isWin(){
        return board.isWin(this.symbol);
    }

    public String getName(){
        return this.name;
    }

    public int getNumber(){
        return  number;
    }

    public char getSymbol(){
        return symbol;
    }
}
