class StreamingPlatform (private var platformName: String) {
    fun getPlatformName(): String {
        return this.platformName
    }

    private var users: MutableList<User> = mutableListOf()

    fun addUser(newUser: User): Unit {
        users.add(newUser)
    }

    fun listUsers() : Unit {
        for (user in users) {
            println(user.getUserName())
        }
    }
}