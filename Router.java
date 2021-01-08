import java.io.FileWriter;
import java.io.IOException;

public class Router extends BasicConfiguration{



    public void writeTxt(){
        try {
            FileWriter myWriter = new FileWriter(hostname+".txt");
            //crear string que se subira
            String subir= getTxtConfiguration();
            myWriter.write(subir);

            //TODO poner el string diferente

            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }


    public static void main(String[] args) {
        Router prueba=new Router();
        prueba.basicConfiguration();
        System.out.println(prueba);
        prueba.writeTxt();
    }

}
