import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;

public class saveweather {
    public static void nono(HttpResponse<String> res) {
        Vertx vertx = Vertx.vertx();
        JsonObject jj = new JsonObject(res.body());
        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
                .setPort(3306)
                .setHost("sql6.freemysqlhosting.net")
                .setDatabase("sql6469448")
                .setUser("sql6469448")
                .setPassword("tprZlW1uah");

        // Pool options
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);

        // Create the client pool
        MySQLPool client = MySQLPool.pool(vertx, connectOptions, poolOptions);

// A simple query
  /*      client.query("SELECT * FROM Userss").execute(ar -> {
            if (ar.succeeded()) {
                RowSet<Row> rows = ar.result();
                //System.out.println("Got " + result.size() + " rows ");
                for (Row row : rows) {
                    System.out.println(it"User " + row.getString(0));
                }
            } else {
                System.out.println("Failure: " + ar.cause().getMessage());
            }
*/      
        String city = jj.getString("name");
        String weath = jj.getString("main");
        System.out.println(city);
        System.out.println(weath);
        client.preparedQuery("INSERT INTO Saveweather (city , weather) VALUES (?,?)")
                .execute(Tuple.of(city,weath), ar -> {
                    if (ar.succeeded()) {
                        RowSet<Row> rows = ar.result();
                        System.out.println("success");
                    } else {
                        System.out.println("Failure: " + ar.cause().getMessage());
                    }

            // Now close the pool

            client.close();
        });
    }
    
}
