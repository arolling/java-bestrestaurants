import org.sql2o.*;
import java.util.List;

public class Region {
  private int id;
  private String area;

  public Region (String area) {
    this.area = area;
  }

  public static List<Region> all() {
    String sql = "SELECT * FROM regions";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Region.class);
    }
  }

  public int getId() {
    return id;
  }

  public String getRegionArea() {
    return area;
  }

}
