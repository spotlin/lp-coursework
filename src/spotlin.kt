fun main () {
    val spotlin = StreamingPlatform("Spotlin")
    println("Welcome to ${ spotlin.getPlatformName() }")

    val mySubscriber = Subscriber("Andromeda", 2)
    println("#1 Subscriber is ${mySubscriber.getUserName()}")
    val getLucky = Song("Get Lucky", 1, 1.2, 2013, "Pop")
    val digitalL = Song("Digital Love", 1, 1.2, 2013, "Pop")

    mySubscriber.addFavorite(getLucky)
    mySubscriber.addFavorite(digitalL)

    println("${mySubscriber.getUserName()} favorites: ")
    mySubscriber.printFavorites()



}