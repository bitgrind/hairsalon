import java.util.List;
import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;


public class ClientsTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Clients_instantiates_correctly(){
    Clients newClients = new Clients("Keith2");
    assertTrue(newClients instanceof Clients);
  }
}