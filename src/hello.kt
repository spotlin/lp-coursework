fun greetKotlin(name: String, greetingMsg: String = "Welcome") {
    println("$greetingMsg, $name");
}

fun laugh() {
    infix fun Int.times(str: String) = str.repeat(this)
    println(10 times "k")
}

fun strLength(str: String): Int {                   // 7
    return str.length
}

fun main () {
//    greetKotlin("Kotlin")
//    laugh()

    var nullableString: String? = null
    var notNullableString: String = "Oh well!"

    println(strLength(notNullableString)) // works
//    println(strLength(nullableString)) // doesn't work

}