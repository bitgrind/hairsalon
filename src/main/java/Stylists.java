import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylists{
  private int id;
  private String name;
  private String description;

  public Stylists(String name, String description){
    this.name = name;
    this.description = description;
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO stylists(name, description) VALUES (:name, :description);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("description", this.description)
        .executeUpdate()
        .getKey();
    }
  }

  public void update(String description){
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET description = :description WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
    }
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

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public String getInfo(){
    return description;
  }
}