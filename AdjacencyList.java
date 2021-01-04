import java.util.*;

public class AdjacencyList {
    private ArrayList<ArrayList<Node>> graph;


    //
    public AdjacencyList(int size){
        graph = new ArrayList<ArrayList<Node>>(size);

        for (int i = 0; i < size; i++){
            graph.add(new ArrayList<Node>());
        }
    }

    public void add(int origin, int destination, String tipe){
        graph.get(origin).add(new Node(tipe,destination));
    }

    public void add(int origin, int destination,String tipe,boolean esFinal){
        graph.get(origin).add(new Node(tipe,destination,esFinal));
    }

    public void imprimir(){
        for(int i=0;i<graph.size();i++){
            System.out.println("Vertice:" + i);
            for (int j = 0; j < graph.get(i).size(); j++) {
                System.out.print(graph.get(i).get(j).toString()+",");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        AdjacencyList prueba=new AdjacencyList(10);

        prueba.add(0,1,"Switch");
        prueba.add(1,2,"Router");
        prueba.add(2,3,"Switch",true);

        prueba.imprimir();

        /*prueba.añadir(0,1,"a");
        prueba.añadir(0,1,"c");
        prueba.añadir(0,1, "x");
        prueba.añadir(1,2, "b");
        prueba.añadir(2,4, "a");
        prueba.añadir(1,5,"b");
        prueba.añadir(5,6,"a");
        prueba.añadir(1,7, "a");
        prueba.añadir(7,8,"b");
        prueba.añadir(8,9,"a");*/


    }

}
