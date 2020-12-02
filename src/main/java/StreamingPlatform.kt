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
            println(user.getUserName())
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
                //Do something
                if (firstLine) {
                    println("First line: $row")
                    firstLine = false
                }

                else {
                    println(row) //[a, b, c]

                }
            }
        }
    }

}