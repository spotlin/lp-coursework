import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

class StreamingPlatform (private var platformName: String) {
    fun getPlatformName(): String {
        return this.platformName
    }

    private var users: MutableList<User> = mutableListOf()
    private var media: MutableList<Media> = mutableListOf()
    private var genres: MutableList<Genre> = mutableListOf()

    /* General functions */

    private fun checkMediaType(media: Media) : String {
        return when (media) {
            is Song -> {
                "Música"
            }

            is Podcast -> {
                "Podcast"
            }

            else -> {
                // Throw error
                ""
            }
        }
    }

    private fun assignMediaToProducers(producers: Array<String>, mediaID: Int) {
        for (producer in producers) {
            var user = getUserByID(producer.toInt())
            (user as Producer).assignMedia(mediaID)

            println("Assigning media ${getMediaByID(mediaID)?.getMediaName()} to ${user.getUserName()}")
        }
    }

    /* Adding to platform */

    // Add an user
    private fun addUser(newUser: User) {
        users.add(newUser)
    }

    // Add a genre
    private fun addGenre(newGenre: Genre) {
        genres.add(newGenre)
    }

    // Add a media
    private fun addMedia(newMedia: Media) {
        media.add(newMedia)
    }

    /* Getting a reference to the object */

    // Get user by ID
    private fun getUserByID(userID : Int) : User? {
        return users.find {it.getUserId() == userID}
    }

    // Get genre by acronym
    private fun getGenreByAcronym(acronym : String) : Genre? {
        return genres.find {it.getGenreAcronyms() == acronym}
    }

    // Get media by ID
    private fun getMediaByID(mediaID: Int) : Media? {
        return media.find {it.getMediaId() == mediaID}
    }

    /* Listing data structures */

    // List all users
    fun listUsers(){
        for (user in users) {
            // 'is' replaces Java's instanceof
            when (user) {
                is Subscriber -> {
                    println("Subscriber: ${user.getUserName()}")
                }

                is Artist -> {
                    println("Artist: ${user.getUserName()}")
                }

                is Podcaster -> {
                    println("Podcaster: ${user.getUserName()}")
                }

                else -> {
                    println("UNDEFINED: ${user.getUserName()}")
                }
            }
        }
    }

    // List all genres
    fun listGenres() {
        for (genre in genres) {
            println(genre.getGenre())
        }
    }


    // List all media
    fun listMedia() {
        for (element in media) {
            println(element.getMediaName())
        }
    }

    /* Loading files */

    // Load users from CSV file
    fun loadUsers(usersFile: String) {
        var firstLine = true

        csvReader().open(usersFile) {
            readAllAsSequence().forEach { row ->
                if (firstLine) {
                    firstLine = false
                } else {
                    for (elem in row) {
                        val splitRow = (elem.split(';'))
                        val userID = splitRow.get(0)
                        val userType = splitRow.get(1)
                        val username = splitRow.get(2)

                        // TODO: replace cascade 'if' with 'when'
                        if (userType == "U") {
                            // Instantiate a new user
                            val newUser = Subscriber(username, userID.toInt())

                            // Add to platform
                            addUser(newUser)
                        } else if (userType == "P") {
                            val newUser = Podcaster(username, userID.toInt())
//
                            // Add to platform
                            addUser(newUser)
                        } else if (userType == "A") {
                            // Instantiate a new user
                            val newUser = Artist(username, userID.toInt())

                            // Add user to platform
                            addUser(newUser)
                        }

                    }

                }
            }
        }
    }

    fun loadGenres(mediaFile: String) {
        var firstLine = true

        csvReader {delimiter = ';'}.open(mediaFile) {
            readAllAsSequence().forEach { row ->
                if (firstLine) {
                    firstLine = false
                } else {
                    val newGenre = Genre(row[1], row[0])
                    addGenre(newGenre)
                }
            }

        }
    }



