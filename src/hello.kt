fun greetKotlin(name: String, greetingMsg: String = "Welcome") {
    println("$greetingMsg, $name");
}

fun laugh() {
    infix fun Int.times(str: String) = str.repeat(this)
    println(10 times "k")
}

fun measureStr(str: String): Unit {
    val letters: Int = strLength(str)
    println("The string '$str' has $letters letters")
}

fun strLength(str: String): Int {                   // 7
    return str.length
}

fun main () {
//    greetKotlin("Kotlin")
//    laugh()
//    measureStr(("Ok Kotlin"))
}