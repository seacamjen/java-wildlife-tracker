import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class EndangeredAnimal extends Wildlife {

  public EndangeredAnimal(String name, String health, String age, String color, String description, String gender, boolean endangered) {
    this.name = name;
    this.id = id;
    this.health = health;
    this.age = age;
    this.color = color;
    this.description = description;
    this.gender = gender;
    this.endangered = endangered;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  public String getColor() {
    return color;
  }

  public String getDescription() {
    return description;
  }

  public String getGender() {
    return gender;
  }

  public boolean getEndangered() {
    return endangered;
  }

  public static EndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM wildlife_animals WHERE id=:id;";
      EndangeredAnimal endangeredAnimal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(EndangeredAnimal.class);
      return endangeredAnimal;
    }
  }

  @Override
  public boolean equals(Object otherEndangeredAnimal) {
    if(!(otherEndangeredAnimal instanceof EndangeredAnimal)) {
      return false;
    } else {
      EndangeredAnimal newEndangeredAnimal = (EndangeredAnimal) otherEndangeredAnimal;
      return this.getName().equals(newEndangeredAnimal.getName()) &&  this.getHealth().equals(newEndangeredAnimal.getHealth()) && this.getAge().equals(newEndangeredAnimal.getAge()) && this.getColor().equals(newEndangeredAnimal.getColor()) && this.getDescription().equals(newEndangeredAnimal.getDescription()) && this.getGender().equals(newEndangeredAnimal.getGender()) && this.getEndangered() == newEndangeredAnimal.getEndangered();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO wildlife_animals (name, health, age, color, description, gender, endangered) VALUES (:name, :health, :age, :color, :description, :gender, :endangered);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("health", this.health)
        .addParameter("age", this.age)
        .addParameter("color", this.color)
        .addParameter("description", this.description)
        .addParameter("gender", this.gender)
        .addParameter("endangered", this.endangered)
        .executeUpdate()
        .getKey();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM wildlife_animals WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateName(String health) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE wildlife_animals SET name=:name WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .executeUpdate();
    }
  }

  public void updateHealth(String health) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE wildlife_animals SET health=:health WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("health", health)
        .executeUpdate();
    }
  }

  public void updateAge(String age) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE wildlife_animals SET age=:age WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("age", age)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateDescription(String description) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE wildlife_animals SET description=:description WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static List<EndangeredAnimal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM wildlife_animals WHERE endangered = true;";
      return con.createQuery(sql)
        .executeAndFetch(EndangeredAnimal.class);
    }
  }

  public static List<Ranger> getRangers(int id) {
   try (Connection con = DB.sql2o.open()) {
     String sql = "SELECT viewers.name, viewers.phone, viewers.ranger FROM viewers INNER JOIN sightings ON viewers.id = sightings.viewer_id INNER JOIN wildlife_animals ON sightings.animal_id = wildlife_animals.id WHERE wildlife_animals.id = :id;";
     return con.createQuery(sql)
       .addParameter("id", id)
       .executeAndFetch(Ranger.class);
    }
  }

  public static List<Sighting> getSightingDetails(int id) {
   try (Connection con = DB.sql2o.open()) {
     String sql = "SELECT sightings.location, sightings.time FROM sightings INNER JOIN wildlife_animals ON sightings.animal_id = wildlife_animals.id WHERE wildlife_animals.id = :id;";
     return con.createQuery(sql)
       .addParameter("id", id)
       .executeAndFetch(Sighting.class);
    }
  }

  public static Integer getTotalSightings(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT COUNT (*) FROM sightings WHERE animal_id = :id;";
      return con.createQuery(sql)
       .addParameter("id", id)
       .executeScalar(Integer.class);
     }
  }

}
