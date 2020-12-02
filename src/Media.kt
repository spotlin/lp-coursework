open class Media(protected var title: String, protected var mediaID: Int, protected var mediaLength: Double, protected var releaseYear: Int ){
    private var counter : Int = 0

    init {
        println("Hello, media")
    }
}