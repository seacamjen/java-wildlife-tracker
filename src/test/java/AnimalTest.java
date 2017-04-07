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
  public void animal_instantiatesCorrectly_false() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals(true, testAnimal instanceof Animal);
  }

  @Test
  public void getName_animalInstantiatesWithName_Deer() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("Deer", testAnimal.getName());
  }

  @Test
  public void getHealth_animalInstantiatesWithHealth_Deer() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("healthy", testAnimal.getHealth());
  }

  @Test
  public void getAge_animalInstantiatesWithAge_Deer() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("3", testAnimal.getAge());
  }

  @Test
  public void getColor_animalInstantiatesWithColor_Deer() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("brown", testAnimal.getColor());
  }

  @Test
  public void getDescription_animalInstantiatesWithDescription_Deer() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("white spots", testAnimal.getDescription());
  }

  @Test
  public void getGender_animalInstantiatesWithGender_Deer() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals("Male", testAnimal.getGender());
  }

  @Test
  public void getEndangered_animalInstantiatesWithEnda_Deer() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals(false, testAnimal.getEndangered());
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame_false() {
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

  @Test //specific to animal class also need for endangeredanimal
  public void all_returnsAllInstancesOfAnimal_false() {
    Animal firstAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    firstAnimal.save();
    Animal secondAnimal = new Animal("Black Bear", "Ill", "5", "black", "injured foot", "Female", true);
    secondAnimal.save();
    assertEquals(true, Animal.all().get(0).equals(firstAnimal));
    assertEquals(true, Animal.all().get(1).equals(secondAnimal));
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
  public void getSightings_returnsAllSightingsForSpecificWildlife_true() {
    Animal testAnimal = new Animal("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    testAnimal.save();
    Sighting testSighting1 = new Sighting(testAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
    testSighting1.save();
    Sighting testSighting2 = new Sighting(testAnimal.getId(), "47.472428, -123.946466", "Ranger Charles");
    testSighting2.save();
    Sighting testSighting3 = new Sighting(900, "47.472428, -123.946466", "Ranger Charles");
    testSighting3.save();
    Sighting [] sightings = new Sightings [] {testSighting1, testSighting2};
    assertTrue(Animal.getSightings().containsAll(Arrays.asList(sightings)));
    assertFalse(Animal.getSightings().contains(testSighting3));
  }

  // @Test //dont know purpose of this test yet
  // public void find_returnsNullWhenNoAnimalFound_null() {
  //   assertTrue(Animal.find(999) == null);
  // }

}
