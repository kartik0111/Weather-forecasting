import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;
public class updateweather {
    public static void func() {
        Vertx vertx = Vertx.vertx();

        String url = "api.openweathermap.org";
        WebClient client = WebClient.create(vertx, new WebClientOptions().setDefaultPort(80).setDefaultHost(url));
        client.get("/data/2.5/weather?q=lucknow,in&units=metric&appid=994be899da8cf911d5ab3436fa020441").as(BodyCodec.string()).send(ar -> {
            if (ar.succeeded()) {
                HttpResponse<String> response = ar.result();
                System.out.println("Got HTTP response body");
                System.out.println(response.body());
                nono(response);
                //System.out.println(str);
            } else {
                ar.cause().printStackTrace();
            }
            client.close();
        });


        //
    }
    public static void nono(HttpResponse<String> res) {
        Vertx vertx = Vertx.vertx();
        JsonObject jj = new JsonObject(res.body());
        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
                .setPort(3306)
                .setHost("sql6.freemysqlhosting.net")
                .setDatabase("sql6469448")
                .setUser("sql6469448")
                .setPassword("tprZlW1uah");
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);
        MySQLPool client = MySQLPool.pool(vertx, connectOptions, poolOptions);
        String city = jj.getString("name");
        String weath = jj.getString("main");
        System.out.println(city);
        System.out.println(weath);
        client.preparedQuery("UPDATE Saveweather SET weather = ? WHERE city = ?")
                .execute(Tuple.of(weath,city), ar -> {
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
    public static void main(String[] args) {
        func();
    }
}