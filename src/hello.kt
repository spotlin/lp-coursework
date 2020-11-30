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

class User (var id: Int, var email: String)

fun main () {
//    greetKotlin("Kotlin")
//    laugh()
//    measureStr(("Ok Kotlin")

    val user1 = User(1, "dan@dan.com")
    println("${user1.id}: ${user1.email}")

}