import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Civilian extends Viewers implements Person {

  public Civilian(boolean ranger, String name, String phone){
    this.ranger = ranger;
    this.name = name;
    this.phone = phone;
  }
}
