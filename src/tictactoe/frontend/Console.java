package tictactoe.frontend;

import tictactoe.backend.ITicTacToe;

public class Console implements ITicTacToeUI {
    protected final ITicTacToe game;
    protected final Helper helper;
    protected String piece;

    public Console(ITicTacToe game) {
        this.game = game;
        helper = new Helper();
        piece = "X";
    }

    @Override
    public void run(){
        setLabelsGameInit();
        play();
    }

    private void play() {
        setLabelsMessagesOptionsInit();
        while (true) {
            movePlayer();
        }
    }

    private void setLabelsGameInit(){
        System.out.println();
        System.out.println(helper.colorYellow() + "\n ----------- TIC TAC TOE 1.0 ----------- \n" + helper.resetColor());
    }

    protected void setLabelsMessagesOptionsInit(){
        piece = "X";
        System.out.println(helper.colorBlue() + "\n\nChoose a number from 1 to 9 for play" + helper.resetColor());
        System.out.println(helper.colorGreen() + "> Press 10 new game \n> Press 11 to the exit game" + helper.resetColor());
        showBoardGame();
        System.out.print("- enter the play number " + piece + " : ");
    }

    protected void movePlayer(){
        int number = helper.enterNumber(11, "play number");
        if (number == 10){
            game.create();
        }else{
            if (number == 11){
                System.out.println(helper.messageFinishGame());
                System.exit(0);
            }else {
                if (!game.markMove(convertRow(number),convertColumn(number))){
                    System.out.println(helper.colorRed() + "***play not valid, box already checked" + helper.resetColor());
                    System.out.print("- re-enter the play number: ");
                    movePlayer();
                }
            }
        }
    }

    protected void showBoardGame() {
        char[][] boardPlay = game.getBoard();
        System.out.println();
        for (int i = 0; i < boardPlay.length; i++) {
            char[] chars = boardPlay[i];
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


    private int convertRow(int number){
        return (number - 1) / 3;
    }

    private int convertColumn(int number){
        return (number - 1) % 3;
    }
}
