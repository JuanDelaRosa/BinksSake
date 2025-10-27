# FASE 1: FUNDAMENTOS - Checklist de Progreso

## 1.1 Modelo de Datos Core ✅ COMPLETADO (Actualizado con MFC)

### Modelos Creados

- [x] `Figure.kt` - Modelo completo compatible con MFC
- [x] `UserFigure.kt` - Figuras en colección personal
- [x] `User.kt` - Perfiles de usuario con tipos
- [x] `Store.kt` - Tiendas e inventario
- [x] `Sale.kt` - Ventas y punto de venta
- [x] `Achievement.kt` - Sistema de logros
- [x] `Social.kt` - Posts, likes, comentarios, seguimiento
- [x] `Message.kt` - Mensajería y chat
- [x] `Search.kt` - Búsqueda y filtros
- [x] `Notification.kt` - Sistema de notificaciones
- [x] `Transfer.kt` - Transferencia de figuras
- [x] `MfcIntegration.kt` - Modelos para importar desde MFC

### Enums Creados

- [x] `FigureCategory` - Categorías de figuras (expandido con MFC)
- [x] `FigureStatus` - Estados de figuras del usuario
- [x] `Condition` - Condición física
- [x] `UserType` - Tipos de usuario
- [x] `Currency` - Monedas (expandido con monedas asiáticas)
- [x] `Theme` - Temas de la app
- [x] `PreorderStatus` - Estados de preventa
- [x] `SaleStatus` - Estados de venta
- [x] `PaymentMethod` - Métodos de pago
- [x] `AchievementType` - Tipos de logros
- [x] `AchievementRarity` - Rareza de logros
- [x] `MediaType` - Tipos de media
- [x] `PostVisibility` - Visibilidad de posts
- [x] `MessageType` - Tipos de mensajes
- [x] `SortOption` - Opciones de ordenamiento
- [x] `NotificationType` - Tipos de notificaciones
- [x] `TransferStatus` - Estados de transferencia
- [x] `ExternalStore` - Tiendas externas soportadas

### Nuevos Campos en Figure (MFC Compatible)

- [x] `externalIds` - IDs de MFC, AmiAmi, HobbySearch, etc
- [x] `jan` - Código de barras japonés
- [x] `illustrator` - Ilustrador original
- [x] `designer` - Diseñador
- [x] `colorProducer` - Productor de color
- [x] `copyright` - Copyright info
- [x] `version` - Versión de la figura (Bunny, Swimsuit, etc)
- [x] `origin` - Serie/Anime origen
- [x] `classification` - Clasificación detallada
- [x] `releaseInfo` - Info completa de release

### Documentación
- [x] README.md completo con ejemplos de uso
- [x] MFC_INTEGRATION.md con estrategia completa de integración

---

## 1.1.5 Integración MyFigureCollection ⏳ EN PROGRESO

### Análisis y Diseño

- [x] Analizar estructura de datos de MFC
- [x] Crear modelos compatibles con MFC
- [x] Documentar estrategia de integración
- [x] Identificar APIs disponibles (Tenji API)
- [x] Mapear campos MFC → Figure

### Métodos de Búsqueda

- [ ] Parser de URLs de MFC
- [ ] Parser de códigos JAN/barcode
- [ ] Parser de URLs de AmiAmi
- [ ] Parser de URLs de Hobby Search
- [ ] Parser de URLs de Tokyo Otaku Mode
- [ ] Validación de formatos

### Scraping/API

- [ ] Integrar Jsoup para web scraping
- [ ] Implementar cliente para Tenji API
- [ ] Fallback entre API y scraping
- [ ] Rate limiting y retry logic
- [ ] Cache de respuestas (30 días)

### Importación

- [ ] UseCase: ImportFromMfcUseCase
- [ ] Repository: MfcRepository
- [ ] Mapear MfcFigure → Figure
- [ ] Guardar en Firebase + Room
- [ ] Crear UserFigure automáticamente
- [ ] Otorgar achievement por primera importación

