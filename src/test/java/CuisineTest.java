import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Cuisine.all().size(), 0);
  }

  @Test
  public void save_returnsTrueIfDescriptionsAreTheSame() {
    Cuisine testCuisine = new Cuisine("Italian");
    testCuisine.save();
    assertTrue(Cuisine.all().get(0).equals(testCuisine));
  }

  @Test
  public void save_addsIndexToObjectID_true() {
    Cuisine testCuisine = new Cuisine("Italian");
    testCuisine.save();
    Cuisine savedCuisine = Cuisine.all().get(0);
    assertEquals(savedCuisine.getId(), testCuisine.getId());
  }
}
