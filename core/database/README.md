# Core Database Module - Room

Este módulo contiene toda la capa de persistencia local usando Room Database.

## 📦 Estructura

```
core/database/
├── entities/
│   ├── FigureEntity.kt              # Figuras globales
│   ├── UserCollectionEntity.kt      # Colección personal
│   ├── StoreEntities.kt             # Tiendas, inventario, preventas
│   ├── SaleEntities.kt              # Ventas y items
│   ├── CacheEntities.kt             # Caché y metadata
│   └── RemoteKeysEntity.kt          # Paginación remota
├── dao/
│   ├── FiguresDao.kt                # CRUD figuras + búsqueda
│   ├── UserCollectionDao.kt         # CRUD colección
│   └── RemoteKeysDao.kt             # CRUD remote keys
├── converters/
│   └── Converters.kt                # Type converters JSON
└── CollectorDatabase.kt             # Database principal

```

## 🗄️ Entities (Tablas)

### 1. `figures` - Base de Datos Global de Figuras

```kotlin
FigureEntity(
    id: String,                    // Primary Key
    name: String,                  // Nombre
    manufacturer: String,          // Fabricante
    series: String,                // Serie/Anime
    character: String,             // Personaje
    category: String,              // Categoría
    jan: String?,                  // Código JAN
    mfcId: String?,               // ID de MyFigureCollection
    // ... 40+ campos
)
```

**Índices:**

- Ninguno (búsqueda por PK o full scan)

### 2. `user_collection` - Colección Personal

```kotlin
UserCollectionEntity(
    id: String,                    // Primary Key
    figureId: String,              // FK → figures
    userId: String,                // ID del usuario
    status: String,                // OWNED, WISHLIST, PREORDER, etc
    purchasePrice: Double?,        // Precio de compra
    condition: String,             // Condición física
    notes: String?,                // Notas personales
    // ...
)
```

**Índices:**

- `figureId`
- `userId`
- `status`
- `addedAt`

### 3. `stores` - Tiendas

```kotlin
StoreEntity(
    id: String,
    ownerId: String,
    name: String,
    description: String,
    // ...
)
```

### 4. `store_inventory` - Inventario de Tiendas

```kotlin
StoreInventoryEntity(
    id: String,
    storeId: String,               // FK → stores
    figureId: String,              // FK → figures
    quantity: Int,                 // Cantidad en stock
    purchasePrice: Double,         // Precio de compra
    salePrice: Double,             // Precio de venta
    margin: Double,                // Margen
    // ...
)
```

### 5. `sales` - Ventas

```kotlin
SaleEntity(
    id: String,
    storeId: String,
    customerId: String?,
    total: Double,
    paymentMethod: String,
    status: String,
    // ...
)
```

### 6. `cache_metadata` - Metadata de Caché

```kotlin
CacheMetadataEntity(
    cacheKey: String,
    dataType: String,
    cachedAt: Long,
    expiresAt: Long,
    size: Long
)
```

### 7. `achievements` - Logros

### 8. `user_achievements` - Progreso de Logros

### 9. `notifications` - Notificaciones Locales

### 10. `search_history` - Historial de Búsquedas

## 🔍 DAOs (Data Access Objects)

### FiguresDao

```kotlin
// Buscar por ID
suspend fun getById(id: String): FigureEntity?

// Buscar por MFC ID
suspend fun getByMfcId(mfcId: String): FigureEntity?

// Buscar por JAN/barcode
suspend fun getByJan(jan: String): FigureEntity?

// Búsqueda con paginación
fun search(query: String): PagingSource<Int, FigureEntity>

// Filtros avanzados
fun filterFigures(
    manufacturer: String?,
    series: String?,
    category: String?,
    isNSFW: Boolean
): PagingSource<Int, FigureEntity>

// Obtener listas para filtros
suspend fun getAllManufacturers(): List<String>
suspend fun getAllSeries(): List<String>
```

### UserCollectionDao

```kotlin
// Obtener colección con paginación
fun getUserCollection(userId: String): PagingSource<Int, UserCollectionEntity>

// Obtener wishlist con paginación
fun getUserWishlist(userId: String): PagingSource<Int, UserCollectionEntity>

// Estadísticas
suspend fun getCollectionCount(userId: String): Int
suspend fun getWishlistCount(userId: String): Int
suspend fun getTotalValue(userId: String): Double?

// Mover entre estados
suspend fun moveToWishlist(id: String)
suspend fun moveToCollection(id: String)
```

## 🔄 Type Converters

Para almacenar tipos complejos como listas y maps en Room:

```kotlin
class Converters {
    // List<String> ↔ String (JSON)
    @TypeConverter
    fun fromStringList(value: List<String>): String
    
    @TypeConverter
    fun toStringList(value: String): List<String>
    
    // Map<String, String> ↔ String (JSON)
    @TypeConverter
    fun fromStringMap(value: Map<String, String>): String
    
    @TypeConverter
    fun toStringMap(value: String): Map<String, String>
}
```

## 📝 Uso Básico

### Insertar Figura

