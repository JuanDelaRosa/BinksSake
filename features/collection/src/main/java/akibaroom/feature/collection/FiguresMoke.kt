package akibaroom.feature.collection

import akibaroom.core.domain.model.Figure
import akibaroom.feature.collection.discover.domain.model.DiscoverSection

object FiguresMoke {

    val figures = listOf(
        Figure(id = 1, name = "Figure 1", image = "https://cdn.shopify.com/s/files/1/1039/4986/products/FR40070__1.jpg"),
        Figure(id = 2, name = "Figure 2", image = "https://m.media-amazon.com/images/I/51tp9YbmVrL._UF894,1000_QL80_.jpg"),
        Figure(id = 1, name = "Figure 1", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpG4lDQMHjtBvUZSX-wQdQY7jtSJDMHzNm1g&s"),
        Figure(id = 2, name = "Figure 2", image = "https://i.pinimg.com/736x/ca/c9/b0/cac9b00094f0a5ed8fd4b20af6c0d905.jpg")
    )

    val sections = listOf(
        DiscoverSection(
            title = "Recomendado para ti",
            list = figures
        ),
        DiscoverSection(
            title = "Nuevos lanzamientos",
            list = figures
        ),
        DiscoverSection(
            title = "Tendencias",
            list = figures
        ),
        DiscoverSection(
            title = "Super Sonico",
            list = figures
        ),
        DiscoverSection(
            title = "Section 5",
            list = figures
        )
    )
}
