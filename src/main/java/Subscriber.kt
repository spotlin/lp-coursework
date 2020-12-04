class Subscriber (username: String, userID: Int): User (username = username, userID = userID) {

    private var favorites: MutableList<Media> = mutableListOf()

    fun addFavorite(newFavorite: Media): Unit {
        favorites.add(newFavorite)
    }

    fun getFavorites() : MutableList<Media> {
        return favorites
    }

    fun printFavorites(): Unit {
            print("$username ")
            for (media in favorites) {
                print("${media.getMediaId()} ")
            }
            println()
    }




}