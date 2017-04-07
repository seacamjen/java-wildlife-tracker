import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Animal extends Wildlife{


  public Animal(String name, String health, String age, String color, String description, String gender, boolean endangered) {
    this.name = name;
    this.id = id;
    this.health = health;
    this.age = age;
    this.color = color;
    this.gender = gender;
    this.endangered = endangered;
  }

  // @Override
  // public boolean equals(Object otherAnimal) {
  //   if(!(otherAnimal instanceof Animal)) {
  //     return false;
  //   } else {
  //     Animal newAnimal = (Animal) otherAnimal;
  //     return this.getName().equals(newAnimal.getName());
  //   }
  // }
  //
  // public void save() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO animals (name) VALUES (:name);";
  //     this.id = (int) con.createQuery(sql, true)
  //       .addParameter("name", this.name)
  //       .executeUpdate()
  //       .getKey();
  //   }
  // }

  public static List<Animal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM wildlife_animals WHERE endangered = false;";
      return con.createQuery(sql)
        .executeAndFetch(Animal.class);
    }
  }

  // public static Animal find(int id) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM animals WHERE id=:id;";
  //     Animal animal = con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeAndFetchFirst(Animal.class);
  //     return animal;
  //   }
  // }

  // public void updateName(String name) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE animals SET name=:name WHERE id=:id;";
  //     con.createQuery(sql)
  //       .addParameter("id", id)
  //       .addParameter("name", name)
  //       .executeUpdate();
  //   }
  // }

  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "DELETE FROM animals WHERE id=:id;";
  //     con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }

  // public List<Sighting> getSightings() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
  //       List<Sighting> sightings = con.createQuery(sql)
  //         .addParameter("id", id)
  //         .executeAndFetch(Sighting.class);
  //     return sightings;
  //   }
  // }

}
