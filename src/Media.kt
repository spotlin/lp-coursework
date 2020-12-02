open class Media(protected var title: String, protected var mediaID: Int, protected var mediaLength: Double, protected var releaseYear: Int ){
    // Replaces Java static keyword
    companion object {
        var counter : Int = 0
    }

    init {
        counter++
    }
}