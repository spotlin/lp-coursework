fun main () {
    val spotlin = StreamingPlatform("Spotlin")
    println("Welcome to ${ spotlin.getPlatformName() }")

    spotlin.loadUsers("src/main/resources/usuarios.csv")


    spotlin.loadFavorites("src/main/resources/favoritos.csv")
//    spotlin.listUsers()

//    val user1 = Subscriber("Orion", 1)
//    spotlin.addUser(user1)
//    spotlin.listUsers()

//    val pop = Genre("Pop", "PO")

//    val media1 = Song("Get Lucky", 1, 1.2, 2013, pop)
//    spotlin.addMedia(media1)
//    spotlin.listMedia()



//    println("#1 Subscriber is ${mySubscriber.getUserName()}")
//
//
//    mySubscriber.addFavorite(getLucky)
//
//    println("${mySubscriber.getUserName()} favorites: ")
//    mySubscriber.printFavorites()

}