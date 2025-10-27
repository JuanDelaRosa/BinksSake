# Integración con MyFigureCollection (MFC)

## 🎯 Objetivo

Permitir a los usuarios de AkibaRoom importar figuras desde MyFigureCollection.net, la base de datos
más completa de figuras coleccionables del mundo.

## 📊 Datos Disponibles en MFC

Basado en el análisis de MFC (ejemplo: https://myfigurecollection.net/item/500291), podemos extraer:

### Campos Principales

- **Item ID** - Identificador único de MFC
- **Name** - Nombre completo de la figura
- **Origin** - Serie/Anime de origen
- **Character** - Personaje(s)
- **Version** - Versión específica (Bunny, Swimsuit, etc)
- **Company** - Fabricante (Good Smile, Alter, etc)
- **Classification** - Tipo (Scale Figure, Prize Figure, etc)

### Créditos de Producción

- **Sculpted by** - Escultor
- **Illustrated by** - Ilustrador (para figuras basadas en art)
- **Designed by** - Diseñador
- **Color production by** - Productor de color

### Especificaciones

- **Material** - PVC, ABS, etc
- **Scale** - 1/7, 1/8, Non-scale, etc
- **Height** - Altura en cm
- **Release Date** - Fecha de lanzamiento
- **Release Price** - Precio original (JPY)
- **Barcode/JAN** - Código de barras japonés

### Imágenes

- Múltiples fotos oficiales
- Fotos de la comunidad
- Prototipos vs producto final

## 🔗 Métodos de Búsqueda Soportados

### 1. URL de MFC

```
https://myfigurecollection.net/item/500291
```

### 2. Código JAN (Barcode)

```
4580416942607
```

Los códigos JAN japoneses empiezan con:

- `45` - Japón (figuras de Good Smile, Alter, etc)
- `49` - Japón (otras marcas)
- `697` - China (miHoYo, Apex, etc)

### 3. URLs de Tiendas Externas

**AmiAmi:**

```
https://www.amiami.com/eng/detail/?gcode=FIGURE-130257
```

**Hobby Search (1999.co.jp):**

```
https://www.1999.co.jp/eng/10803259
```

**Tokyo Otaku Mode:**

```
https://otakumode.com/shop/61d7ad76bd25de00380c6999/...
```

## 🏗️ Arquitectura de Integración

```
User Input (MFC URL/JAN)
    ↓
[Parse & Validate]
    ↓
[Check Local DB First]
    ↓
    ├─ Found → Return local figure
    └─ Not Found ↓
        [Scrape MFC / Use API]
            ↓
        [Map to Figure model]
            ↓
        [Save to Firebase & Room]
            ↓
        [Return Figure + UserFigure]
```

## 💾 Mapeo de Datos MFC → Figure

```kotlin
// MFC Data
{
  "itemId": "500291",
  "name": "Alice Nakiri - Bunny Ver.",
  "origin": "Shokugeki no Souma",
  "character": "Nakiri Alice",
  "company": "FREEing",
  "classification": "1/4 Scale",
  "sculptedBy": "Hiroshi Nakanishi",
  "jan": "4580416942607"
}

// Mapped to Figure
Figure(
  id = "fig_mfc_500291",
  name = "Alice Nakiri - Bunny Ver.",
  series = "Shokugeki no Souma",
  character = "Nakiri Alice",
  manufacturer = "FREEing",
  category = FigureCategory.SCALE_FIGURE,
  scale = "1/4",
  sculptor = "Hiroshi Nakanishi",
  externalIds = ExternalIds(
    mfcId = "500291",
    jan = "4580416942607"
  )
)
```

## 🔧 Implementación Técnica

### Opción 1: API No Oficial (Recomendada)

- **Tenji API**: https://api.tenji.moe/docs
- Endpoint: `GET /mfc/item/{itemId}`
- Rate limit: Desconocido
- Gratuita pero no oficial

### Opción 2: Web Scraping (Fallback)

- Usar Jsoup o similar
- Parsear HTML directamente de MFC
- Más lento pero más confiable
- XPath patterns disponibles en Koillection wiki

### Opción 3: Híbrida (Mejor)

1. Intentar API Tenji primero
2. Si falla, scraping directo
3. Cache agresivo (30 días) para reducir requests

## 📝 Flujo de Usuario

### Importar desde MFC

1. **Usuario copia URL de MFC**
   ```
   https://myfigurecollection.net/item/500291
   ```

2. **App procesa URL**
    - Extrae item ID: `500291`
    - Busca en BD local primero
    - Si no existe, consulta MFC

3. **App muestra preview**
   ```
   ┌─────────────────────────────────┐
   │ [Imagen]                        │
   │                                 │
   │ Alice Nakiri - Bunny Ver.       │
   │ FREEing • 1/4 Scale             │
   │ ¥24,800 JPY                     │
   │                                 │
   │ [Agregar a Colección]           │
   │ [Agregar a Wishlist]            │
   └─────────────────────────────────┘
   ```

4. **Usuario confirma**
    - Se crea `Figure` en BD global
    - Se crea `UserFigure` en colección del usuario
    - Se otorga achievement "First Import"

### Búsqueda por JAN/Barcode

1. **Usuario escanea código de barras**
   ```
   4580416942607
   ```

2. **App busca en múltiples fuentes**
    - BD local de AkibaRoom
    - API de MFC (si disponible)
    - APIs de tiendas (AmiAmi, etc)

3. **Muestra resultados ordenados por confianza**
   ```
   ┌─────────────────────────────────┐
   │ ✓ Found in AkibaRoom (100%)     │
   │   Alice Nakiri Bunny            │
   │                                 │
   │ ✓ Found in MFC (95%)            │
   │   Alice Nakiri - Bunny Ver.     │
   │                                 │
   │ ✓ Found in AmiAmi (90%)         │
   │   Nakiri Alice Bunny Ver.       │
   └─────────────────────────────────┘
   ```

## 🚀 Funcionalidades Adicionales

### 1. Disponibilidad en Tiendas

Buscar dónde está disponible la figura:

- AmiAmi
- Hobby Search
- Mercari JP
- Yahoo Auctions JP
- Mandarake
- Surugaya

### 2. Alertas de Precio

- Notificar cuando baje de precio
- Notificar cuando vuelva a stock
- Notificar cuando salga en preventa

### 3. Comparación de Precios

```
┌─────────────────────────────────┐
│ Alice Nakiri Bunny Ver.         │
├─────────────────────────────────┤
│ AmiAmi:        ¥19,800 ⭐ Mejor │
│ HobbySearch:   ¥21,500          │
│ Mandarake:     ¥22,000          │
│ Mercari JP:    ¥18,500 (usado)  │
└─────────────────────────────────┘
```

### 4. Historial de Precios

Gráfica mostrando evolución del precio en el tiempo.

## ⚠️ Consideraciones Legales

### Rate Limiting

- Máximo 10 requests/minuto por usuario
- Máximo 100 requests/día por usuario nuevo
- Usuarios verificados: 1000 requests/día

### Caché

- Figuras: 30 días
- Imágenes: 90 días (usar CDN)
- Precios: 24 horas
- Disponibilidad: 6 horas

### Atribución

Mostrar en cada figura importada:

```
ℹ️ Data from MyFigureCollection
   Community-driven database
```

### Términos de Uso

- No replicar toda la BD de MFC
- Solo importar figuras que el usuario agregue
- Respetar copyright de imágenes
- No revender datos

## 🔒 Privacidad

### Datos que NO compartimos con MFC:

- Precio de compra del usuario
- Dónde la compró
- Notas personales
- Si está firmada
- Fotos personales

### Datos que SÍ podríamos compartir (opcional):

- Que el usuario posee X figura
- Ratings/reviews públicos
- Fotos de la comunidad (con permiso)

## 📊 Métricas a Trackear

- Total de figuras importadas desde MFC
- Figuras más importadas
- Tiempo promedio de importación
- Tasa de éxito de scraping
- Fuentes de importación (MFC vs JAN vs otras)

## 🎯 Milestones de Implementación

### Fase 1: Básico (MVP)

- [x] Modelos de datos MFC
- [ ] Parser de URLs de MFC
- [ ] Scraping básico de MFC
- [ ] Mapeo MFC → Figure
- [ ] Importar a colección personal

### Fase 2: Barcode

- [ ] Integrar ML Kit Barcode Scanner
- [ ] Búsqueda por JAN en BD local
- [ ] Búsqueda por JAN en MFC
- [ ] Fallback a búsqueda manual

### Fase 3: Tiendas Externas

- [ ] Parsear AmiAmi URLs
- [ ] Parsear Hobby Search URLs
- [ ] API de disponibilidad en tiendas
- [ ] Comparador de precios

### Fase 4: Avanzado

- [ ] Alertas de precio
- [ ] Historial de precios
- [ ] Recomendaciones basadas en MFC
- [ ] Sincronización bidireccional (opcional)

## 🛠️ Herramientas Útiles

### APIs Disponibles

- **Tenji API**: https://api.tenji.moe/
- **Figure Hunter**: https://figurehunter.net/ (scraping)
- **Barcode Lookup**: APIs de Google Shopping, eBay

### Librerías

- **Jsoup**: Web scraping
- **ML Kit**: Barcode scanning
- **Coil**: Image loading con cache
- **Room**: Cache local
- **WorkManager**: Background sync

### Testing

- Item de prueba: https://myfigurecollection.net/item/500291
- JAN de prueba: `4580416942607`
- AmiAmi de prueba: `FIGURE-130257`

## 📖 Referencias

- [MFC Homepage](https://myfigurecollection.net/)
- [MFC API Discussion](https://myfigurecollection.net/club/349/)
- [Tenji API Docs](https://api.tenji.moe/docs)
- [Figure Hunter](https://figurehunter.net/)
- [Koillection Scraping Guide](https://github.com/benjaminjonard/koillection/wiki/Scraping)
