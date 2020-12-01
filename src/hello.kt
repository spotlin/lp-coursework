fun main () {
    val spotlin = StreamingPlatform("Spotlin")
    println(spotlin.getPlatformName())

    val myUser = User("Orion", 1)
    println(myUser.getUserName())

    val mySubscriber = Subscriber("Andromeda", 2)
    mySubscriber.favorites()
    println(mySubscriber.getUserName())
}