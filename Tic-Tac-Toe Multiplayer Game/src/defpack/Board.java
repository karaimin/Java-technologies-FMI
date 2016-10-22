package defpack;

/**
 * Created by aleydin on 22.10.16.
 */
public class Board {
    private char[][] board;
    int rows;
    int cols;

    public Board(){
        this.rows = 3;
        this.cols = this.rows;
        this.board = new char[this.rows][this.cols];
        reset();
    }

    private void reset(){
        int counter = 49;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.board[i][j] = (char)counter;
                counter++;
            }
        }
    }

    public void print(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private boolean isWinHorizontal(char symbol){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(symbol != this.board[i][j]) {
                    break;
                }
                if(j == cols - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isWinVertical(char symbol){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(symbol != this.board[j][i]) {
                    break;
                }
                if(j == rows - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isWinDiagonal(char symbol){
        for (int i = 0; i < rows; i++) {
            if(symbol != this.board[i][i]){
                break;
            }
            if(i == rows - 1){
                return true;
            }

        }
        int size = 2;

        for (int i = rows - 1; i >=0 ; i--) {
            if(symbol != this.board[i][size - i]){
                break;
            }
            if(i == 0){
                return true;
            }
        }
        return false;
    }

    public boolean canAdd(char symbol, int boardNumber){
        int yPos = boardNumber / rows;
        int xPos = boardNumber % rows;

        String currentPos = String.valueOf(this.board[yPos][xPos]);
        if (currentPos.equalsIgnoreCase("x") || currentPos.equalsIgnoreCase("o")){
            return false;
        }
        return true;
    }

    public boolean isFilled(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String currentPos = String.valueOf(this.board[i][j]);
                if(!currentPos.equalsIgnoreCase("o") && !currentPos.equalsIgnoreCase("x")){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWin(char symbol){
        return isWinDiagonal(symbol) || isWinHorizontal(symbol) || isWinVertical(symbol);
    }

    public boolean add(char symbol, int number){
        return this.add(symbol, (number % rows) , (number / rows));
    }
    private boolean add(char symbol, int xPos, int yPos){
        this.board[yPos][xPos] = symbol;
        return true;
    }
}
