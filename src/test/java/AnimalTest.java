import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class AnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void animal_instantiatesCorrectly_true() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals(true, testAnimal instanceof Animal);
  }

  @Test
  public void getName_animalInstantiatesWithName_String() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("Deer", testAnimal.getName());
  }

  @Test
  public void getHealth_animalInstantiatesWithHealth_String() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("healthy", testAnimal.getHealth());
  }

  @Test
  public void getAge_animalInstantiatesWithAge_String() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("3", testAnimal.getAge());
  }

  @Test
  public void getColor_animalInstantiatesWithColor_String() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("brown", testAnimal.getColor());
  }

  @Test
  public void getDescription_animalInstantiatesWithDescription_String() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("white spots", testAnimal.getDescription());
  }

  @Test
  public void getGender_animalInstantiatesWithGender_String() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("Male", testAnimal.getGender());
  }

  @Test
  public void getEndangered_animalInstantiatesWithEnda_String() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals(false, testAnimal.getEndangered());
  }

  @Test
  public void getId_animalInstantiatesWithEnda_String() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    assertTrue(testAnimal.getId() > 0);
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame_true() {
    Animal firstAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    Animal anotherAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertTrue(firstAnimal.equals(anotherAnimal));
  }

  @Test
  public void save_assignsIdToObjectAndSavesObjectToDatabase() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    Animal savedAnimal = Animal.all().get(0);
    assertEquals(testAnimal.getId(), savedAnimal.getId());
  }

  @Test
  public void find_returnsAnimalWithSameId_secondAnimal() {
    Animal firstAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    firstAnimal.save();
    Animal secondAnimal = new Animal("Black Bear", "Ill", "5", "black", "injured foot", "Female", true);
    secondAnimal.save();
    assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
  }

  @Test
  public void delete_deletesAnimalFromDatabase_0() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    testAnimal.delete();
    assertEquals(0, Animal.all().size());
  }

  public void updateName_updatesAnimalNameInDatabase_String() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    testAnimal.updateName("Buck");
    assertEquals("Buck", testAnimal.getName());
  }

  @Test
  public void update_updatesHealthAttribute_true() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    testAnimal.updateHealth("ill");
    assertEquals("ill", Animal.find(testAnimal.getId()).getHealth());
  }

  @Test
  public void update_updatesAgeAttribute_true() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    testAnimal.updateAge("Adult");
    assertEquals("Adult", Animal.find(testAnimal.getId()).getAge());
  }

  @Test
  public void update_updatesDescriptionAttribute_true() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    testAnimal.updateDescription("broken foot");
    assertEquals("broken foot", Animal.find(testAnimal.getId()).getDescription());
  }


  @Test
  public void all_returnsAllInstancesOfAnimal_true() {
    Animal firstAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    firstAnimal.save();
    Animal secondAnimal = new Animal("Black Bear", "Ill", "5", "black", "injured foot", "Female", false);
    secondAnimal.save();
    assertTrue(Animal.all().get(0).equals(firstAnimal));
    assertTrue(Animal.all().get(1).equals(secondAnimal));
  }

  @Test
  public void find_returnsNullWhenNoAnimalFound_null() {
    Animal firstAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    firstAnimal.save();
    assertTrue(Animal.find(999) == null);
  }

  @Test
  public void getRangers_returnsListOfRangersInfo(){
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    Ranger testRanger = new Ranger(false, "Randy", "802-234-9873", 1);
    testRanger.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), testRanger.getId(), "45.472428, -121.946466");
    testSighting.save();
    Animal testAnimal1 = new Animal("Goose", "healthy", "Old", "brown", "white spots", "Male", false);
    testAnimal1.save();
    Ranger testRanger1 = new Ranger(false, "Bobby", "802-234-9873", 2);
    testRanger1.save();
    Sighting testSighting1 = new Sighting(testAnimal.getId(), testRanger.getId(), "45.472428, -121.946466");
    testSighting1.save();
    assertNotNull(testAnimal.getRangers(testAnimal.getId()));
  }

  @Test
  public void getSightingDetials_returnsListOfSightingDetails() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    Ranger testRanger = new Ranger(false, "Randy", "802-234-9873", 1);
    testRanger.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), testRanger.getId(), "45.472428, -121.946466");
    testSighting.save();
    Animal testAnimal1 = new Animal("Goose", "healthy", "Old", "brown", "white spots", "Male", false);
    testAnimal1.save();
    Ranger testRanger1 = new Ranger(false, "Bobby", "802-234-9873", 2);
    testRanger1.save();
    Sighting testSighting1 = new Sighting(testAnimal.getId(), testRanger.getId(), "45.472428, -121.946466");
    testSighting1.save();
    assertNotNull(testAnimal.getSightingDetails(testAnimal.getId()));
  }

  @Test
  public void getTotalSightings_returnsNumberOfSightings_int() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    Ranger testRanger = new Ranger(false, "Randy", "802-234-9873", 1);
    testRanger.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), testRanger.getId(), "45.472428, -121.946466");
    testSighting.save();
    Animal testAnimal1 = new Animal("Goose", "healthy", "Old", "brown", "white spots", "Male", false);
    testAnimal1.save();
    Ranger testRanger1 = new Ranger(false, "Bobby", "802-234-9873", 2);
    testRanger1.save();
    Sighting testSighting1 = new Sighting(testAnimal.getId(), testRanger.getId(), "45.472428, -121.946466");
    testSighting1.save();
    assertEquals((Integer)2, testAnimal.getTotalSightings(testAnimal.getId()));
  }

}
