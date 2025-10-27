package akibaroom.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import akibaroom.core.database.converters.Converters

@Entity(tableName = "figures")
@TypeConverters(Converters::class)
data class FigureEntity(
    @PrimaryKey val id: String,
    val name: String,
    val manufacturer: String,
    val series: String,
    val character: String,
    val category: String,
    val scale: String?,
    val releaseDate: String?,
    val msrp: Double?,
    val barcode: String?,
    val jan: String?,
    val images: String,
    val description: String,
    val isNSFW: Boolean,
    val uploadedBy: String,
    val uploadedAt: Long,
    val tags: String,
    val height: Double?,
    val width: Double?,
    val depth: Double?,
    val dimensionUnit: String?,
    val weight: Double?,
    val material: String?,
    val sculptor: String?,
    val illustrator: String?,
    val designer: String?,
    val colorProducer: String?,
    val copyright: String?,
    val version: String?,
    val origin: String?,
    val classification: String?,
    val releaseInfoJson: String?,
    val mfcId: String?,
    val amiAmiId: String?,
    val hobbySearchId: String?,
    val goodSmileId: String?,
    val metadata: String?
)
