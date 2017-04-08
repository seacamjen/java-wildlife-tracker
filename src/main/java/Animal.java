import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Animal extends Wildlife {


  public Animal(String name, String health, String age, String color, String description, String gender, boolean endangered) {
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

  public static Animal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM wildlife_animals WHERE id=:id;";
      Animal animal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Animal.class);
      return animal;
    }
  }

  // public List<Sighting> getSightings() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
  //       List<Sighting> sightings = con.createQuery(sql)
  //         .addParameter("id", id)
  //         .executeAndFetch(Sighting.class);
  //     return sightings;
  //   }
  // }

  @Override
  public boolean equals(Object otherAnimal) {
    if(!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getName().equals(newAnimal.getName()) &&  this.getHealth().equals(newAnimal.getHealth()) && this.getAge().equals(newAnimal.getAge()) && this.getColor().equals(newAnimal.getColor()) && this.getDescription().equals(newAnimal.getDescription()) && this.getGender().equals(newAnimal.getGender()) && this.getEndangered() == newAnimal.getEndangered();
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

  public void updateName(String name) {
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

  public static List<Animal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM wildlife_animals WHERE endangered = false;";
      return con.createQuery(sql)
        .executeAndFetch(Animal.class);
    }
  }
//add a test
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
