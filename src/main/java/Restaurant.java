import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int id;
  private String name;
  private int cuisineId;
  private int priceRange;
  private String address;
  private int regionId;
  private String hours;

  public Restaurant (String name, int cuisine) {
    this.name = name;
    cuisineId = cuisine;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getCuisineId() {
    return cuisineId;
  }

  @Override
  public boolean equals(Object otherRestaurant){
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
        this.getCuisineId() == newRestaurant.getCuisineId() &&
        this.getId() == newRestaurant.getId();
    }
  }

  //CREATE
  public void save() {
    String sql = "INSERT INTO restaurants (name, cuisineId) VALUES (:name, :cuisineId)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("cuisineId", cuisineId)
        .executeUpdate()
        .getKey();
    }
  }

  //READ
  public static List<Restaurant> all() {
    String sql = "SELECT * FROM restaurants";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Restaurant.class);
    }
  }

  //UPDATE
  public void update(String newName) {
    this.name = newName;
    String sql = "UPDATE restaurants SET name = :name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  //DELETE
  public void delete() {
    String sql = "DELETE FROM restaurants WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static Restaurant find(int id) {
    String sql = "SELECT * FROM restaurants WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Restaurant.class);
    }
  }

  public String getCuisineType() {
    String sql = "SELECT type FROM cuisine WHERE cuisineid = (SELECT cuisineId FROM restaurants WHERE id=:id)";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(String.class);
    }

  }

  /******************************************************
    Students:
    **TODO: Create find method
    TODO: Create method to get cuisine type
  *******************************************************/

}
