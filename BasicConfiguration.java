import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BasicConfiguration {

    protected String hostname;


    public String getHostname() {
        return addConfTerminal("Hostname "+ hostname);
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }


    public void basicConfiguration(){
        //hostname
        configurarHostname();

    }

    public String addConfTerminal(String toAdd){

        return "Configure terminal\n"+toAdd+"\nexit";
    }

    public void configurarHostname(){
        Scanner entrada= new Scanner(System.in);
        System.out.print("Ingese el hostname:\n");
        setHostname(entrada.nextLine());
    }

    @Override
    public String toString() {
        return "BasicConfiguration{\n" +
                "\thostname='" + hostname + '\'' + "\n"+
                '}';
    }


    public String getTxtConfiguration(){
        String regreso="! --"+hostname+" basic configuration\n";
        regreso+="\n!--Hostname\n"+getHostname();
        return regreso;
    }


    public void writeTxt(){
        try {
            FileWriter myWriter = new FileWriter(hostname+".txt");
            //crear string que se subira
            String subir= getTxtConfiguration();

            //TODO poner el string diferente

            myWriter.write(subir);

            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void main(String[] args) {
        BasicConfiguration prueba=new BasicConfiguration();
        prueba.basicConfiguration();
        System.out.println(prueba);
        prueba.writeTxt();


    }


}
