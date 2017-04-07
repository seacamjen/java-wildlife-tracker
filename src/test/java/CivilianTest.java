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
    Civilian testcivilian = new Civilian("Deer", "healthy", "3", "brown", "white spots", "Male", false);
    assertEquals(true, testcivilian instanceof Civilian);
  }
}
