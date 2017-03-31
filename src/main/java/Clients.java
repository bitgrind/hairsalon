import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Clients{
  private int id;
  private String name;
  private String description;
  private int stylist_id;

  public Clients(String name, String description, int stylist_id){
    this.name = name;
    this.description = description;
    this.stylist_id = stylist_id;
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO clients (name, description, stylist_id) VALUES (:name, :description, :stylist_id);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("description", this.description)
        .addParameter("stylist_id", this.stylist_id)
        .executeUpdate()
        .getKey();
    }
  }
  public static Clients find(int id){
    String sql = "SELECT * FROM clients WHERE id = :id";
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
  public static List<Clients> stylistAll(int stylist_id) {
    String sql = "SELECT * FROM clients WHERE stylist_id = :stylist_id;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("stylist_id", stylist_id)
        .executeAndFetch(Clients.class);
    }
  }

  public void updateInfo(String description) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET description = :description WHERE id = :id";
      con.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
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
  public int getStylistId(){
    return stylist_id;
  }
}

