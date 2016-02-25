import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Favorite Restaurants");
  }

  @Test
  public void assertsThatAllRestaurantsDisplayOnHomePage() {
    Restaurant testRestaurant = new Restaurant("Fado", 2);
    testRestaurant.save();
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Fado");
  }

  @Test
  public void addANewRestaurant() {
    Cuisine testCuisine = new Cuisine("Mexican7");
    Cuisine testCuisine2 = new Cuisine("Italian7");
    testCuisine.save();
    testCuisine2.save();
    goTo("http://localhost:4567/");
    click("a", withText("Add New Restaurant"));
    fill("#restaurantName").with("Mexican Palace");
    Select select = new Select(webDriver.findElement(By.id("selectCuisine")));
    select.selectByValue("" + testCuisine.getId());
    submit("#addRestaurant");
    assertThat(pageSource()).contains("Mexican Palace");
  }

  @Test
  public void addANewCuisine(){
    Cuisine testCuisine = new Cuisine("Mexican9");
    Cuisine testCuisine2 = new Cuisine("Italian9");
    testCuisine.save();
    testCuisine2.save();
    Restaurant testRestaurant = new Restaurant("Fado", testCuisine2.getId());
    testRestaurant.save();
    Restaurant testRestaurant2 = new Restaurant("Pedro's", testCuisine.getId());
    testRestaurant2.save();
    goTo("http://localhost:4567/new-restaurant");
    fill("#cuisineType").with("Sushi");
    submit("#addCuisine");
    assertThat(pageSource()).contains("Sushi");
    assertThat(pageSource()).contains("Fado");
  }

  @Test
  public void getPageForSpecificRestaurant() {
    Cuisine testCuisine = new Cuisine("Mexican");
    Cuisine testCuisine2 = new Cuisine("Italian");
    testCuisine.save();
    testCuisine2.save();
    Restaurant testRestaurant = new Restaurant("Fado", testCuisine2.getId());
    testRestaurant.save();
    Restaurant testRestaurant2 = new Restaurant("Pedro's", testCuisine.getId());
    testRestaurant2.save();
    goTo("http://localhost:4567/");
    click("a", withText("Fado"));
    assertThat(pageSource()).contains("Italian");
  }

  @Test
  public void addNewAddressOnlyToRestaurant() {
    Cuisine testCuisine = new Cuisine("Mexican");
    Cuisine testCuisine2 = new Cuisine("Italian");
    testCuisine.save();
    testCuisine2.save();
    Restaurant testRestaurant = new Restaurant("Fado", testCuisine2.getId());
    testRestaurant.save();
    Restaurant testRestaurant2 = new Restaurant("Pedro's", testCuisine.getId());
    testRestaurant2.save();
    goTo("http://localhost:4567/restaurant/" + testRestaurant.getId());
    fill("#address").with("1200 Main St");
    submit("#restaurantInfo");
    assertThat(pageSource()).contains("1200 Main St");
  }

  @Test
  public void addNewOptionalInformationToRestaurant() {
    Cuisine testCuisine = new Cuisine("Mexican");
    Cuisine testCuisine2 = new Cuisine("Italian");
    testCuisine.save();
    testCuisine2.save();
    Restaurant testRestaurant = new Restaurant("Fado", testCuisine2.getId());
    testRestaurant.save();
    Restaurant testRestaurant2 = new Restaurant("Pedro's", testCuisine.getId());
    testRestaurant2.save();
    goTo("http://localhost:4567/restaurant/" + testRestaurant.getId());
    fill("#hours").with("4-11 Tuesday through Sunday");
    Select select = new Select(webDriver.findElement(By.id("selectPrice")));
    select.selectByValue("3");
    Select select2 = new Select(webDriver.findElement(By.id("selectArea")));
    select2.selectByValue("3");
    submit("#restaurantInfo");
    assertThat(pageSource()).contains("4-11 Tuesday through Sunday");
    assertThat(pageSource()).contains("Price:");
    assertThat(pageSource()).contains("Area:");
    assertEquals(testRestaurant.getArea(), "Southeast Portland");
  }

  @Test
  public void filterRestaurantsByCuisineType() {
    Cuisine testCuisine = new Cuisine("Mexican");
    Cuisine testCuisine2 = new Cuisine("Italian");
    testCuisine.save();
    testCuisine2.save();
    Restaurant testRestaurant = new Restaurant("Fado", testCuisine2.getId());
    testRestaurant.save();
    Restaurant testRestaurant2 = new Restaurant("Pedro's", testCuisine.getId());
    testRestaurant2.save();
    Restaurant testRestaurant3 = new Restaurant("Pizza Hut", testCuisine2.getId());
    testRestaurant3.save();
    goTo("http://localhost:4567/");
    click("a", withText("Italian"));
    assertThat(!(pageSource()).contains("Pedro's"));
    assertThat(pageSource()).contains("Fado");
  }
}
