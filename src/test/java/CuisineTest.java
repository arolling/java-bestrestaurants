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
  public void save_returnsTrueIfTypesAreTheSame() {
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

  @Test
  public void update_updatesTheNameOfACuisine_true() {
    Cuisine testCuisine = new Cuisine("Italian");
    testCuisine.save();
    testCuisine.update("Chinese");
    Cuisine savedCuisine = Cuisine.all().get(0);
    assertEquals(savedCuisine.getType(), "Chinese");
  }

  @Test
  public void find_findsTheSpecificCuisineBasedOnId() {
    Cuisine testCuisine = new Cuisine("Mexican");
    Cuisine testCuisine2 = new Cuisine("Italian5");
    testCuisine.save();
    testCuisine2.save();
    assertEquals(Cuisine.find(testCuisine.getId()).getType(), "Mexican");
    assertTrue(Cuisine.find(testCuisine2.getId()).equals(testCuisine2));
  }

  @Test
  public void delete_deletesTheSpecifiedCuisineBasedOnId() {
    Cuisine testCuisine = new Cuisine("Mexican2");
    Cuisine testCuisine2 = new Cuisine("Italian2");
    testCuisine.save();
    testCuisine2.save();
    testCuisine.delete();
    assertFalse(Cuisine.all().contains(testCuisine));
    assertTrue(Cuisine.all().contains(testCuisine2));
  }

  @Test
  public void getRestaurants_getsRestaurantsWithSpecificCuisine() {
    Cuisine testCuisine = new Cuisine("Mexican3");
    Cuisine testCuisine2 = new Cuisine("Italian3");
    testCuisine.save();
    testCuisine2.save();
    Restaurant testRestaurant = new Restaurant("Mexican Palace", testCuisine.getId());
    Restaurant testRestaurant2 = new Restaurant("Italian Pizzeria", testCuisine2.getId());
    Restaurant testRestaurant3 = new Restaurant("Mexican Grill", testCuisine.getId());
    testRestaurant.save();
    testRestaurant2.save();
    testRestaurant3.save();
    assertEquals(testCuisine.getRestaurants().size(), 2);
    assertTrue(testCuisine.getRestaurants().contains(testRestaurant3));
  }


}
