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

class User (var id: Int, var name: String, var email: String) {
    fun getUserId(): Int {
        return this.id
    }

    fun getUserEmail(): String {
        return this.email
    }

    fun setUserEmail(newEmail: String): Unit {
        this.email = newEmail
    }
}

fun main () {
//    greetKotlin("Kotlin")
//    laugh()
//    measureStr(("Ok Kotlin")

    val user1 = User(1, "Daniel", "dan@ufes.com")
    val email = user1.getUserEmail()
    println("You're trying to tire me: $email")

}