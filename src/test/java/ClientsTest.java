import java.util.List;
import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;


public class ClientsTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Clients_instantiates_correctly(){
    Clients newClients = new Clients("Keith2", "Clients", 1);
    assertTrue(newClients instanceof Clients);
  }

  @Test
  public void Clients_test_all_the_getters(){
    Clients newClients = new Clients("Keith2", "Clients", 1);
    assertEquals("Keith2", newClients.getName());
    assertEquals("Clients", newClients.getInfo());
  }

  @Test
  public void Clients_save_works(){
    Clients newClients = new Clients("Keith2", "Clients", 1);
    newClients.save();
    assertTrue(newClients.all() instanceof List<?>);
  }

  @Test
  public void clients_find_works(){
    Clients newClient = new Clients("Keith2", "Clients", 1);
    newClient.save();
    assertTrue(newClient.find(newClient.getId()) instanceof Clients);
  }

  @Test
  public void clients_all_works(){
    Clients newClient = new Clients("Keith2", "Clients", 1);
    newClient.save();
    assertTrue(Clients.all() instanceof List<?>);
  }

  @Test
  public void clients_update_works(){
    Clients newClient = new Clients("Keith2", "Clients", 1);
    newClient.save();
    newClient.update("difficult");
    assertEquals("difficult", Clients.find(newClient.getId()));
  }

  @Test
  public void clients_deletes_works() {
    Clients newClient = new Clients("Keith2", "Clients", 1);
    newClient.save();
    int newClientId = newClient.getId();
    newClient.delete();
    assertEquals(null, Clients.find(newClientId));
  }
}