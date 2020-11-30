fun greetKotlin(name: String, greetingMsg: String = "Welcome") {
    println("$greetingMsg, $name");
}

fun laugh() {
    infix fun Int.times(str: String) = str.repeat(this)
    println(10 times "k")
}


fun main () {
    greetKotlin("Kotlin")
    laugh()
}