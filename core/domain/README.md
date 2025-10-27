# Core Domain Models - AkibaRoom

Este módulo contiene todos los modelos de dominio compartidos de la aplicación.

## 📦 Estructura de Modelos

### 1. **Figure.kt** - Figuras y Coleccionables

- `Figure` - Modelo global de figura en la base de datos
- `Dimensions` - Dimensiones físicas de la figura
- `FigureCategory` - Categorías (Scale, Nendoroid, Figma, etc)
- `MeasurementUnit` - Unidades de medida (CM, INCH, MM)

### 2. **UserFigure.kt** - Figuras en Colección Personal

- `UserFigure` - Figura en colección de usuario con datos personales
- `FigureStatus` - Estado (OWNED, WISHLIST, PREORDER, SOLD, etc)
- `Condition` - Condición física (MINT, NEAR_MINT, GOOD, etc)

### 3. **User.kt** - Perfiles de Usuario

- `UserProfile` - Perfil completo del usuario
- `UserType` - Tipo de usuario (COLLECTOR, STORE, ADMIN)
- `SocialLinks` - Enlaces a redes sociales
- `UserPreferences` - Preferencias del usuario
- `NotificationPreferences` - Preferencias de notificaciones
- `UserStats` - Estadísticas del usuario
- `Currency` - Monedas soportadas
- `Theme` - Temas de la app

### 4. **Store.kt** - Tiendas e Inventario

- `Store` - Perfil de tienda
- `Address` - Dirección física con coordenadas
- `StoreContact` - Información de contacto
- `StoreHours` - Horarios de atención
- `DayHours` - Horario por día
- `StorePolicies` - Políticas de la tienda
- `StoreRating` - Sistema de calificación
- `StoreInventoryItem` - Item en inventario de tienda
- `PreorderItem` - Preventas/pedidos
- `PreorderStatus` - Estado de preventa

### 5. **Sale.kt** - Ventas y Punto de Venta

- `Sale` - Venta completa con items
- `SaleItem` - Item individual de una venta
- `SaleStatus` - Estado de venta
- `PaymentMethod` - Método de pago
- `Customer` - Cliente de tienda
- `StoreAnalytics` - Analytics de tienda
- `TopSellingItem` - Figura más vendida
- `CategorySales` - Ventas por categoría
- `CustomerPurchaseInfo` - Info de compras de cliente
- `AnalyticsPeriod` - Período de análisis

### 6. **Achievement.kt** - Sistema de Logros

- `Achievement` - Logro/Medalla
- `AchievementType` - Tipo de logro
- `AchievementCategory` - Categoría (Bronze, Silver, Gold, etc)
- `AchievementRarity` - Rareza (Common, Rare, Legendary, etc)
- `AchievementRequirement` - Requisito para desbloquear
- `AchievementReward` - Recompensa al desbloquear
- `UserAchievement` - Progreso de logro de usuario
- `AchievementNotification` - Notificación de logro

### 7. **Social.kt** - Red Social

- `Post` - Publicación en feed
- `Media` - Contenido multimedia (imagen/video)
- `MediaType` - Tipo de media
- `FigureTag` - Figura etiquetada en post
- `PostVisibility` - Visibilidad del post
- `Comment` - Comentario en post
- `Like` - Like en post o comentario
- `LikeTargetType` - Target del like
- `Follow` - Relación de seguimiento
- `UserFollowInfo` - Info de usuario para seguir
- `FeedItem` - Item en feed (post, logro, figura nueva)
- `FeedItemType` - Tipo de item en feed
- `AchievementFeedItem` - Logro en feed
- `NewFigureFeedItem` - Nueva figura en feed

### 8. **Message.kt** - Mensajería

- `Conversation` - Conversación entre usuarios
- `Message` - Mensaje individual
- `MessageType` - Tipo de mensaje
- `SharedFigure` - Figura compartida en chat
- `FigureShareAction` - Acción al compartir figura
- `ConversationPreview` - Preview de conversación
- `ConversationUser` - Usuario en conversación
- `BlockedUser` - Usuario bloqueado

### 9. **Search.kt** - Búsqueda y Filtros

