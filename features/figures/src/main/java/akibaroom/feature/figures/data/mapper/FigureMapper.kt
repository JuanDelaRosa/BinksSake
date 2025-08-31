package akibaroom.feature.figures.data.mapper

import akibaroom.feature.figures.api.CharacterResponse
import akibaroom.core.database.FigureEntity
import akibaroom.feature.figures.ui.FigureUi

internal fun CharacterResponse.toUi(): FigureUi =
    FigureUi(id = id, name = name, image = image)

internal fun FigureEntity.toUi(): FigureUi =
    FigureUi(id = id, name = name, image = imageUrl)
