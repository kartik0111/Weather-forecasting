import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;

import java.util.Scanner;

public class searchweather {
    public static void nono() {
        Scanner myObj = new Scanner(System.in);
        Vertx vertx = Vertx.vertx();
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
                    System.out.println("User " + row.getString(0));
                }
            } else {
                System.out.println("Failure: " + ar.cause().getMessage());
            }
*/
        client
                .preparedQuery("SELECT * FROM Saveweather WHERE city=?")
                .execute(Tuple.of("Ojas"), ar -> {
                    if (ar.succeeded()) {
                        RowSet<Row> rows = ar.result();
                        for (Row row : rows) {
                            System.out.println(row.getString(1));
                        }
                    } else {
                        System.out.println("Failure: " + ar.cause().getMessage());
                    }
                });
    }

    public static void main(String[] args) {
        nono();
    }

}
