import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Clients{
  private int id;
  private String name;
  private String desc;

  public Clients(String name){
    this.name = name;
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO clients(name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public void update(){

  }

  public static Clients find(int id){
    String sql = "SELECT * FROM cleints WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Clients.class);
    }
  }

  public static List<Clients> all() {
    String sql = "SELECT * FROM clients;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Clients.class);
    }
  }

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public String getDesc(){
    return desc;
  }
}

