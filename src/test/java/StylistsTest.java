import java.util.List;
import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;


public class StylistsTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylists_instantiates_correctly(){
    Stylists newStylists = new Stylists("Keith2", "Stylists");
    assertTrue(newStylists instanceof Stylists);
  }

  @Test
  public void test_all_the_getters(){
    Stylists newStylist = new Stylists("keith", "Stylists");
    assertEquals("keith", newStylist.getName());
    assertEquals("Stylists", newStylist.getInfo());
  }

  @Test
  public void stylists_save_works(){
    Stylists newStylist = new Stylists("keith", "Stylists");
    newStylist.save();
    assertTrue(newStylist.all() instanceof List<?>);
  }

  @Test
  public void stylists_find_works(){
    Stylists newStylist = new Stylists("keith", "Stylists");
    newStylist.save();
    assertTrue(newStylist.find(newStylist.getId()) instanceof Stylists);
  }

  @Test
  public void stylists_all_works(){
    Stylists newStylists = new Stylists("keith","dude");
    newStylists.save();
    assertTrue(Stylists.all() instanceof List<?>);
  }

  @Test
  public void stylists_update_works(){
    Stylists newStylist = new Stylists("keith", "dude");
    newStylist.save();
    newStylist.update("developer");
    assertEquals("developer", Stylists.find(newStylist.getId()));
  }

  @Test
  public void stylists_deletes_works() {
    Stylists newStylists = new Stylists("Keith2", "Clients");
    newStylists.save();
    int newStylistsId = newStylists.getId();
    newStylists.delete();
    assertEquals(null, Stylists.find(newStylistsId));
  }
}