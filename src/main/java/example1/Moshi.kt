package example1

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

@JsonClass(generateAdapter = true)
class Person(val id: Long, val name: String, val age: Int = -1)

data class FooHolder(
        val someMetadata: Int,
        val payload: List<Foo>
)

sealed class Foo

data class Bar(val bar: Int, val x: String? = null) : Foo()

data class Baz(val baz: Int) : Foo()

val moshi = Moshi.Builder()
//            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()

val personJsonAdapter =  moshi.adapter<Person>(Person::class.java)

val person = Person(1, "rodolfo", 68)

fun main() {

  println(personJsonAdapter.toJson(person))

}