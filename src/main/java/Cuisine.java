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
    try (Connection con = DB.sql2o.open()) {
      // String sql = "INSERT INTO cuisine (type) VALUES (:type)";
      // con.createQuery(sql)
      //   .addParameter("type", type)
      //   .executeUpdate();
      /******************************************************
        Students: TODO: Create sql query and execute update
      *******************************************************/
    }
  }

  //READ
  public static List<Cuisine> all() {
    String sql = "SELECT * FROM cuisine";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Cuisine.class);
    }
  }

  //UPDATE
  public void update(String newType) {
    this.type = newType;
    try(Connection con = DB.sql2o.open()) {
      /******************************************************
        Students: TODO: Create sql query and execute update
      *******************************************************/
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      /******************************************************
        Students: TODO: Create sql query and execute update
      *******************************************************/
    }
  }

  /******************************************************
    Students:
    TODO: Create find method
    TODO: Create method to get restaurants
  *******************************************************/

}
