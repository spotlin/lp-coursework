fun main () {
    val spotlin = StreamingPlatform("Spotlin")
    println("Welcome to ${ spotlin.getPlatformName() }")

    spotlin.loadUsers("src/main/resources/usuarios.csv")
    spotlin.loadGenres("src/main/resources/generos.csv")
    spotlin.loadMedia("src/main/resources/midias.csv")
    spotlin.loadFavorites("src/main/resources/favoritos.csv")


    spotlin.generateFavoritesReport()
    spotlin.generateMediaByProducerReport()
}