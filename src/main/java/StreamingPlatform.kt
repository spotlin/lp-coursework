import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

class StreamingPlatform (private var platformName: String) {
    fun getPlatformName(): String {
        return this.platformName
    }

    private var users: MutableList<User> = mutableListOf()
    private var media: MutableList<Media> = mutableListOf()

    fun addUser(newUser: User): Unit {
        users.add(newUser)
    }

    fun listUsers() : Unit {
        for (user in users) {
            println(user.getUserName())
        }
    }

    fun addMedia(newMedia: Media): Unit {
        media.add(newMedia)
    }

    fun listMedia() : Unit {
        for (element in media) {
            println(element.getMediaName())
        }
    }
}