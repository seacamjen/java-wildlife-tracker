import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class SightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void sighting_instantiatesCorrectly_true() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
    testCivilian.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), testCivilian.getId(), "45.472428, -121.946466");
    assertEquals(true, testSighting instanceof Sighting);
  }

  // @Test
  // public void getId_instantiatesAndReturnsId() {
  //   Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
  //   testAnimal.save();
  //   Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
  //   testCivilian.save();
  //   Sighting testSighting = new Sighting(testAnimal.getId(), testCivilian.getId(), "45.472428, -121.946466");
  //   assertEquals(blah)
  // }

  @Test
  public void getAnimalId_instantiatesAndReturnsId() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
    testCivilian.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), testCivilian.getId(), "45.472428, -121.946466");
    assertEquals(testAnimal.getId(), testSighting.getAnimalId());
  }

  @Test
  public void getViewerId_instantiatesAndReturnsId() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
    testCivilian.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), testCivilian.getId(), "45.472428, -121.946466");
    assertEquals(testCivilian.getId(), testSighting.getViewerId());
  }

  @Test
  public void getLocation_instantiatesAndReturnsId() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
    testCivilian.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), testCivilian.getId(), "45.472428, -121.946466");
    assertEquals("45.472428, -121.946466", testSighting.getLocation());
  }

  @Test
  public void getTime_instantiatesAndReturnsId() {
    Sighting testSighting = new Sighting(1, 2, "45.472428, -121.946466");
    testSighting.save();
    Timestamp savedSighting = Sighting.find(testSighting.getId()).getSightingTime();
    Timestamp rightNow = new Timestamp(new Date().getTime());
    assertEquals(rightNow.getDay(), savedSighting.getDay());
  }

  @Test
  public void equals_returnsTrueIfLocationAndDescriptionAreSame_true() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
    testCivilian.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), testCivilian.getId(), "45.472428, -121.946466");
    Sighting anotherSighting = new Sighting(testAnimal.getId(), testCivilian.getId(), "45.472428, -121.946466");
    assertTrue(testSighting.equals(anotherSighting));
  }

  @Test
  public void save_insertsObjectIntoDatabase_true() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
    testCivilian.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), testCivilian.getId(), "45.472428, -121.946466");
    testSighting.save();
    Sighting savedSighting = Sighting.all().get(0);
    assertEquals(savedSighting.getId(), testSighting.getId());
  }

  @Test
  public void all_returnsAllInstancesOfSighting_true() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
    testCivilian.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), testCivilian.getId(), "45.472428, -121.946466");
    testSighting.save();
    Animal testAnimal1 = new Animal("Goose", "healthy", "Old", "brown", "white spots", "Male", false);
    testAnimal1.save();
    Civilian testCivilian1 = new Civilian(false, "Bobby", "802-234-9873");
    testCivilian1.save();
    Sighting testSighting1 = new Sighting(testAnimal.getId(), testCivilian.getId(), "45.472428, -121.946466");
    testSighting1.save();
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
    assertEquals(true, Sighting.all().get(1).equals(testSighting1));
  }

  @Test
  public void find_returnsSightingWithSameId_secondSighting() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
    testCivilian.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), testCivilian.getId(), "45.472428, -121.946466");
    testSighting.save();
    Animal testAnimal1 = new Animal("Goose", "healthy", "Old", "brown", "white spots", "Male", false);
    testAnimal1.save();
    Civilian testCivilian1 = new Civilian(false, "Bobby", "802-234-9873");
    testCivilian1.save();
    Sighting testSighting1 = new Sighting(testAnimal.getId(), testCivilian.getId(), "45.472428, -121.946466");
    testSighting1.save();
    assertEquals(testSighting1, Sighting.find(testSighting1.getId()));
  }

  @Test
  public void find_returnsNullWhenNoAnimalFound_null() {
    assertTrue(Animal.find(999) == null);
  }

}
