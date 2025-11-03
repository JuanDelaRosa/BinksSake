package akibaroom.feature.collection

import akibaroom.core.domain.model.Figure
import akibaroom.core.domain.model.FigureCategory
import akibaroom.feature.collection.detail.domain.model.Character
import akibaroom.feature.collection.detail.domain.model.FigureDetails
import akibaroom.feature.collection.discover.domain.model.DiscoverSection

object FiguresMoke {

    val figures = listOf(
        Figure(
            id = "1",
            name = "Figure 1",
            manufacturer = "Good Smile Company",
            series = "Test Series",
            character = "Test Character",
            category = FigureCategory.SCALE_FIGURE,
            images = listOf("https://cdn.shopify.com/s/files/1/1039/4986/products/FR40070__1.jpg"),
            uploadedBy = "system"
        ),
        Figure(
            id = "2",
            name = "Figure 2",
            manufacturer = "Good Smile Company",
            series = "Test Series",
            character = "Test Character",
            category = FigureCategory.NENDOROID,
            images = listOf("https://m.media-amazon.com/images/I/51tp9YbmVrL._UF894,1000_QL80_.jpg"),
            uploadedBy = "system"
        ),
        Figure(
            id = "3",
            name = "Figure 3",
            manufacturer = "Good Smile Company",
            series = "Test Series",
            character = "Test Character",
            category = FigureCategory.FIGMA,
            images = listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpG4lDQMHjtBvUZSX-wQdQY7jtSJDMHzNm1g&s"),
            uploadedBy = "system"
        ),
        Figure(
            id = "4",
            name = "Figure 4",
            manufacturer = "Good Smile Company",
            series = "Test Series",
            character = "Test Character",
            category = FigureCategory.PRIZE_FIGURE,
            images = listOf("https://i.pinimg.com/736x/ca/c9/b0/cac9b00094f0a5ed8fd4b20af6c0d905.jpg"),
            uploadedBy = "system"
        )
    )

    val figureDetails = FigureDetails(
        uuid = "1",
        name = "Figure 1",
        images = listOf("https://i.pinimg.com/736x/ca/c9/b0/cac9b00094f0a5ed8fd4b20af6c0d905.jpg"),
        description = "Description 1",
        version = "red",
        character = Character(
            name = "Rick",
            imageUrl = "",
            uuid = "1"
        )
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
