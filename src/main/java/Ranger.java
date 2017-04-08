import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Ranger extends Viewers implements Person {
  public int ranger_number;

  public Ranger(boolean ranger, String name, String phone, int ranger_number){
    this.ranger = ranger;
    this.name = name;
    this.phone = phone;
    this.ranger_number = ranger_number;
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

  public int getRangerNumber() {
    return ranger_number;
  }

  @Override
  public boolean equals(Object otherRanger) {
    if(!(otherRanger instanceof Ranger)) {
      return false;
    } else {
      Ranger newRanger = (Ranger) otherRanger;
      return this.getName().equals(newRanger.getName()) &&
             this.getRanger() == newRanger.getRanger() && this.getPhone().equals(newRanger.getPhone()) &&
             this.getRangerNumber() == newRanger.getRangerNumber();
    }
  }

  public static List<Ranger> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM viewers WHERE ranger = true;";
      return con.createQuery(sql)
        .executeAndFetch(Ranger.class);
    }
  }

  public void save() {
    if ((ranger == true) && (ranger_number < 1)) {
      throw new IllegalArgumentException("If you are a ranger you must enter your ranger number.");
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO viewers (ranger, name, phone, ranger_number) VALUES (:ranger, :name, :phone, :ranger_number);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("ranger", ranger)
        .addParameter("name", name)
        .addParameter("phone", phone)
        .addParameter("ranger_number", ranger_number)
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

  public static Ranger find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM viewers WHERE id=:id;";
      Ranger ranger = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Ranger.class);
      return ranger;
    }
  }

  public void updateRanger(String name, String phone) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE viewers SET (name, phone) = (:name, :phone) WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .addParameter("phone", phone)
        .executeUpdate();
    }
  }
//add a test
  public static Ranger findByRangerNumber(int rangerNumber) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM viewers WHERE ranger_number = :rangerNumber;";
      Ranger ranger = con.createQuery(sql)
        .addParameter("rangerNumber", rangerNumber)
        .executeAndFetchFirst(Ranger.class);
      if (ranger == null) {
        throw new NoSuchElementException("No Ranger with this Ranger Number");
      }
      return ranger;
    }
  }
//add a test
  public static List<Animal> getAnimals(int id) {
   try (Connection con = DB.sql2o.open()) {
     String sql = "SELECT wildlife_animals.name, wildlife_animals.description, wildlife_animals.health, wildlife_animals.gender, wildlife_animals.endangered FROM wildlife_animals INNER JOIN sightings ON wildlife_animals.id = sightings.animal_id INNER JOIN viewers ON sightings.viewer_id = viewers.id WHERE viewer_id = :id;";
     return con.createQuery(sql)
       .addParameter("id", id)
       .executeAndFetch(Animal.class);
    }
  }

}