- `SearchQuery` - Query de búsqueda con filtros
- `PriceRange` - Rango de precios
- `DateRange` - Rango de fechas
- `SortOption` - Opciones de ordenamiento
- `SearchSuggestion` - Sugerencia de búsqueda
- `SuggestionType` - Tipo de sugerencia
- `SearchHistory` - Historial de búsquedas
- `SavedSearch` - Búsqueda guardada
- `FilterOptions` - Opciones de filtros disponibles
- `FilterOption` - Opción de filtro individual

### 10. **Notification.kt** - Notificaciones

- `Notification` - Notificación en app
- `NotificationType` - Tipo de notificación
- `NotificationActionData` - Datos de acción
- `NotificationActionType` - Tipo de acción
- `PushNotificationPayload` - Payload para push

### 11. **Transfer.kt** - Transferencias

- `FigureTransfer` - Transferencia de figura entre usuarios
- `TransferStatus` - Estado de transferencia
- `FigureOwnershipHistory` - Historial de propietarios
- `AcquisitionSource` - Origen de adquisición

## 🎯 Uso de los Modelos

### Ejemplo: Crear una figura completa

```kotlin
val figure = Figure(
    id = "fig_123",
    name = "Rem -Crystal Dress Ver.-",
    manufacturer = "FuRyu",
    series = "Re:Zero",
    character = "Rem",
    category = FigureCategory.SCALE_FIGURE,
    scale = "1/7",
    releaseDate = "2024-12",
    msrp = 149.99,
    barcode = "4580416942607",
    images = listOf("https://..."),
    description = "Beautiful crystal dress version",
    isNSFW = false,
    uploadedBy = "user_456",
    tags = listOf("Re:Zero", "Rem", "Crystal", "FuRyu"),
    dimensions = Dimensions(
        height = 24.0,
        width = 15.0,
        depth = 15.0,
        unit = MeasurementUnit.CM
    ),
    weight = 0.5,
    sculptor = "Unknown"
)
```

### Ejemplo: Agregar figura a colección personal

```kotlin
val userFigure = UserFigure(
    id = "uf_789",
    figureId = "fig_123",
    userId = "user_456",
    status = FigureStatus.OWNED,
    purchasePrice = 159.99,
    purchaseDate = "2024-11-15",
    purchaseLocation = "AmiAmi",
    condition = Condition.MINT,
    hasBox = true,
    boxCondition = Condition.MINT,
    notes = "Bought on sale, arrived in perfect condition"
)
```

### Ejemplo: Crear venta en tienda

```kotlin
val sale = Sale(
    id = "sale_001",
    storeId = "store_123",
    customerId = "customer_456",
    items = listOf(
        SaleItem(
            figureId = "fig_123",
            figureName = "Rem Crystal Dress",
            quantity = 1,
            unitPrice = 159.99,
            subtotal = 159.99
        )
    ),
    subtotal = 159.99,
    tax = 12.80,
    total = 172.79,
    paymentMethod = PaymentMethod.CARD,
    status = SaleStatus.COMPLETED
)
```

## 🔧 Dependencias

```kotlin
// build.gradle.kts
dependencies {
    implementation(libs.kotlinx.serialization.json)
}
```

## 📝 Notas Importantes

1. **Todos los modelos son `@Serializable`** para poder ser guardados en Room, enviados por
   Firebase, o serializados a JSON.

2. **Uso de nullable con cuidado**: Solo campos que realmente pueden ser null son nullables.

3. **Defaults sensatos**: Todos los campos tienen valores por defecto apropiados.

4. **Timestamps en Long**: Usamos `System.currentTimeMillis()` para timestamps consistentes.

5. **IDs como String**: Permite flexibilidad entre UUID, Firebase IDs, o IDs custom.

## 🚀 Próximos Pasos

Después de completar estos modelos de dominio, necesitas:

1. ✅ **COMPLETADO** - Modelos de dominio
2. ⏭️ **SIGUIENTE** - Entities de Room (1.2)
3. ⏭️ **SIGUIENTE** - DAOs de Room (1.2)
4. ⏭️ **SIGUIENTE** - Estructura Firebase (1.3)

## 📖 Referencias

- [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html)
- [Room Database](https://developer.android.com/training/data-storage/room)
- [Firebase Database](https://firebase.google.com/docs/database)
