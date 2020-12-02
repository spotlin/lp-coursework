fun main () {
    val spotlin = StreamingPlatform("Spotlin")
    println("Welcome to ${ spotlin.getPlatformName() }")

    val orion = Subscriber("Orion", 1)
    spotlin.addUser(orion)
//    spotlin.listUsers()

    val getLucky = Song("Get Lucky", 1, 1.2, 2013, "Pop")
    val digitalLove = Song("Digital Love", 1, 1.2, 2013, "Pop")
    val somethingBoutUs = Song("Something About Us", 1, 1.2, 2013, "Pop")
    spotlin.addMedia(getLucky)
    spotlin.addMedia(digitalLove)
    spotlin.addMedia(somethingBoutUs)
    spotlin.listMedia()

//    println("#1 Subscriber is ${mySubscriber.getUserName()}")
//
//
//    mySubscriber.addFavorite(getLucky)
//
//    println("${mySubscriber.getUserName()} favorites: ")
//    mySubscriber.printFavorites()

}