public class user {
    private String name;
    private Integer privilege;
    private String secret;

    public user(String name,int privilege,String secret){
        this.name=name;
        this.privilege=privilege;
        this.secret=secret;
    }

    public String getUser(){
        return "username "+name+" privilege "+ privilege+" secret "+secret;
    }
}
