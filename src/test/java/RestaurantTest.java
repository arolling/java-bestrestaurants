import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void save_returnsTrueIfTypesAndIdsAreTheSame() {
    Restaurant testRestaurant = new Restaurant("Italian Pizzeria", 1);
    testRestaurant.save();
    assertTrue(Restaurant.all().get(0).equals(testRestaurant));
  }

  @Test
  public void update_updatesTheNameOfARestaurant_true() {
    Restaurant testRestaurant = new Restaurant("Italian Pizzeria", 1);
    testRestaurant.save();
    testRestaurant.update("Chinese Takeout");
    Restaurant savedRestaurant = Restaurant.all().get(0);
    assertEquals(savedRestaurant.getName(), "Chinese Takeout");
  }

  @Test
  public void find_findsTheSpecificRestaurantBasedOnId() {
    Restaurant testRestaurant = new Restaurant("Mexican Palace", 1);
    Restaurant testRestaurant2 = new Restaurant("Italian Pizzeria", 2);
    testRestaurant.save();
    testRestaurant2.save();
    assertEquals(Restaurant.find(testRestaurant.getId()).getName(), "Mexican Palace");
    assertTrue(Restaurant.find(testRestaurant2.getId()).equals(testRestaurant2));
  }

  @Test
  public void delete_deletesTheSpecifiedRestaurantBasedOnId() {
    Restaurant testRestaurant = new Restaurant("Mexican Palace", 1);
    Restaurant testRestaurant2 = new Restaurant("Italian Pizzeria", 2);
    testRestaurant.save();
    testRestaurant2.save();
    testRestaurant.delete();
    assertFalse(Restaurant.all().contains(testRestaurant));
    assertTrue(Restaurant.all().contains(testRestaurant2));
  }
}