### UI de Importación

- [ ] Pantalla de import con campo de URL
- [ ] Preview de figura antes de importar
- [ ] Loading states
- [ ] Error handling con mensajes claros
- [ ] Success con feedback visual

---

## 1.2 Base de Datos Local (Room) ✅ COMPLETADO

### Entities Creadas

- [x] `FigureEntity` - Figura completa con 40+ campos
- [x] `UserCollectionEntity` - Colección personal con FK a figures
- [x] `StoreEntity` - Perfil de tienda
- [x] `StoreInventoryEntity` - Inventario con FK a store y figure
- [x] `PreorderEntity` - Preventas con FK a store y figure
- [x] `CustomerEntity` - Clientes de tienda con FK a store
- [x] `SaleEntity` - Ventas con FK a store
- [x] `SaleItemEntity` - Items de venta con FK a sale y figure
- [x] `CacheMetadataEntity` - Metadata de caché con TTL
- [x] `SearchHistoryEntity` - Historial de búsquedas
- [x] `NotificationEntity` - Notificaciones locales
- [x] `AchievementEntity` - Definiciones de logros
- [x] `UserAchievementEntity` - Progreso de logros del usuario
- [x] `RemoteKeysEntity` - Paginación remota

### DAOs Creados

- [x] `FiguresDao` - CRUD completo + búsqueda avanzada
- [x] `UserCollectionDao` - CRUD colección + estadísticas
- [x] `RemoteKeysDao` - CRUD remote keys

### Database Configuration

- [x] Actualizar `CollectorDatabase` con 14 entities
- [x] Definir versión 2 de la base de datos
- [x] TypeConverters para List<String> y Map<String, String>
- [x] Configurar índices en campos frecuentes (userId, status, addedAt, etc)
- [x] Foreign Keys con CASCADE delete
- [x] Exportar schema habilitado

### Características Implementadas

- [x] Búsqueda por ID, MFC ID, JAN, Barcode
- [x] Búsqueda full-text en nombre, manufacturer, series, character
- [x] Filtros combinados (manufacturer, series, category, NSFW)
- [x] Paginación con PagingSource en todas las listas
- [x] Flow para datos reactivos
- [x] Queries para obtener listas de filtros (manufacturers, series)
- [x] Estadísticas de colección (count, total value)
- [x] Transacciones para mover figuras entre estados

### Documentación

- [x] README.md completo con ejemplos de uso
- [x] Ejemplos de migraciones
- [x] Guía de testing
- [x] Consideraciones de optimización y seguridad

### Pendientes para Siguiente Fase

- [ ] Crear estrategias de migración 1→2 detalladas
- [ ] Implementar DAOs restantes (Store, Sales, Achievements)
- [ ] Tests unitarios para todos los DAOs
- [ ] Implementar limpieza automática de caché antiguo

---

## 1.3 Firebase Setup ✅ COMPLETADO

### Realtime Database Structure

- [x] `/users/{userId}` - Perfiles con stats y preferences
- [x] `/userCollections/{userId}/{figureId}` - Colecciones privadas
- [x] `/stores/{storeId}` - Datos de tiendas
- [x] `/inventory/{storeId}/{figureId}` - Inventarios
- [x] `/sales/{storeId}/{saleId}` - Historial ventas
- [x] `/achievements/{userId}` - Logros y progreso
- [x] `/messages/{conversationId}` - Mensajes
- [x] `/feed/posts/{postId}` - Posts públicos

### Firestore Collections

- [x] `figures` - Base de datos global con índices compuestos
- [x] `users` - Perfiles públicos con índice en username
- [x] `stores` - Tiendas con geolocalización

### Firebase Storage

- [x] `/figures/{figureId}/` - Imágenes de figuras
- [x] `/users/{userId}/profile/` - Fotos de perfil
- [x] `/stores/{storeId}/` - Logos y galerías
- [x] Estructura de carpetas definida

### Security Rules

