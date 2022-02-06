import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.codec.BodyCodec;

public class WeatherApiCall extends AbstractVerticle {


//        private HttpRequest<JsonObject> request;
//
//        @Override
//        public void start() {
//
//            request = WebClient.create(vertx) // (1)
//                    .get(80, "http://api.openweathermap.org/data/2.5/weather?q=jaipur&appid=994be899da8cf911d5ab3436fa020441", ++"/") // (2)
//                    .ssl(false)  // (3)
//                    .putHeader("Accept", "application/json")  // (4)
//                    .as(BodyCodec.jsonObject()) // (5)
//                    .expect(ResponsePredicate.SC_OK);  // (6)
//
//            vertx.setPeriodic(3000, id -> fetchWeather());
//        }
//
//        private void fetchWeather() {
//            request.send(asyncResult -> {
//
//                    System.out.println(asyncResult.result().body().toString()); // (7)
//
//            });
//        }
public static void func() {
    Vertx vertx = Vertx.vertx();

    String url = "api.openweathermap.org";
    WebClient client = WebClient.create(vertx, new WebClientOptions().setDefaultPort(80).setDefaultHost(url));
    client.get("/data/2.5/weather?q=lucknow,in&units=metric&appid=994be899da8cf911d5ab3436fa020441").as(BodyCodec.string()).send(ar -> {
            if (ar.succeeded()) {
                    HttpResponse<String> response = ar.result();
                    System.out.println("Got HTTP response body");
                    System.out.println(response.body());
                    saveweather.nono(response);
                    //System.out.println(str);
                } else {
                    ar.cause().printStackTrace();
                }
            client.close();
            });


//
}
public static void main(String[] args) {
    func();
}
       
                        //Vertx vertx = Vertx.vertx();
//            HttpClient httpClient = vertx.createHttpClient();
//            httpClient.request(HttpMethod.GET, "http://api.openweathermap.org/data/2.5/weather?q=jaipur&appid=994be899da8cf911d5ab3436fa020441").onSuccess(handler -> {
//                System.out.println(handler.response().result().body().toString());
//                System.exit(0);
//            }).onFailure(handler -> {
//                System.out.println(handler.fillInStackTrace());
//                System.exit(1);
//            });

    }






