class Song (title: String, mediaID: Int, mediaLength: Double, releaseYear: Int, var genre: Genre) : Media(title = title, mediaID = mediaID, mediaLength = mediaLength, releaseYear = releaseYear){
    fun getSongGenre() : Genre {
        return this.genre
    }
}

