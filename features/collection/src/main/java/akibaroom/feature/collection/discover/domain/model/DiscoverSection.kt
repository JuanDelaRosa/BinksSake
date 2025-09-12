package akibaroom.feature.collection.discover.domain.model

import akibaroom.core.domain.model.Figure

data class DiscoverSection(
    val title: String,
    val list: List<Figure>
)
