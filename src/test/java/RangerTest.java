import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class RangerTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void ranger_instantiatesCorrectly() {
    Ranger testRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    assertTrue(testRanger instanceof Ranger);
  }

  @Test
  public void getRanger_instantiatesCorrectly_boolean() {
    Ranger testRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    assertEquals(true, testRanger.getRanger());
  }

  @Test
  public void getName_instantiatesCorrectly_String() {
    Ranger testRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    assertEquals("Randy", testRanger.getName());
  }

  @Test
  public void getPhone_instantiatesCorrectly_String() {
    Ranger testRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    assertEquals("802-234-9873", testRanger.getPhone());
  }

  @Test
  public void getId_instantiatesCorrectly_String() {
    Ranger testRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    testRanger.save();
    assertTrue(testRanger.getId() > 0);
  }

  @Test
  public void getRangerNumber_instantiatesCorrectly_String() {
    Ranger testRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    assertEquals(234, testRanger.getRangerNumber());
  }

  @Test
  public void all_returnsAllInstancesOfRanger_true() {
    Ranger testRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    testRanger.save();
    Ranger otherRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    otherRanger.save();
    assertTrue(Ranger.all().get(0).equals(testRanger));
    assertTrue(Ranger.all().get(1).equals(otherRanger));
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame_true() {
    Ranger testRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    Ranger otherRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    assertTrue(testRanger.equals(otherRanger));
  }

  @Test
  public void save_assignsIdToObjectAndSavesObjectToDatabase() {
    Ranger testRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    testRanger.save();
    Ranger savedRanger = Ranger.all().get(0);
    assertEquals(testRanger.getId(), savedRanger.getId());
  }

  @Test
  public void delete_deletesRangerFromDatabase_0() {
    Ranger testRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    testRanger.save();
    testRanger.delete();
    assertEquals(0, Ranger.all().size());
  }

  @Test
  public void find_returnsRangerWithSameId_secondRanger() {
    Ranger testRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    testRanger.save();
    Ranger otherRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    otherRanger.save();
    assertEquals(otherRanger, Ranger.find(otherRanger.getId()));
  }

  @Test
  public void findByPhone_returnsRangerWithSamePhone_secondRanger() {
    Ranger testRanger = new Ranger(true, "Randy", "822-234-9873", 234);
    testRanger.save();
    Ranger otherRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    otherRanger.save();
    assertEquals(otherRanger.getPhone(), Ranger.find(otherRanger.getId()).getPhone());
  }

  @Test
  public void update_updatesRangerNamePhone_true() {
    Ranger testRanger = new Ranger(true, "Randy", "802-234-9873", 234);
    testRanger.save();
    testRanger.updateRanger("Randy Joe", "909-234-2212");
    assertEquals("Randy Joe", Ranger.find(testRanger.getId()).getName());
    assertEquals("909-234-2212", Ranger.find(testRanger.getId()).getPhone());
  }

  @Test
  public void getAnimals_returnsListOfRangersInfo(){
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
    assertNotNull(testRanger.getAnimals(testRanger.getId()));
  }
}
