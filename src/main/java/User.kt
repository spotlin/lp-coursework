open class User (protected var username: String, protected var userID: Int){
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

