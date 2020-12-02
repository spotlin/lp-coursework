fun main () {
    val spotlin = StreamingPlatform("Spotlin")
    println("Welcome to ${ spotlin.getPlatformName() }")

    val orion = Subscriber("Orion", 1)
    val andromeda = Subscriber("Andromeda", 2)
    val pleiades = Subscriber("Pleiades", 3)

    spotlin.addUser(orion)
    spotlin.addUser(andromeda)
    spotlin.addUser(pleiades)

    spotlin.listUsers()

//    println("#1 Subscriber is ${mySubscriber.getUserName()}")
//    val getLucky = Song("Get Lucky", 1, 1.2, 2013, "Pop")
//
//    mySubscriber.addFavorite(getLucky)
//
//    println("${mySubscriber.getUserName()} favorites: ")
//    mySubscriber.printFavorites()

}