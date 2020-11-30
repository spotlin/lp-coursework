fun greetKotlin(name: String, greetingMsg: String = "Welcome") {
    println("$greetingMsg, $name");
}

fun laugh() {
    infix fun Int.times(str: String) = str.repeat(this)
    println(10 times "k")
}


fun main () {
//    greetKotlin("Kotlin")
//    laugh()

    // var is mutable
    // val is immutable

    var x1: Int = 1
    val x2: Int = 2
    val x3 = 3

    println("$x1 $x2 $x3")

}