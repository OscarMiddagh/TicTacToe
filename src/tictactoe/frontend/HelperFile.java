package tictactoe.frontend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HelperFile extends Helper{
    private File f;
    public HelperFile(String ruta){
        f = new File(ruta);
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //Esto viola principio de sustitucion de liskov??
    protected void input(){
        //Esto es asi por que solo necesitamos instanciar una vez el scanner
        //El Helper original intancia al scanner en cada llamada al metodo
    }
}
