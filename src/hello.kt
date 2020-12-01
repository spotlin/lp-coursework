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

open class Animal {
    open fun greet() {
        println("*pet sounds*")
    }
}

class Dog : Animal() {
    override fun greet() {
        println("wow wow!")
    }
}

class Cat : Animal() {
    override fun greet() {
        println("meowww")
    }
}

// TODO: what does the fox say?

fun main () {
//    greetKotlin("Kotlin")
//    laugh()
//    measureStr(("Ok Kotlin")

//    val user1 = User(1, "Daniel", "dan@ufes.com")
//    val email = user1.getUserEmail()

    val doggo: Animal = Dog()
    doggo.greet()

    val kitty: Animal = Cat()
    kitty.greet()

}