class Subscriber (username: String, userID: Int): User (username = username, userID = userID){
    fun favorites () {
        println("favorites!")
    }
}