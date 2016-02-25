import java.util.Map;
import java.util.HashMap;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("allCuisines", Cuisine.all());
      model.put("allRestaurants", Restaurant.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("restaurantName");
      String cuisineIdString = request.queryParams("selectCuisine");
      int cuisineId = Integer.parseInt(cuisineIdString);
      Restaurant newRestaurant = new Restaurant(name, cuisineId);
      newRestaurant.save();
      model.put("allRestaurants", Restaurant.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/new-restaurant", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("allRestaurants", Restaurant.all());
      model.put("allCuisines", Cuisine.all());
      model.put("template", "templates/newrestaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/new-restaurant", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String type = request.queryParams("cuisineType");
      Cuisine newCuisine = new Cuisine(type);
      newCuisine.save();
      model.put("allRestaurants", Restaurant.all());
      model.put("allCuisines", Cuisine.all());
      model.put("template", "templates/newrestaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/restaurant/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Restaurant restaurant = Restaurant.find(id);
      model.put("regions", Region.all());
      model.put("restaurant", restaurant);
      model.put("template", "templates/onerestaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/restaurant/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Restaurant restaurant = Restaurant.find(id);
      String address = request.queryParams("address");
      if (address.length() > 0){
        restaurant.setAddress(address);
      }
      String hours = request.queryParams("hours");
      if (hours.length() > 0) {
        restaurant.setHours(hours);
      }
      int priceRange = Integer.parseInt(request.queryParams("selectPrice"));
      if (priceRange > 0) {
        restaurant.setPriceRange(priceRange);
      }
      int regionId = Integer.parseInt(request.queryParams("selectArea"));
      if (regionId > 0) {
        restaurant.setRegion(regionId);
      }
      model.put("regions", Region.all());
      model.put("restaurant", restaurant);
      model.put("template", "templates/onerestaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());






    /******************************************************
    STUDENTS:
    **TODO: Create page to display information about the selected restaurant
    --INCLUDING FORM TO ADD OPTIONAL DATA
    TODO: Create page to display restaurants by cuisine type
    *******************************************************/

  }
}
