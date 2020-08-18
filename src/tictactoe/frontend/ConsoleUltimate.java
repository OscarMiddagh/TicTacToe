package tictactoe.frontend;

import tictactoe.backend.ITicTacToe;

public class ConsoleUltimate extends Console{
    private final ITicTacToe game;
    private Helper helper;
    private String piece;

    public ConsoleUltimate(ITicTacToe game) {
        super(game);
        this.game = game;
        helper = new Helper();
        piece = "X";
    }
    @Override
    public void run(){
        setLabelsGameInit();
        play();
    }
    private void selectTypeInputMarkMove(){
        System.out.println(" 1.- Input Console \n 2.- Input File");
        int number = helper.enterNumber(2, "play number");
        if(number == 2){
            String routeFile = helper.enterString();
            helper = new HelperFile(routeFile);
        }
    }
    protected void setLabelsGameInit(){
        System.out.println();
        System.out.println(helper.colorYellow() + "\n ----------- TIC TAC TOE 1.0 ----------- \n" + helper.resetColor());
        selectTypeInputMarkMove();
    }
    protected void setLabelsMessagesOptionsInit(){
        piece = "X";
        System.out.println(helper.colorBlue() + "\n\nChoose a number from 1 to 9 for play" + helper.resetColor());
        System.out.println(helper.colorGreen() + "> Press 100 new game \n> Press 111 to the exit game" + helper.resetColor());
        System.out.print(helper.resetColor()+"- enter the play number " + piece + " : ");
    }

    protected void movePlayer(){
        int row = helper.enterNumber(111, "play number");
        if (row == 100){
            game.create();
        }else{
            if (row == 111){
                System.out.println(helper.messageFinishGame());
                System.exit(0);
            }else {
                inputColumn(row);
            }
        }
    }
    private void inputColumn(int row){
        int column = helper.enterNumber(111, "play number");
        if (column == 100){
            game.create();
        }else {
            if (column == 111) {
                System.out.println(helper.messageFinishGame());
                System.exit(0);
            }else {
                inputComplete(row,column);
            }
        }
    }
    protected void starSubMenu() {
        System.out.println(helper.colorGreen() + "> Press 100 new game \n> Press 111 to the exit game" + helper.resetColor() + "\n");
        System.out.print("- enter the option: ");
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
