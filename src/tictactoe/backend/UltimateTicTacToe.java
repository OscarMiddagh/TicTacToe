package tictactoe.backend;

public class UltimateTicTacToe implements ITicTacToe{
    private TicTacToe ultimateBoard;
    private TicTacToe[][] boards;
    private int followingBoard;
    private int dimension;
    private boolean turnX;
    private boolean automaticChangeTurn;
    public UltimateTicTacToe(){
        dimension = 3;
        ultimateBoard = new TicTacToe();
        boards = new TicTacToe[dimension][dimension];
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards.length; j++){
                boards[i][j] = new TicTacToe();
            }
        }
        automaticChangeTurn = true;
        create();
    }

    @Override
    public void create() {
        createUltimateBoard();
        createGeneralBoard();
    }
    private void createUltimateBoard(){
        turnX = true;
        followingBoard = -1;
        ultimateBoard.create();
        ultimateBoard.disableAutomaticShiftChange();
    }
    private void createGeneralBoard(){
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards.length; j++){
                boards[i][j].create();
                boards[i][j].disableAutomaticShiftChange();
            }
        }
    }
    @Override
    public boolean markMove(int row, int column) {
        //search, transform position and markMove
        boolean res = false;
        int actualBoardRow = convertPositionBoards(row);
        int actualBoardColumn = convertPositionBoards(column);
        int currentBoard = actualBoardRow*3+actualBoardColumn;
        TicTacToe actualBoard;
        if(!checkTicTacToe()){
            if(checkLimits(row) && checkLimits(column)){
                actualBoard = boards[actualBoardRow][actualBoardColumn];
                if(!isCurrentBoardLocked(currentBoard)){
                    res = actualBoardMarkMove(row,column,actualBoard);
                }
            }
        }

        return res;
    }
    private boolean actualBoardMarkMove(int row, int column, TicTacToe actualBoard){
        int markRow = convertPositionMark(row);
        int markColumn = convertPositionMark(column);
        boolean res = false;
        if(actualBoard.markMove(markRow,markColumn)){
            if(actualBoard.checkTicTacToe()){
                ultimateBoard.markMove(convertPositionBoards(row),convertPositionBoards(column));
            }
            changeBoard(markRow,markColumn);
            explicitChangeTurn();
            res = true;
        }
        return res;
    }

    private void explicitChangeTurn() {
        if(getAutomaticShiftChange()){
            changeTurn();
        }
    }

    private int convertPositionBoards(int pos){
        return pos/3;
    }
    private int convertPositionMark(int pos){
        return pos%3;
    }
    @Override
    public void changeTurn() {
        ultimateBoard.changeTurn();
        changeTurnGeneralBoard();
    }
    private void changeTurnGeneralBoard(){
        for(int i = 0; i<boards.length;i++){
            for(int j = 0; j<boards.length;j++){
                boards[i][j].changeTurn();
            }
        }
        turnX = !turnX;
    }
    @Override
    public void activateAutomaticShiftChange() {
        automaticChangeTurn = true;
    }

    @Override
    public void disableAutomaticShiftChange() {
        automaticChangeTurn = false;
    }
    private void changeBoard(int row, int column) {
        TicTacToe actualBoard = boards[row][column];
        if(isCurrentBoardFinished(actualBoard)){
            followingBoard = -1;
        }else {
            followingBoard = row*3+column;
        }
    }

    private boolean checkLimits(int value){
        boolean check = false;
        int limitMin = 0;
        int limitMax = 8;
        if(value >= limitMin && value <= limitMax){
            check = true;
        }
        return check;
    }

    private  boolean isCurrentBoardFinished(TicTacToe actualBoard){
        boolean res = false;
        if(actualBoard.draw()||actualBoard.checkTicTacToe()){
            res = true;
        }
        return res;
    }
    private  boolean isCurrentBoardLocked(int actualBoard){
        boolean res = false;
        if(followingBoard>-1){
            if(actualBoard!=followingBoard){
                res = true;
            }
        }
        return res;
    }
    @Override
    public boolean checkTicTacToe() {
        return ultimateBoard.checkTicTacToe();
    }

    @Override
    public char winner() {
        return ultimateBoard.winner();
    }

    @Override
    public boolean draw() {
        return ultimateBoard.draw();
    }

    @Override
    public char[][] getBoard() {
        char[][] res = new char[9][9];

        for(int i = 0; i< ultimateBoard.getBoard().length ; i++){
            for (int j= 0; j< ultimateBoard.getBoard().length ; j++){
                getBoardSmall(i,j,res);
            }
        }
        return res;
    }
    private void getBoardSmall(int i, int j, char[][] generalBoard){
        char[][] actual =  boards[i][j].getBoard();
        for (int m = 0; m< actual.length;m++){
            for (int n = 0; n< actual.length;n++){
                generalBoard[(i*3)+m][(j*3)+n] = actual[m][n];
            }
        }
    }

    @Override
    public boolean getAutomaticShiftChange() {
        return automaticChangeTurn;
    }

}
