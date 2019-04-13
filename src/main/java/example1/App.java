package example1;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class App extends AbstractVerticle {

  public static void main(String[] args) {
    long begin = System.currentTimeMillis();
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new App(), done -> {
      long end = System.currentTimeMillis();
      if (done.succeeded()) {
        System.out.println("Application started in " + (end - begin) + " ms");
      } else {
        done.cause().printStackTrace();
      }
    });
  }

  @Override
  public void start() {

    System.out.println("*** starting");

    Router router = Router.router(vertx);

    router.route().handler(BodyHandler.create());

    router.get("/").handler(event -> {
      event.response().end("***** ok");
    });

    vertx.createHttpServer().requestHandler(router).listen(8080);
  }

}
