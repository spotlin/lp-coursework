import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import kotlin.reflect.KClass

class StreamingPlatform (private var platformName: String) {
    fun getPlatformName(): String {
        return this.platformName
    }

    private var users: MutableList<User> = mutableListOf()
    private var media: MutableList<Media> = mutableListOf()

    // Add an user
    fun addUser(newUser: User): Unit {
        users.add(newUser)
    }

    // Get user by ID
    fun getUserByID(userID : Int) : User? {
        return users.find {it.getUserId() == userID}
    }

    // Get media by ID
    fun getMediaByID(mediaID: Int) : Media? {
        return media.find {it.getMediaId() == mediaID}
    }

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

    // Add media
    fun addMedia(newMedia: Media): Unit {
        media.add(newMedia)
    }

    // List all media
    fun listMedia(): Unit {
        for (element in media) {
            println(element.getMediaName())
        }
    }

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

                            // Add to platform
                            addUser(newUser)

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
                            // TODO: load media CSV file

                            // TODO: Get media by ID

                            // Remove remaining whitespace from CSV
                            var favMediaNoWhiteSpace = favoriteMedia.filter { !it.isWhitespace() }

                            // Create new instance of media
                            var newMedia = Media("Get Lucky", favMediaNoWhiteSpace.toInt(), 3.14, 2019)

                            // Associate favorite media with user
                            (user as Subscriber).addFavorite(newMedia)
                        }

                        (user as Subscriber).printFavorites()

                        // Add to MutableList

//                        println(userFavorites)
                    }

                    else {
                        // This user has no favorites
                    }
//                    for (elem in row) {
//                            var splitRow = (elem.split(','))

//                            var userCode = splitRow.get(0)
//                            var userFavorites = splitRow.get(1)

//                        println("$elem")
//
//                            // TODO: replace cascade 'if' with 'when'
//                            if (userType == "U") {
//                                // Instantiate a new user
//                                var newUser = Subscriber(username, userID.toInt())
//
//                                // Add to platform
//                                addUser(newUser)
//                            } else if (userType == "P") {
//                                var newUser = Podcaster(username, userID.toInt())
////
//                                // Add to platform
//                                addUser(newUser)
//                            } else if (userType == "A") {
//                                // Instantiate a new user
//                                var newUser = Artist(username, userID.toInt())
//
//                                // Add to platform
//                                addUser(newUser)
//
//                            }

//                    }

                }
            }
        }
    }

}