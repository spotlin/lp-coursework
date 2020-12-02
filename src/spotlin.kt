fun main () {
    val spotlin = StreamingPlatform("Spotlin")
    println("Welcome to ${ spotlin.getPlatformName() }")

    val mySubscriber = Subscriber("Andromeda", 2)
    println("#1 Subscriber is ${mySubscriber.getUserName()}")

    println("${mySubscriber.getUserName()} favorites: ")
    mySubscriber.printFavorites()

    println("\nAfter adding favorites... ")

    println("${mySubscriber.getUserName()} favorites: ")
    mySubscriber.addFavorites("Horse With No Name")
    mySubscriber.addFavorites("From Now On")
    mySubscriber.addFavorites("Strawberry Fields Forever")

    mySubscriber.printFavorites()

}