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
  public void EndangeredAnimal_instantiatesCorrectly_false() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
  }

  @Test
  public void getName_EndangeredAnimalInstantiatesWithName_Deer() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("Deer", testEndangeredAnimal.getName());
  }

  @Test
  public void getHealth_EndangeredAnimalInstantiatesWithHealth_Deer() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("healthy", testEndangeredAnimal.getHealth());
  }

  @Test
  public void getAge_EndangeredAnimalInstantiatesWithAge_Deer() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("3", testEndangeredAnimal.getAge());
  }

  @Test
  public void getColor_EndangeredAnimalInstantiatesWithColor_Deer() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("brown", testEndangeredAnimal.getColor());
  }

  @Test
  public void getDescription_EndangeredAnimalInstantiatesWithDescription_Deer() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("white spots", testEndangeredAnimal.getDescription());
  }

  @Test
  public void getGender_EndangeredAnimalInstantiatesWithGender_Deer() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("Male", testEndangeredAnimal.getGender());
  }

  @Test
  public void getEndangered_EndangeredAnimalInstantiatesWithEnda_Deer() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals(false, testEndangeredAnimal.getEndangered());
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame_false() {
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

  // @Test
  // public void getSightings_returnsAllSightingsForSpecificWildlife_true() {
  //   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
  //   testEndangeredAnimal.save();
  //   Sighting testSighting1 = new Sighting(testEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
  //   testSighting1.save();
  //   Sighting testSighting2 = new Sighting(testEndangeredAnimal.getId(), "47.472428, -123.946466", "Ranger Charles");
  //   testSighting2.save();
  //   Sighting testSighting3 = new Sighting(900, "47.472428, -123.946466", "Ranger Charles");
  //   testSighting3.save();
  //   Sighting [] sightings = new Sightings [] {testSighting1, testSighting2};
  //   assertTrue(EndangeredAnimal.getSightings().containsAll(Arrays.asList(sightings)));
  //   assertFalse(EndangeredAnimal.getSightings().contains(testSighting3));
  // }

  @Test //specific to endangeredEndangeredAnimals
  public void all_returnsAllInstancesOfEndangeredEndangeredAnimal_true() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Deer", "healthy", "3", "brown", "white spots", "Male", true);
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Black Bear", "Ill", "5", "black", "injured foot", "Female", true);
    secondEndangeredAnimal.save();
    assertEquals(true, EndangeredAnimal.all().get(0).equals(firstEndangeredAnimal));
    assertEquals(true, EndangeredAnimal.all().get(1).equals(secondEndangeredAnimal));
  }
}
