public class Node {
    String name;
    Integer numero;
    Boolean esFinal;

    public Node(String valor, int numero){
        this.name=valor;
        this.numero=numero;
        esFinal=false;
    }


    public Node(String valor, int numero,boolean esFinal){
        this.name=valor;
        this.numero=numero;
        this.esFinal=esFinal;
    }

    public String toString(){
        String regreso= (this.name+"-"+this.numero);
        if(esFinal){
            regreso+=" Es final";
        }
        return regreso;
    }



}
