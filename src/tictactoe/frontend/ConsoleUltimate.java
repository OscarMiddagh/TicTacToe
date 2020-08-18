package tictactoe.frontend;

import tictactoe.backend.ITicTacToe;

public class ConsoleUltimate extends Console{
    public ConsoleUltimate(ITicTacToe game) {
        super(game);
    }
    protected void setNewGameNum() {
        newGame = 100;
    }
    protected void setExitNum(){
        exit = 111;
    }
    protected void movePlayer(){
        System.out.println("Row:");
        int row = helper.enterNumber(exit, "play number");
        if (row == newGame){
            game.create();
        }else{
            exitOptionChoose(row);
        }
    }
    protected void exitOptionChoose(int row){
        if (row == exit){
            System.out.println(helper.messageFinishGame());
            System.exit(0);
        }else {
            inputColumn(row);
        }
    }
    private void inputColumn(int row){
        System.out.println("Column:");
        int column = helper.enterNumber(exit, "play number");
        if (column == newGame){
            game.create();
        }else {
            if (column == exit) {
                System.out.println(helper.messageFinishGame());
                System.exit(0);
            }else {
                inputComplete(row-1,column-1);
            }
        }
    }
    private void inputComplete(int row, int column){
        if (!game.markMove(row,column)){
            System.out.println(helper.colorRed() + "***play not valid, box already checked" + helper.resetColor());
            System.out.print("- re-enter the play number: ");
            movePlayer();
        }else {
            showBoardGame();
            changeLabelTurn();
            checkStatusGame();
        }
    }

}