```kotlin
val figureEntity = FigureEntity(
    id = "fig_001",
    name = "Rem - Crystal Dress",
    manufacturer = "FuRyu",
    series = "Re:Zero",
    character = "Rem",
    category = "SCALE_FIGURE",
    images = "[\"url1\",\"url2\"]",
    tags = "[\"Re:Zero\",\"Rem\"]",
    uploadedBy = "user_123",
    uploadedAt = System.currentTimeMillis(),
    // ...
)

database.figuresDao().insert(figureEntity)
```

### Agregar a Colección

```kotlin
val userFigure = UserCollectionEntity(
    id = "uf_001",
    figureId = "fig_001",
    userId = "user_123",
    status = "OWNED",
    purchasePrice = 159.99,
    condition = "MINT",
    hasBox = true,
    customImages = "[]",
    addedAt = System.currentTimeMillis(),
    updatedAt = System.currentTimeMillis()
)

database.userCollectionDao().insert(userFigure)
```

### Buscar Figuras

```kotlin
// Por ID
val figure = database.figuresDao().getById("fig_001")

// Por JAN
val figureByJan = database.figuresDao().getByJan("4580416942607")

// Búsqueda con Flow
database.figuresDao().getRecent(20).collect { figures ->
    // Actualizar UI
}

// Búsqueda con Paging
val pager = Pager(
    config = PagingConfig(pageSize = 20),
    pagingSourceFactory = { database.figuresDao().search("Rem") }
).flow
```

### Obtener Colección del Usuario

```kotlin
val pager = Pager(
    config = PagingConfig(pageSize = 20),
    pagingSourceFactory = { 
        database.userCollectionDao().getUserCollection("user_123") 
    }
).flow.cachedIn(viewModelScope)
```

## 🚀 Migraciones

### Versión 1 → 2

```kotlin
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Agregar nuevas tablas
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS `user_collection` (
                `id` TEXT NOT NULL PRIMARY KEY,
                `figureId` TEXT NOT NULL,
                `userId` TEXT NOT NULL,
                ...
            )
        """)
        
        // Agregar índices
        database.execSQL("""
            CREATE INDEX index_user_collection_figureId 
            ON user_collection(figureId)
        """)
    }
}
```

### Registrar en DatabaseModule

```kotlin
Room.databaseBuilder(context, CollectorDatabase::class.java, "collector_db")
    .addMigrations(MIGRATION_1_2)
    .build()
```

## ⚡ Optimizaciones

### 1. Índices Estratégicos

Los índices están configurados en campos frecuentemente usados en WHERE y JOIN:

- `userId` - Filtrar por usuario
- `status` - Filtrar por estado
- `addedAt` - Ordenamiento temporal
- `figureId` - Foreign keys

### 2. Paginación

Todas las listas largas usan `PagingSource` para cargar datos incrementalmente.

### 3. Flow vs Suspend

- **Flow**: Para datos que cambian (UI reactiva)
- **Suspend**: Para operaciones únicas (insert, update, delete)

### 4. Transaction

Operaciones que modifican múltiples tablas usan `@Transaction`:

```kotlin
@Transaction
suspend fun moveToWishlist(id: String) {
    // Asegura atomicidad
    updateStatus(id, "WISHLIST", System.currentTimeMillis())
}
```

## 🔒 Consideraciones de Seguridad

### Datos Sensibles

- **NO guardar**: Passwords, tokens sin encriptar
- **Encriptar**: Datos personales críticos (usar SQLCipher)
- **Caché limitado**: Social data (30 días max)

### Sincronización

- Siempre validar datos de Firebase antes de insertar
- Limpiar caché antigua periódicamente
- Manejar conflictos de sincronización

## 📊 Tamaños Estimados

| Tabla | Registros Típicos | Tamaño Aprox |
|-------|-------------------|--------------|
| `figures` | 1,000 - 10,000 | 5-50 MB |
| `user_collection` | 100 - 1,000 | 1-5 MB |
| `cache_metadata` | 1,000 - 5,000 | <1 MB |
| `search_history` | 100 - 500 | <1 MB |

**Total estimado: 10-100 MB** dependiendo del uso

## 🧪 Testing

```kotlin
@RunWith(AndroidJUnit4::class)
class FiguresDaoTest {
    private lateinit var database: CollectorDatabase
    private lateinit var figuresDao: FiguresDao
    
    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            context,
            CollectorDatabase::class.java
        ).build()
        figuresDao = database.figuresDao()
    }
    
    @Test
    fun insertAndRetrieveFigure() = runTest {
        val figure = FigureEntity(...)
        figuresDao.insert(figure)
        
        val retrieved = figuresDao.getById(figure.id)
        assertEquals(figure, retrieved)
    }
}
```

## 📖 Referencias

- [Room Documentation](https://developer.android.com/training/data-storage/room)
- [Paging 3 Library](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- [Type Converters](https://developer.android.com/training/data-storage/room/referencing-data)
- [Migrations](https://developer.android.com/training/data-storage/room/migrating-db-versions)
