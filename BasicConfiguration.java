import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class BasicConfiguration {

    protected String hostname;

    protected String secret;

    protected String banner;

    protected String ipDomain;

    protected user[] usuarios;

    public String getHostname() {
        return addConfTerminal("Hostname "+ hostname);
    }

    public String getSecret() {
        return addConfTerminal("enable secret "+ secret);
    }

    public String getNoIpDomainLookup(){
        return addConfTerminal("no ip domain-lookup");
    }

    public String getIpDomain(){
        return addConfTerminal("ip domain-name "+ ipDomain);
    }

    public String getServicePasswordEncryption(){
        return addConfTerminal("service password-encryption");
    }

    public String getBanner(){
        return addConfTerminal("banner motd ^C "+ banner+" ^");
    }

    public String getUsuarios(){
        String regreso="";
        for(int i=0;i<usuarios.length;i++){
            regreso+=addConfTerminal(usuarios[i].getUser())+"\n";
        }
        return regreso;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setIpDomain(String ipDomain){
        this.ipDomain = ipDomain;
    }

    public void setBanner(String banner){
        this.banner=banner;
    }


    public void basicConfiguration(){
        //hostname
        configurarHostname();
        //secret
        configurarSecret();
        //banner
        configurarBanner();
        //ip domain
        configurarIpDomain();
        //tamaño usuarios
        configurarUserSize();
        //for every use
        configurarUser();
    }

    public String addConfTerminal(String toAdd){
        return "Configure terminal\n"+toAdd+"\nexit";
    }

    public void configurarUserSize(){
        Scanner entrada= new Scanner(System.in);
        System.out.print("Ingrese la cantidad de usuarios que desea tener (tiene que ser almenos uno):\n");
        usuarios=new user[parseInt(entrada.nextLine())];

    }
    public void configurarUser(){
        Scanner entrada= new Scanner(System.in);
        for(int i =0;i<usuarios.length;i++){
            String name;
            Integer privilege;
            String secret;
            int numeroUsuario=i+1;
            System.out.println("Ingrese el nombre del usuario "+ numeroUsuario);
            name=entrada.nextLine();
            System.out.println("Ingrese el privilegio de "+name+ " (de 1 a 15, 15 es el mayor)");
            privilege=parseInt(entrada.nextLine());
            System.out.println("Ingrese la contraseña de "+ name);
            secret=entrada.nextLine();
            usuarios[i]=new user(name,privilege,secret);
        }
    }

    public void configurarSecret(){
        Scanner entrada= new Scanner(System.in);
        System.out.print("Ingrese Secret(contraseña):\n");
        setSecret(entrada.nextLine());
    }

    public void configurarIpDomain(){
        Scanner entrada= new Scanner(System.in);
        System.out.print("Ingrese el ip domain name:\n");
        setIpDomain(entrada.nextLine());
    }

    public void configurarBanner(){
        Scanner entrada= new Scanner(System.in);
        System.out.print("Ingrese el banner (Por cuestiones legales se recomienda poner que no es bienvenido):\n");
        setBanner(entrada.nextLine());
    }

    public void configurarHostname(){
        Scanner entrada= new Scanner(System.in);
        System.out.print("Ingrese el hostname:\n");
        setHostname(entrada.nextLine());
    }

    @Override
    public String toString() {
        String imprimirUsuarios="";
        for(int i=0;i<usuarios.length;i++){
            imprimirUsuarios+="\t" +usuarios[i].getUser()+"\n";
        }

        return "BasicConfiguration{\n" +
                "\thostname='" + hostname + '\'' + "\n"+
                "\tsecret='" + secret + '\'' + "\n"+
                "\tbanner='" + banner + '\'' + "\n"+
                "\tip domain='" + ipDomain + '\'' + "\n"+
                "\tusuarios:\n"+imprimirUsuarios+
                '}';
    }


    public String getTxtConfiguration(){
        //name
        String regreso="! --"+hostname+" basic configuration\n";
        //hostname Configuration
        regreso+="\n!--Hostname\n"+getHostname()+"\n";
        // secret configuration
        regreso+="\n!--Secret\n"+getSecret()+"\n";
        //no ip domain lookup
        regreso+="\n!--no ip domain-loolip\n"+getNoIpDomainLookup()+"\n";
        //Service password-encription
        regreso+="\n!--Service password-encription\n"+getServicePasswordEncryption()+"\n";
        //Banner
        regreso+="\n!--Banner\n"+getBanner()+"\n";
        //ip domain name
        regreso+="\n!--ip domain name\n"+getIpDomain()+"\n";
        //usuarios
        regreso+="\n!--Usuarios\n"+getUsuarios()+"\n";

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
