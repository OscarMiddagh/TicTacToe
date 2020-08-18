package tictactoe.frontend;

import tictactoe.backend.ITicTacToe;

public class Console implements ITicTacToeUI {
    protected ITicTacToe game;
    protected Helper helper;
    protected String piece;
    protected int exit;
    protected int newGame;
    public Console(ITicTacToe game) {
        this.game = game;
        helper = new Helper();
        piece = "X";
        setExitNum();
        setNewGameNum();
    }

    protected void setNewGameNum() {
        newGame = 10;
    }

    protected void setExitNum(){
        exit = 11;
    }
    @Override
    public void run(){
        setLabelsGameInit();
        selectTypeInputMarkMove();
        play();
    }
    private void selectTypeInputMarkMove(){
        System.out.println(" 1.- Input Console \n 2.- Input File");
        int number = helper.enterNumber(2, "play number");
        if(number == 2){
            System.out.println("Route File:");
            String routeFile = helper.enterString();
            helper = new HelperFile(routeFile);
        }
    }

    protected void play() {
        setLabelsMessagesOptionsInit();
        while (true) {
            movePlayer();
        }
    }

    protected void setLabelsGameInit(){
        System.out.println();
        System.out.println(helper.colorYellow() + "\n ----------- TIC TAC TOE 1.0 ----------- \n" + helper.resetColor());
    }

    protected void setLabelsMessagesOptionsInit(){
        piece = "X";
        System.out.println(helper.colorBlue() + "\n\nChoose a number from 1 to 9 for play" + helper.resetColor());
        System.out.println(helper.colorGreen() + "> Press "+newGame+" new game \n> Press "+exit+" to the exit game" + helper.resetColor());
        showBoardGame();
        System.out.print("- enter the play number " + piece + " : ");
    }

    protected void movePlayer(){
        int number = helper.enterNumber(exit, "play number");
        if (number == newGame){
            game.create();
        }else{
            if (number == exit){
                System.out.println(helper.messageFinishGame());
                System.exit(0);
            }else {
                inputComplete(number);
            }
        }
    }

    protected void inputComplete(int number) {
        if (!game.markMove(convertRow(number),convertColumn(number))){
            System.out.println(helper.colorRed() + "***play not valid, box already checked" + helper.resetColor());
            System.out.print("- re-enter the play number: ");
            movePlayer();
        }
    }

    protected void showBoardGame() {
        char[][] boardPlay = game.getBoard();
        System.out.println();
        for (char[] chars : boardPlay) {
            for (int j = 0; j < boardPlay.length; j++) {
                char box = chars[j];
                if (box == 'X') {
                    System.out.print("[ " + helper.colorPurple() + box + helper.resetColor() + " ]");
                } else {
                    if (box == 'O') {
                        System.out.print("[ " + helper.colorCyan() + box + helper.resetColor() + " ]");
                    } else {
                        System.out.print("[   ]");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    protected void checkStatusGame(){
        if(game.checkTicTacToe()){
            String winner = String.valueOf(game.winner());
            System.out.println(helper.messageWinnerGame(winner) + "\n");
            starSubMenu();
        }else{
            if (game.draw()){
                System.out.println(helper.messageDrawGame() + "\n");
                starSubMenu();
            }else{
                System.out.print("- enter the play number " + piece + " : ");
            }
        }
    }
    protected void changeLabelTurn(){
        if(piece.equals("X")){
            piece = "O";
        }else{
            piece = "X";
        }
    }
    protected void starSubMenu() {
        System.out.println(helper.colorGreen() + "> Press "+newGame+" new game \n> Press "+exit+" to the exit game" + helper.resetColor() + "\n");
        System.out.print("- enter the option: ");
    }
    protected int convertRow(int number){
        return (number - 1) / 3;
    }

    protected int convertColumn(int number){
        return (number - 1) % 3;
    }
}
