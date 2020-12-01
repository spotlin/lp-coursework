open class User (var username: String, var userID: Int){
    fun getUserName(): String {
        return this.username;
    }

    fun setUserName(newUsername: String): Unit {
        this.username = newUsername
    }
}

