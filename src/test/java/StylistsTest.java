import java.util.List;
import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;


public class StylistsTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylists_instantiates_correctly(){
    Stylists newStylists = new Stylists("Keith2");
    assertTrue(newStylists instanceof Stylists);
  }
}