import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Civilian extends Viewers implements Person {

  public Civilian(boolean ranger, String name, String phone){
    this.ranger = ranger;
    this.name = name;
    this.phone = phone;
    this.id = id;
  }

  public boolean getRanger() {
    return ranger;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherCivilian) {
    if(!(otherCivilian instanceof Civilian)) {
      return false;
    } else {
      Civilian newCivilian = (Civilian) otherCivilian;
      return this.getName().equals(newCivilian.getName()) &&
             this.getRanger() == newCivilian.getRanger() && this.getPhone().equals(newCivilian.getPhone());
    }
  }

  public static List<Civilian> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM viewers WHERE ranger = false;";
      return con.createQuery(sql)
        .executeAndFetch(Civilian.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO viewers (ranger, name, phone) VALUES (:ranger, :name, :phone);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("ranger", ranger)
        .addParameter("name", name)
        .addParameter("phone", phone)
        .executeUpdate()
        .getKey();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM viewers WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static Civilian find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM viewers WHERE id=:id;";
      Civilian civilian = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Civilian.class);
      return civilian;
    }
  }
}
