import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Timestamp;
import java.util.Date;

public class Sighting {
  private int animal_id;
  private int viewer_id;
  private String location;
  private Timestamp time;
  private int id;

  public Sighting(int animal_id, int viewer_id, String location) {
    this.animal_id = animal_id;
    this.viewer_id = viewer_id;
    this.location = location;
  }

  public int getId() {
    return id;
  }

  public int getAnimalId() {
    return animal_id;
  }

  public String getLocation() {
    return location;
  }

  public int getViewerId() {
    return viewer_id;
  }

  public Timestamp getSightingTime() {
    return time;
  }

  @Override
  public boolean equals(Object otherSighting) {
    if(!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.getAnimalId() == (newSighting.getAnimalId()) &&        this.getViewerId() == newSighting.getViewerId() &&   this.getLocation().equals(newSighting.getLocation());
    }
  }

  public void save() {
    if (location == null) {
        throw new IllegalArgumentException("A location must be entered!");
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (location, time, animal_id, viewer_id) VALUES (:location, now(), :animal_id, :viewer_id);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("location", this.location)
        .addParameter("animal_id", this.animal_id)
        .addParameter("viewer_id", this.viewer_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Sighting> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings;";
      return con.createQuery(sql)
        .executeAndFetch(Sighting.class);
    }
  }

  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE id=:id;";
      Sighting sighting = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Sighting.class);
      return sighting;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

  public List<Sighting> getAnimalSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT viewers.name FROM viewers INNER JOIN sightings ON viewers.id = sightings.viewer_id INNER JOIN wildlife_animals ON sightings.animal_id = wildlife_animals.id WHERE animal_id = :animal_id;";
      return con.createQuery(sql)
        .addParameter("animal_id", animal_id)
        .executeAndFetch(Sighting.class);
    }
  }

  public List<Sighting> getViewerSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT wildlife_animals.name FROM wildlife_animals INNER JOIN sightings ON wildlife_animals.id = sightings.animal_id INNER JOIN viewers ON sightings.viewer_id = viewers.id WHERE viewer_id = :viewer_id;";
      return con.createQuery(sql)
        .addParameter("viewer_id", viewer_id)
        .executeAndFetch(Sighting.class);
    }
  }

  public List<Sighting> getViewerSightingsNewestFirst() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT wildlife_animals.name FROM wildlife_animals INNER JOIN sightings ON wildlife_animals.id = sightings.animal_id INNER JOIN viewers ON sightings.viewer_id = viewers.id WHERE viewer_id = :viewer_id ORDER by sightings.time DESC;";
      return con.createQuery(sql)
        .addParameter("viewer_id", viewer_id)
        .executeAndFetch(Sighting.class);
    }
  }

}