- [x] Realtime Database Rules completas
- [x] Firestore Security Rules
- [x] Storage Security Rules con límites de tamaño
- [x] Validaciones de ownership y permissions

### Documentación

- [x] FIREBASE_STRUCTURE.md con 648 líneas
- [x] Ejemplos de estructura de datos JSON
- [x] Security rules completas
- [x] Estrategia de sincronización
- [x] Estimación de costos por usuario
- [x] Setup inicial paso a paso

### Estrategia de Sincronización

- [x] Datos críticos (Tiempo Real) - Realtime DB
- [x] Datos públicos (Cache Agresivo) - Firestore
- [x] Datos transaccionales (No Cache) - Cloud Functions

### Costos Estimados

- [x] Cálculo para 10K usuarios: ~$210/mes
- [x] Breakdown por servicio (Realtime, Firestore, Storage, Functions)

---

## 1.4 Sistema de Autenticación ⏳ PENDIENTE

### Completar features:auth

- [ ] Pantalla de onboarding
- [ ] Login con Email/Google/Phone
- [ ] Registro con selección de tipo (Collector/Store)
- [ ] Formulario de perfil inicial
- [ ] Verificación de email
- [ ] Recuperación de contraseña
- [ ] Términos y condiciones

### Gestión de Sesión

- [ ] Persistencia de sesión
- [ ] Refresh token automático
- [ ] Manejo de sesión expirada
- [ ] Logout en todos los dispositivos

---

## 1.5 Sistema de Sincronización ⏳ PENDIENTE

### Crear core:sync Module

- [ ] Crear módulo `core:sync`
- [ ] `SyncManager` maestro
- [ ] `FigureSyncWorker`
- [ ] `CollectionSyncWorker`
- [ ] `AchievementSyncWorker`
- [ ] Conflict resolution strategy
- [ ] Queue de operaciones pendientes
- [ ] Retry logic con exponential backoff

### WorkManager Setup

- [ ] Configurar WorkManager
- [ ] Periodic sync (cada 15 min)
- [ ] On-demand sync
- [ ] Constraints (WiFi, battery)
- [ ] Status tracking UI

---

## 📊 Progreso General Fase 1

- **Completado**: 3/5 (60%)
- **Tiempo estimado restante**: 2-4 semanas
- **Siguiente tarea prioritaria**: 1.4 Sistema de Autenticación

---

## 🎯 Próximos Pasos Inmediatos

1. **Configurar Autenticación**
    - Implementar flujos de login/registro
    - Manejar diferentes tipos de usuario
    - Configurar persistencia de sesión

2. **Implementar Sincronización**
    - Crear Workers para sincronización
    - Implementar estrategia de caché
    - Manejar conflictos de datos

3. **Completar Pendientes**
   - Revisar y completar pendientes de Fase 1

---

## 📝 Notas de Implementación

### Decisiones Importantes Tomadas

1. **IDs como String**: Mayor flexibilidad para Firebase y UUID
2. **Timestamps en Long**: Compatibilidad universal
3. **Serializable**: Todos los modelos para JSON/Room/Firebase
4. **Nullable mínimo**: Solo cuando realmente necesario
5. **Enums exhaustivos**: Cubren todos los casos de uso

### Consideraciones de Performance

- Usar índices en Room para queries frecuentes
- Implementar paginación en todas las listas
- Caché agresivo para imágenes con Coil
- Lazy loading de datos pesados

### Consideraciones de Seguridad

- Nunca guardar tokens en SharedPreferences sin encriptar
- Validar datos del usuario antes de guardar en Firebase
- Implementar rate limiting en operaciones costosas
- Sanitizar inputs para evitar injection

---

## 🔗 Referencias Útiles

- [Room Database Guide](https://developer.android.com/training/data-storage/room)
- [Firebase Realtime Database](https://firebase.google.com/docs/database)
- [Firebase Auth](https://firebase.google.com/docs/auth)
- [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)
- [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html)
