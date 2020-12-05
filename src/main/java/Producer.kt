open class Producer (username: String, userID: Int): User (username = username, userID = userID) {
    private var media: MutableList<Int> = mutableListOf()

    public fun assignMedia(newMedia : Int) {
        media.add(newMedia)
    }
}