    fun loadMedia(mediaFile: String) {
        var firstLine = true

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
                    val genre = getGenreByAcronym(mediaGenre.substringBefore(","))
                    when (mediaType) {
                        // Add to Song class
                        "M" -> {
                            var newSong = genre?.let { Song(mediaName, mediaCode.toInt(), mediaLength.toDouble(), mediaReleaseYear.toInt(), it) }
                            // Add null-check
                            if (newSong != null) {
                                addMedia(newSong)
                            }
                        }

                        // Add to Podcast class
                        "P" -> {
                            var newPodcast = genre?.let { Podcast(mediaName, mediaCode.toInt(), mediaLength.toDouble(), mediaReleaseYear.toInt(), it, mediaSeason.toInt()) }
                            // Add null-check
                            if (newPodcast != null) {
                                addMedia(newPodcast)
                            }
                        }

                        else -> {
                            // Throw error

                        }
                    }

                    // Assign media to producers
                    mediaProducers = mediaProducers.filter { !it.isWhitespace() }
                    val producersArray = mediaProducers.split(",").toTypedArray()
                    assignMediaToProducers(producersArray, mediaCode.toInt())

                }
            }
        }
    }

    // Load favorites from CSV file
    fun loadFavorites(favoritesFile: String): Unit {
        var firstLine = true

        csvReader {delimiter = ';'}.open(favoritesFile) {
            readAllAsSequence().forEach { row ->
                if (firstLine) {
                    firstLine = false
                } else {

                    val userID = row.toString().substringBefore(',').replace("[", "")
                    val userFavorites = row.toString().substringAfter(',').replace("]", "")

                    // If Subscriber : User has favorites...
                    if (userFavorites != " " && userFavorites != "") {
                        // Get Subscriber by ID from StreamingPlatform
                        val user = getUserByID(userID.toInt())

                        // Create array from userFavorites CSV row
                        val userFavoritesArray = userFavorites.split(",").toTypedArray()

                        // Traverse this array and add each media separately
                        for (favoriteMedia in userFavoritesArray) {

                            // Remove remaining whitespace from CSV
                            val favMediaNoWhiteSpace = favoriteMedia.filter { !it.isWhitespace() }

                            // Get reference to favorite media by ID
                            val media = getMediaByID(favMediaNoWhiteSpace.toInt())

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
        // Create CSV file in which the report will be written
        csvWriter {delimiter = ';'}.open("3-favoritos.csv") {
            // Create CSV header
            val newRow = listOf("CódigoAssinante", "Tipo Mídia", "Código Mídia", "Gênero", "Duração")
            writeRow(newRow)

            // Sort users by user ID (ascending)
            val sortedUsers = users.sorted()

            // Traverse through each user
            for (user in sortedUsers) {
                // Get reference to user by userID
                val user = getUserByID(user.getUserId())

                // Check if User is Subscriber
                if (user is Subscriber) {
                    // Get user ID *
                    val userID = user.getUserId()

                    // Get user favorites
                    val userFavorites = user.getFavorites()

                    // Sort user favorites: 1. by type; 2. by ID
                    val userFavoritesSorted = userFavorites.sortedWith(
                            compareBy(
                                    { it is Podcast },
                                    { it.getMediaId() }
                            )
                    )

                    // Traverse through user favorites
                    for (media in userFavoritesSorted) {
                        // Check if media is Song or Podcast *
                        val mediaType = checkMediaType(media)

                        // Add remaining data
                        val mediaID = media.getMediaId()
                        val mediaGenre = media.getMediaGenre().getGenre()
                        val mediaLength = media.getLength()

                        // Create row with data
                        val newRow = listOf("$userID", "$mediaType", "$mediaID", "$mediaGenre", "$mediaLength")
                        writeRow(newRow)
                    }

                }
            }
        }
    }

    // Generate list of media by artist
    fun generateMediaByProducerReport() : Unit {
        // Create CSV file in which the report will be written
        csvWriter {delimiter = ';'}.open("2-produtores.csv") {
            // Create CSV header
            val newRow = listOf("NomeProdutor", "NomeMídia")
            writeRow(newRow)

            // Sort users by user ID (ascending)
            val sortedUsers = users.sorted()

            // Traverse through each user
            for (user in sortedUsers) {
                // Get reference to user by userID
                val user = getUserByID(user.getUserId())

                // Check if User is Subscriber
                if (user is Subscriber) {
                    // Get user ID *
                    val userID = user.getUserId()

                    // Get user favorites
                    val userFavorites = user.getFavorites()

                    // Sort user favorites: 1. by type; 2. by ID
                    val userFavoritesSorted = userFavorites.sortedWith(
                            compareBy(
                                    { it is Podcast },
                                    { it.getMediaId() }
                            )
                    )

                    // Traverse through user favorites
                    for (media in userFavoritesSorted) {
                        // Check if media is Song or Podcast *
                        val mediaType = checkMediaType(media)

                        // Add remaining data
                        val mediaID = media.getMediaId()
                        val mediaGenre = media.getMediaGenre().getGenre()
                        val mediaLength = media.getLength()

                        // Create row with data
                        val newRow = listOf("$userID", "$mediaType", "$mediaID", "$mediaGenre", "$mediaLength")
                        writeRow(newRow)
                    }

                }
            }
        }
    }

}