public class IPWithMask extends ip{
    private int mask1;

    private int mask2;

    private int mask3;

    private int mask4;

    private boolean isDHCP;

    public IPWithMask(String entrada) {
        super(0,0,0,0);
        //super(entrada);
        isDHCP=true;
    }

    public IPWithMask(int ip1,int ip2,int ip3, int ip4,int mask1,int mask2,int mask3,int mask4){
        super(ip1,ip2,ip3,ip4);
        this.mask1=mask1;
        this.mask2=mask2;
        this.mask3=mask3;
        this.mask4=mask4;
        isDHCP=false;
    }

    public IPWithMask(int ip1,int ip2,int ip3, int ip4,String mask){
        super(ip1,ip2,ip3,ip4);
        //todo make a method for transcripton of /mask to 0.0.0.0
        isDHCP=false;
    }



    @Override
    public String toString() {
        if(!isDHCP){
            return ip1+"."+ip2+"."+ip3+"."+ip4+" "+mask1+"."+mask2+"."+mask3+"."+mask4;
        }else {
            return "dhcp";
        }
    }

    public static void main(String[] args) {
        IPWithMask prueba=new IPWithMask(10,0,0,0,255,255,248,0);
        System.out.println(prueba);
        IPWithMask prueba2=new IPWithMask("dhcp auto");
        System.out.println(prueba2);

    }
}
