import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class RegionTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_nineEntriesAtFirst() {
    assertEquals(Region.all().size(), 9);
  }

  @Test
  public void region_instantiatesCorrectly_true() {
    Region testRegion = new Region("North Portland");
    assertEquals(true, testRegion instanceof Region);
  }

  

}
