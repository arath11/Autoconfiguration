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

    protected String SshOrTelnet;

    protected Integer lineVtyCantidad = 15;

    protected Integer tiempoInactivoLineVty = 5;

    protected Integer tiempoConsole = 5;

    boolean isNTPserverEnable;

    protected ip logginIp;

    protected ip ntpServer;

    protected Integer loggingBuffered=4096;

    public BasicConfiguration(){
        isNTPserverEnable=false;
    }

    public String getNTPServer(){
        return addConfTerminal("logging on\nlogging buffered "+loggingBuffered+"\nlogging "+logginIp+"\nntp server "+ntpServer+
                "\nservice timestamps log datetime msec\n" +
                "service timestamps debug datetime msec");

    }

    public String getTimeZoneMexico(){
        return addConfTerminal("clock timezone CDT -1 0\n" +
                "clock summer-time CDT recurring");
    }

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

    public String getLineVTY(){
        if (SshOrTelnet.equals("ssh") || SshOrTelnet.equals("all")){
            return addConfTerminal("line vty 0 "+ lineVtyCantidad +" \nlogin local\nExec-timeout "+tiempoInactivoLineVty+"\nTransport input "+SshOrTelnet+" \nexit")+"\n"+addConfTerminal("Crypto key generate rsa 1024\nip ssh version 2");
        }
        return addConfTerminal("line vty 0 "+ lineVtyCantidad +" \nlogin local\nExec-timeout "+tiempoInactivoLineVty+"\nTransport input "+SshOrTelnet+" \nexit");
    }

    public String getConsoleExecTimeout(){
        return addConfTerminal("line console 0\nlogin local\nexec-timeout 5 0\nexit");
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

    public void setLineVtyNumber(int lineVtyNumber){
        this.lineVtyCantidad=lineVtyNumber;
    }

    public void setLineVtyTiempo(int tiempo){
        this.tiempoInactivoLineVty=tiempo;
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
        //for every user
        configurarUser();
        //Configure ssh and or telnet
        configurarSSHTELNET();
        //ask if there is an ntp server
        checkNTPServet();


    }

    public void checkNTPServet(){
        Scanner entrada=new Scanner(System.in);
        System.out.println("Desea usar un servidor NTP [y/n]");
        String respuesta=entrada.nextLine();
        if(respuesta.equals("Y")||respuesta.equals("y")||respuesta.equals("Yes")||respuesta.equals("yes")){
            //ask for information about the ntp server
            isNTPserverEnable=true;
            //ask for logging
            System.out.println("Igrese la ip donde guardara el log (logging ip):");
            logginIp=new ip(entrada.nextLine());
            System.out.println("Ingrese la ip del servidor ntp ");
            ntpServer=new ip(entrada.nextLine());

        }else {

        }
    }


    public String addConfTerminal(String toAdd){
        return "Configure terminal\n"+toAdd+"\nexit";
    }

    public void configurarSSHTELNET(){
        Scanner entrada=new Scanner(System.in);
        System.out.println("Cuantas line vty se usaran (4 0 15, se recomienda 15)");
        int lineVtyNumber=parseInt(entrada.nextLine());
        setLineVtyNumber(lineVtyNumber);
        System.out.println("Ingrese el tiempo que de inactividad para terminar la conexión");
        int tiempoInactivo=parseInt(entrada.nextLine());
        setLineVtyTiempo(tiempoInactivo);
        System.out.println("ingresar:\n 1 para ssh\n2 para telnet\n3 para all");
        int datoIngresado=parseInt(entrada.nextLine());
        if(datoIngresado==1){
            SshOrTelnet="ssh";
        }else if(datoIngresado==2){
            SshOrTelnet="telnet";
        }else {
            SshOrTelnet="all";
        }

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
                "\tline vty 0 "+lineVtyCantidad+'\''+"\n"+
                "\tExec-timeout "+tiempoInactivoLineVty+'\''+"\n"+
                "\ttransport input "+SshOrTelnet+'\''+"\n"+
                "\tConsole timeout "+tiempoConsole+'\''+"\n"+
                "\tLogging  "+logginIp+'\''+"\n"+
                "\tLogging Buffer "+ loggingBuffered+'\''+"\n"+
                "\tNtp server "+ntpServer+'\''+"\n"+

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
        //lineVty
        regreso+="\n!--LineVTY\n"+getLineVTY()+"\n";
        //Console time out
        regreso+="\n!--console timeout\n"+getConsoleExecTimeout()+"\n";
        if(isNTPserverEnable){
            regreso+="\n!--Logging and ntp server\n"+getNTPServer()+"\n";
        }
        regreso+="\n!--TimeZone\n"+getTimeZoneMexico()+"\n";
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
