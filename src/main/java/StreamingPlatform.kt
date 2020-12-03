import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

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
        var firstLine : Boolean = true

        csvReader().open(usersFile) {
            readAllAsSequence().forEach { row ->
                if (firstLine) {
                    firstLine = false
                }

                else {
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
                        }

                        else if (userType == "P") {
//                            // Instantiate a new user
//                            var newUser = User(username, userID.toInt())
//
//                            // Add to platform
//                            addUser(newUser)

                            println("Do g yet. Podcaster.")
                        }

                        else if (userType == "A") {
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

}