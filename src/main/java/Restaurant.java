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

  public void setPriceRange(int price) {
    priceRange = price;
    String sql = "UPDATE restaurants SET pricerange = :priceRange WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("priceRange", priceRange)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public int getPriceRange() {
    return priceRange;
  }

  public void setAddress(String address) {
    this.address = address;
    String sql = "UPDATE restaurants SET address = :address WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("address", this.address)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public String getAddress() {
    return address;
  }

  public void setRegion(int region) {
    regionId = region;
    String sql = "UPDATE restaurants SET regionId = :regionId WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("regionId", this.regionId)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public int getRegionId() {
    return regionId;
  }

  public void setHours(String hours) {
    this.hours = hours;
    String sql = "UPDATE restaurants SET hours = :hours WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("hours", this.hours)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public String getHours() {
    return hours;
  }


  @Override
  public boolean equals(Object otherRestaurant){
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
        this.getCuisineId() == newRestaurant.getCuisineId() &&
        this.getPriceRange() == newRestaurant.getPriceRange() &&
        this.getRegionId() == newRestaurant.getRegionId() &&
        //this.getHours().equals(newRestaurant.getHours()) &&
        //this.getAddress().equals(newRestaurant.getAddress()) &&
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
    **TODO: Create method to get cuisine type
  *******************************************************/

}
