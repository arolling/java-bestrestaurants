import org.sql2o.*;
import java.util.List;

public class Cuisine {
  private int cuisineId;
  private String type;

  public Cuisine (String type) {
    this.type = type;
  }

  public int getId() {
    return cuisineId;
  }

  public String getType() {
    return type;
  }

  @Override
  public boolean equals(Object otherCuisine){
    if (!(otherCuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getType().equals(newCuisine.getType()) &&
        this.getId() == newCuisine.getId();
    }
  }

  //CREATE
  public void save() {
    String sql = "INSERT INTO cuisine (type) VALUES (:type)";
    try (Connection con = DB.sql2o.open()) {
      this.cuisineId = (int) con.createQuery(sql, true)
        .addParameter("type", type)
        .executeUpdate()
        .getKey();
    }
  }

  //READ
  public static List<Cuisine> all() {
    String sql = "SELECT DISTINCT ON (type) * FROM cuisine ORDER BY type, cuisineid";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Cuisine.class);
    }
  }

  //UPDATE
  public void update(String newType) {
    this.type = newType;
    String sql = "UPDATE cuisine SET type = :type WHERE cuisineId = :cuisineId";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("type", type)
        .addParameter("cuisineId", cuisineId)
        .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM cuisine WHERE cuisineId = :cuisineId";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("cuisineId", cuisineId)
        .executeUpdate();
    }
  }

  public static Cuisine find(int id) {
    String sql = "SELECT * FROM cuisine WHERE cuisineId = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Cuisine.class);
    }
  }

  public List<Restaurant> getRestaurants() {
    String sql = "SELECT * FROM restaurants WHERE cuisineId = :cuisineId";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("cuisineId", cuisineId)
        .executeAndFetch(Restaurant.class);
    }
  }

  /******************************************************
    Students:
    **TODO: Create find method
    **TODO: Create method to get restaurants
  *******************************************************/

}
