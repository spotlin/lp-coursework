open class Media(protected var title: String, protected var mediaID: Int, protected var mediaLength: Double, protected var releaseYear: Int ){

    fun getMediaName() : String {
        return this.title
    }

    fun getMediaId() : Int {
        return this.mediaID
    }

    // Replaces Java static keyword
    companion object {
        var counter : Int = 0
    }

    init {
        counter++
    }
}