package example1

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler

class App2 : AbstractVerticle() {

  override fun start() {

    println("*** starting kotlin verticle ")

    val router = Router.router(vertx)

    router.route().handler(BodyHandler.create())

    router.get("/").handler { event -> event.response().end(personJsonAdapter.toJson(person)) }

    vertx.createHttpServer().requestHandler(router).listen(8080)
  }

  companion object {

    @JvmStatic
    fun main(args: Array<String>) {

      val begin = System.currentTimeMillis()
      val vertx = Vertx.vertx()
      vertx.deployVerticle(App2()) { done ->
        val end = System.currentTimeMillis()
        if (done.succeeded()) {
          println("Application started in " + (end - begin) + " ms")
        } else {
          done.cause().printStackTrace()
        }
      }
    }
  }

}

