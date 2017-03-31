import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylists{
  private int id;
  private String name;
  private String desc;

  public Stylists(String name){
    this.name = name;
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO stylists(name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public void update(){

  }

  public static Stylists find(int id){
    String sql = "SELECT * FROM stylists WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylists.class);
    }
  }

  public static List<Stylists> all(){
    String sql = "SELECT * FROM stylists;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Stylists.class);
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