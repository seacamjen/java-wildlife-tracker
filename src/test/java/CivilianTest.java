import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class CivilianTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void civilian_instantiatesCorrectly() {
    Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
    assertTrue(testCivilian instanceof Civilian);
  }

  @Test
  public void getRanger_instantiatesCorrectly_boolean() {
    Civilian testcivilian = new Civilian(false, "Randy", "802-234-9873");
    assertEquals(false, testcivilian.getRanger());
  }

  @Test
  public void getName_instantiatesCorrectly_String() {
    Civilian testcivilian = new Civilian(false, "Randy", "802-234-9873");
    assertEquals("Randy", testcivilian.getName());
  }

  @Test
  public void getPhone_instantiatesCorrectly_String() {
    Civilian testcivilian = new Civilian(false, "Randy", "802-234-9873");
    assertEquals("802-234-9873", testcivilian.getPhone());
  }

  @Test
  public void all_returnsAllInstancesOfCivilian_true() {
    Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
    testCivilian.save();
    Civilian otherCivilian = new Civilian(false, "Randy", "802-234-9873");
    otherCivilian.save();
    assertTrue(Civilian.all().get(0).equals(testCivilian));
    assertTrue(Civilian.all().get(1).equals(otherCivilian));
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame_true() {
    Civilian testcivilian = new Civilian(false, "Randy", "802-234-9873");
    Civilian othercivilian = new Civilian(false, "Randy", "802-234-9873");
    assertTrue(testcivilian.equals(othercivilian));
  }

  @Test
  public void save_assignsIdToObjectAndSavesObjectToDatabase() {
    Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
    testCivilian.save();
    Civilian savedCivilian = Civilian.all().get(0);
    assertEquals(testCivilian.getId(), savedCivilian.getId());
  }

  @Test
  public void delete_deletesCivilianFromDatabase_0() {
    Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
    testCivilian.save();
    testCivilian.delete();
    assertEquals(0, Civilian.all().size());
  }

  @Test
  public void find_returnsCivilianWithSameId_secondCivilian() {
    Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
    testCivilian.save();
    Civilian otherCivilian = new Civilian(false, "Randy", "802-234-9873");
    otherCivilian.save();
    assertEquals(otherCivilian, Civilian.find(otherCivilian.getId()));
  }

  @Test
  public void update_updatesCivilianNamePhone_true() {
    Civilian testCivilian = new Civilian(false, "Randy", "802-234-9873");
    testCivilian.save();
    testCivilian.updateCivilian("Randy Joe", "909-234-2212");
    assertEquals("Randy Joe", Civilian.find(testCivilian.getId()).getName());
    assertEquals("909-234-2212", Civilian.find(testCivilian.getId()).getPhone());
  }
}
