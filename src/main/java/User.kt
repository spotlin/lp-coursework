open class User (protected var username: String, protected var userID: Int) : Comparable <User>
{
    override fun compareTo(other: User): Int {
        if (this.userID > other.userID) return 1
        if (this.userID < other.userID) return -1
        return 0
    }

    fun getUserName(): String {
        return this.username
    }

    fun setUserName(newUsername: String): Unit {
        this.username = newUsername
    }

    fun getUserId() : Int {
        return this.userID
    }
}

