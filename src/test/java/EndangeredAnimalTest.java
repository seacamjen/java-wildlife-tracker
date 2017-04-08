import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class EndangeredAnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void EndangeredAnimal_instantiatesCorrectly_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
  }

  @Test
  public void getName_EndangeredAnimalInstantiatesWithName_String() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("Deer", testEndangeredAnimal.getName());
  }

  @Test
  public void getHealth_EndangeredAnimalInstantiatesWithHealth_String() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("healthy", testEndangeredAnimal.getHealth());
  }

  @Test
  public void getAge_EndangeredAnimalInstantiatesWithAge_String() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("3", testEndangeredAnimal.getAge());
  }

  @Test
  public void getColor_EndangeredAnimalInstantiatesWithColor_String() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("brown", testEndangeredAnimal.getColor());
  }

  @Test
  public void getDescription_EndangeredAnimalInstantiatesWithDescription_String() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("white spots", testEndangeredAnimal.getDescription());
  }

  @Test
  public void getGender_EndangeredAnimalInstantiatesWithGender_String() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("Male", testEndangeredAnimal.getGender());
  }

  @Test
  public void getId_EndangeredAnimalInstantiatesWithId_String() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testEndangeredAnimal.save();
    assertTrue(testEndangeredAnimal.getId() > 0);
  }

  @Test
  public void getEndangered_EndangeredAnimalInstantiatesWithEnda_boolean() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals(false, testEndangeredAnimal.getEndangered());
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame_true() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    EndangeredAnimal anotherEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertTrue(firstEndangeredAnimal.equals(anotherEndangeredAnimal));
  }

  @Test
  public void save_assignsIdToObjectAndSavesObjectToDatabase() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", true);
    testEndangeredAnimal.save();
    EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.all().get(0);
    assertEquals(testEndangeredAnimal.getId(), savedEndangeredAnimal.getId());
  }

  @Test
  public void find_returnsEndangeredAnimalWithSameId_secondEndangeredAnimal() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Black Bear", "Ill", "5", "black", "injured foot", "Female", true);
    secondEndangeredAnimal.save();
    assertEquals(EndangeredAnimal.find(secondEndangeredAnimal.getId()), secondEndangeredAnimal);
  }

  @Test
  public void delete_deletesEndangeredAnimalFromDatabase_0() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testEndangeredAnimal.save();
    testEndangeredAnimal.delete();
    assertEquals(0, EndangeredAnimal.all().size());
  }

  public void updateName_updatesEndangeredAnimalNameInDatabase_String() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testEndangeredAnimal.save();
    testEndangeredAnimal.updateName("Buck");
    assertEquals("Buck", testEndangeredAnimal.getName());
  }

  @Test
  public void update_updatesHealthAttribute_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testEndangeredAnimal.save();
    testEndangeredAnimal.updateHealth("ill");
    assertEquals("ill", EndangeredAnimal.find(testEndangeredAnimal.getId()).getHealth());
  }

  @Test
  public void update_updatesAgeAttribute_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testEndangeredAnimal.save();
    testEndangeredAnimal.updateAge("Adult");
    assertEquals("Adult", EndangeredAnimal.find(testEndangeredAnimal.getId()).getAge());
  }

  @Test
  public void update_updatesDescriptionAttribute_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testEndangeredAnimal.save();
    testEndangeredAnimal.updateDescription("broken foot");
    assertEquals("broken foot", EndangeredAnimal.find(testEndangeredAnimal.getId()).getDescription());
  }

  @Test
  public void all_returnsAllInstancesOfEndangeredEndangeredAnimal_true() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", true);
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Black Bear", "Ill", "5", "black", "injured foot", "Female", true);
    secondEndangeredAnimal.save();
    assertEquals(true, EndangeredAnimal.all().get(0).equals(firstEndangeredAnimal));
    assertEquals(true, EndangeredAnimal.all().get(1).equals(secondEndangeredAnimal));
  }

  @Test
  public void getRangers_returnsListOfRangersInfo(){
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testEndangeredAnimal.save();
    Ranger testRanger = new Ranger(false, "Randy", "802-234-9873", 1);
    testRanger.save();
    Sighting testSighting = new Sighting(testEndangeredAnimal.getId(), testRanger.getId(), "45.472428, -121.946466");
    testSighting.save();
    EndangeredAnimal testEndangeredAnimal1 = new EndangeredAnimal("Goose", "healthy", "Old", "brown", "white spots", "Male", false);
    testEndangeredAnimal1.save();
    Ranger testRanger1 = new Ranger(false, "Bobby", "802-234-9873", 2);
    testRanger1.save();
    Sighting testSighting1 = new Sighting(testEndangeredAnimal.getId(), testRanger.getId(), "45.472428, -121.946466");
    testSighting1.save();
    assertNotNull(testEndangeredAnimal.getRangers(testEndangeredAnimal.getId()));
  }

  @Test
  public void getSightingDetials_returnsListOfSightingDetails() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testEndangeredAnimal.save();
    Ranger testRanger = new Ranger(false, "Randy", "802-234-9873", 1);
    testRanger.save();
    Sighting testSighting = new Sighting(testEndangeredAnimal.getId(), testRanger.getId(), "45.472428, -121.946466");
    testSighting.save();
    EndangeredAnimal testEndangeredAnimal1 = new EndangeredAnimal("Goose", "healthy", "Old", "brown", "white spots", "Male", false);
    testEndangeredAnimal1.save();
    Ranger testRanger1 = new Ranger(false, "Bobby", "802-234-9873", 2);
    testRanger1.save();
    Sighting testSighting1 = new Sighting(testEndangeredAnimal.getId(), testRanger.getId(), "45.472428, -121.946466");
    testSighting1.save();
    assertNotNull(testEndangeredAnimal.getSightingDetails(testEndangeredAnimal.getId()));
  }

  @Test
  public void getTotalSightings_returnsNumberOfSightings_int() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testEndangeredAnimal.save();
    Ranger testRanger = new Ranger(false, "Randy", "802-234-9873", 1);
    testRanger.save();
    Sighting testSighting = new Sighting(testEndangeredAnimal.getId(), testRanger.getId(), "45.472428, -121.946466");
    testSighting.save();
    EndangeredAnimal testEndangeredAnimal1 = new EndangeredAnimal("Goose", "healthy", "Old", "brown", "white spots", "Male", false);
    testEndangeredAnimal1.save();
    Ranger testRanger1 = new Ranger(false, "Bobby", "802-234-9873", 2);
    testRanger1.save();
    Sighting testSighting1 = new Sighting(testEndangeredAnimal.getId(), testRanger.getId(), "45.472428, -121.946466");
    testSighting1.save();
    assertEquals((Integer)2, testEndangeredAnimal.getTotalSightings(testEndangeredAnimal.getId()));
  }
}
