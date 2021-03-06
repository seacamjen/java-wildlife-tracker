import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Civilian extends Viewers implements Person {
  public static final boolean CIVILIAN_RANGER_STATUS = false;

  public Civilian(boolean ranger, String name, String phone){
    ranger = CIVILIAN_RANGER_STATUS;
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
        .throwOnMappingFailure(false)
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
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Civilian.class);
      return civilian;
    }
  }

  public static Civilian findByPhone(String phone) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM viewers WHERE phone = :phone;";
      Civilian civilian = con.createQuery(sql)
        .addParameter("phone", phone)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Civilian.class);
      if (civilian == null) {
        throw new NoSuchElementException("No Civilian with this phone number");
      }
      return civilian;
    }
  }

  public void updateCivilian(String name, String phone) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE viewers SET (name, phone) = (:name, :phone) WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .addParameter("phone", phone)
        .throwOnMappingFailure(false)
        .executeUpdate();
    }
  }

  public static List<Animal> getAnimals(int id) {
   try (Connection con = DB.sql2o.open()) {
     String sql = "SELECT wildlife_animals.name, wildlife_animals.description, wildlife_animals.health, wildlife_animals.gender, wildlife_animals.endangered FROM wildlife_animals INNER JOIN sightings ON wildlife_animals.id = sightings.animal_id INNER JOIN viewers ON sightings.viewer_id = viewers.id WHERE viewer_id = :id;";
     return con.createQuery(sql)
       .addParameter("id", id)
       .executeAndFetch(Animal.class);
    }
  }
}
