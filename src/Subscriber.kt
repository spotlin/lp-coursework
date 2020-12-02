class Subscriber (username: String, userID: Int): User (username = username, userID = userID) {

    private var favorites: MutableList<Media> = mutableListOf()

    fun addFavorite(newFavorite: Media): Unit {
        favorites.add(newFavorite)
    }

    // TODO: add removeFavorite method

    fun printFavorites(): Unit {
            for (media in favorites) {
                println(media.getMediaName())
            }
        }


}