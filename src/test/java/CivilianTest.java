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
  public void civilian_instantiatesCorrectly_true() {
    Civilian testcivilian = new Civilian(true, "Randy", "802-234-9873");
    assertEquals(true, testcivilian instanceof Civilian);
  }

  @Test
  public void getRanger_instantiatesCorrectly_boolean() {
    Civilian testcivilian = new Civilian(true, "Randy", "802-234-9873");
    assertEquals(true, testcivilian.getRanger());
  }

  @Test
  public void getName_instantiatesCorrectly_String() {
    Civilian testcivilian = new Civilian(true, "Randy", "802-234-9873");
    assertEquals("Randy", testcivilian.getName());
  }

  @Test
  public void getPhone_instantiatesCorrectly_String() {
    Civilian testcivilian = new Civilian(true, "Randy", "802-234-9873");
    assertEquals("802-234-9873", testcivilian.getPhone());
  }
}
