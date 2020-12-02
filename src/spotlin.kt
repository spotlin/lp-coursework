fun main () {
    val spotlin = StreamingPlatform("Spotlin")
    println("Welcome to ${ spotlin.getPlatformName() }")

    val mySubscriber = Subscriber("Andromeda", 2)
    println("#1 Subscriber is ${mySubscriber.getUserName()}")
    mySubscriber.addFavorite("Strawberry Fields Forever")

    println("${mySubscriber.getUserName()} favorites: ")
    mySubscriber.printFavorites()


    val newMedia = Media("Get Lucky", 1, 1.2, 2013)

}