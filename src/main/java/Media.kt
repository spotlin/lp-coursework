open class Media(protected var title: String, protected var mediaID: Int, protected var mediaLength: Double, protected var releaseYear: Int, var genre: Genre) {

    fun getMediaName() : String {
        return this.title
    }

    fun getMediaId() : Int {
        return this.mediaID
    }

    fun getMediaGenre() : Genre {
        return this.genre
    }

    fun getMediaLength() : Genre {
        return this.genre
    }

    // Replaces Java static keyword
    companion object {
        var counter : Int = 0
    }

    init {
        counter++
    }
}