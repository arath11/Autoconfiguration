import static java.lang.Integer.parseInt;

public class ip {

    protected int ip1;
    protected int ip2;
    protected int ip3;
    protected int ip4;

    public ip(String entrada){
        String [] entrada2=entrada.split("\\.");
        ip1=parseInt(entrada2[0]);
        ip2=parseInt(entrada2[1]);
        ip3=parseInt(entrada2[2]);
        ip4=parseInt(entrada2[3]);

    }

    public ip(int ip1,int ip2, int ip3, int ip4){
        this.ip1=ip1;
        this.ip2=ip2;
        this.ip3=ip3;
        this.ip4=ip4;
    }


    public String getIp(){
        return ip1+"."+ip2+"."+ip3+"."+ip4;
    }


    @Override
    public String toString() {
        return ip1+"."+ip2+"."+ip3+"."+ip4;

    }
}
