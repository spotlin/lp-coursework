class Subscriber (username: String, userID: Int): User (username = username, userID = userID) {

    private var favorites: MutableList<String> = mutableListOf()

    fun printFavorites(): Unit {
            for (media in favorites) {
                println(media)
            }
        }

    fun addFavorites(newFavorite: String): Unit {
        favorites.add(newFavorite)
    }
}