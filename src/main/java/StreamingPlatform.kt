import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import kotlin.reflect.KClass

class StreamingPlatform (private var platformName: String) {
    fun getPlatformName(): String {
        return this.platformName
    }

    private var users: MutableList<User> = mutableListOf()
    private var media: MutableList<Media> = mutableListOf()
    private var genres: MutableList<Genre> = mutableListOf()

    /* Adding to platform */

    // Add an user
    fun addUser(newUser: User): Unit {
        users.add(newUser)
    }

    // Add a genre
    fun addGenre(newGenre: Genre): Unit {
        genres.add(newGenre)
    }

    // Add a media
    fun addMedia(newMedia: Media): Unit {
        media.add(newMedia)
    }



    /* Getting a reference to the object */

    // Get user by ID
    fun getUserByID(userID : Int) : User? {
        return users.find {it.getUserId() == userID}
    }

    // Get genre by acronym
    fun getGenreByAcronym(acronym : String) : Genre? {
        return genres.find {it.getGenreAcronyms() == acronym}
    }

    // Get media by ID
    fun getMediaByID(mediaID: Int) : Media? {
        return media.find {it.getMediaId() == mediaID}
    }

    /* Listing data structures */

    // List all users
    fun listUsers() : Unit {
        for (user in users) {
            // 'is' replaces Java's instanceof
            if (user is Subscriber) {
                println("Subscriber: ${user.getUserName()}")
            }

            else if (user is Artist){
                println("Artist: ${user.getUserName()}")
            }

            else if (user is Podcaster){
                println("Podcaster: ${user.getUserName()}")
            }

            else {
                println("UNDEFINED: ${user.getUserName()}")
            }
        }
    }

    // List all genres
    fun listGenres(): Unit {
        for (genre in genres) {
            println(genre.getGenre())
        }
    }


    // List all media
    fun listMedia(): Unit {
        for (element in media) {
            println(element.getMediaName())
        }
    }

    /* Loading files */

    // Load users from CSV file
    fun loadUsers(usersFile: String): Unit {
        var firstLine: Boolean = true

        csvReader().open(usersFile) {
            readAllAsSequence().forEach { row ->
                if (firstLine) {
                    firstLine = false
                } else {
                    for (elem in row) {
                        var splitRow = (elem.split(';'))
                        var userID = splitRow.get(0)
                        var userType = splitRow.get(1)
                        var username = splitRow.get(2)

                        // TODO: replace cascade 'if' with 'when'
                        if (userType == "U") {
                            // Instantiate a new user
                            var newUser = Subscriber(username, userID.toInt())

                            // Add to platform
                            addUser(newUser)
                        } else if (userType == "P") {
                            var newUser = Podcaster(username, userID.toInt())
//
                            // Add to platform
                            addUser(newUser)
                        } else if (userType == "A") {
                            // Instantiate a new user
                            var newUser = Artist(username, userID.toInt())

                            // Add user to platform
                            addUser(newUser)
                        }

                    }

                }
            }
        }
    }

    fun loadGenres(mediaFile: String): Unit {
        var firstLine: Boolean = true

        csvReader {delimiter = ';'}.open(mediaFile) {
            readAllAsSequence().forEach { row ->
                if (firstLine) {
                    firstLine = false
                } else {
                    var newGenre = Genre(row[1], row[0])
                    addGenre(newGenre)
                }
            }

        }
    }



    fun loadMedia(mediaFile: String): Unit {
        var firstLine: Boolean = true

        csvReader {delimiter = ';'}.open(mediaFile) {
            readAllAsSequence().forEach { row ->
                if (firstLine) {
                    firstLine = false
                } else {
                    var mediaCode = row[0]
                    var mediaName = row[1]
                    var mediaType = row[2]
                    var mediaProducers = row[3]
                    var mediaLength = row[4]
                    var mediaGenre = row[5]
                    var mediaSeason = row[6]
                    var mediaAlbum = row[7]
                    var mediaAlbumCode = row[8]
                    var mediaReleaseYear = row[9]

                    // Turn mediaLength into double
                    mediaLength = mediaLength.replace(",", ".")

                    // Get reference to Genre object
//                    println(mediaGenre.substringBefore(","))
                    val genre = getGenreByAcronym(mediaGenre.substringBefore(","))
//                    println("${genre?.getGenreAcronyms()}: ${genre?.getGenre()}")
                    // Check media type to add media to appropriate class
                    when (mediaType) {
                        // Add to Song class
                        "M" -> {
                            // TODO: associate proper Genre to each media
                            var newSong = genre?.let { Song(mediaName, mediaCode.toInt(), mediaLength.toDouble(), mediaReleaseYear.toInt(), it) }
                            if (newSong != null) {
                                addMedia(newSong)
                            }
                        }

                        // Add to Podcast class
                        "P" -> {
                            var newPodcast = Podcast(mediaName, mediaCode.toInt(), mediaLength.toDouble(), mediaReleaseYear.toInt(), Genre("Comedy", "CO"), mediaSeason.toInt())
                            addMedia(newPodcast)
                        }

                        else -> {
                            // Throw error

                        }
                    }




                }
            }
        }
    }

    // Load favorites from CSV file
    fun loadFavorites(favoritesFile: String): Unit {
        var firstLine: Boolean = true

        csvReader {delimiter = ';'}.open(favoritesFile) {
            readAllAsSequence().forEach { row ->
                if (firstLine) {
                    firstLine = false
                } else {

                    var userID = row.toString().substringBefore(',').replace("[", "")
                    var userFavorites = row.toString().substringAfter(',').replace("]", "")

                    // If Subscriber : User has favorites...
                    if (userFavorites != " " && userFavorites != "") {
                        // Get Subscriber by ID from StreamingPlatform
                        var user = getUserByID(userID.toInt())

                        // Create array from userFavorites CSV row
                        var userFavoritesArray = userFavorites.split(",").toTypedArray()

                        // Traverse this array and add each media separately
                        for (favoriteMedia in userFavoritesArray) {

                            // Remove remaining whitespace from CSV
                            var favMediaNoWhiteSpace = favoriteMedia.filter { !it.isWhitespace() }

                            // Get reference to favorite media by ID
                            var media = getMediaByID(favMediaNoWhiteSpace.toInt())

                            // Associate favorite media with user
                            // Check if media exists in catalogue
                            if (media != null) {
                                (user as Subscriber).addFavorite(media)
                            }
                        }

//                        (user as Subscriber).printFavorites()
                    }

                    else {
                        // This user has no favorites
                    }
                }
            }
        }
    }

    /* Generating reports */

    // Generate list of favorite media by user report
    fun generateFavoritesReport() : Unit {

        // Get userID

        // Get media type

        // Get media ID

        // Get media genre

        // Get media length

        // Write to CSV


    }
}