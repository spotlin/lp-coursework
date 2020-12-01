class User (var username: String, var usercode: Int){
    fun getUserName(): String {
        return this.username;
    }

    fun setUserName(newUsername: String): Unit {
        this.username = newUsername
    }
}