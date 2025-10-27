# Firebase Database Structure - AkibaRoom

## 🎯 Estrategia de Datos

AkibaRoom usa un enfoque **híbrido**:

- **Realtime Database**: Datos que cambian frecuentemente (colecciones, inventarios, mensajes)
- **Firestore**: Búsquedas complejas y queries con índices (figuras globales, tiendas)
- **Firebase Storage**: Imágenes y archivos multimedia

## 📊 Realtime Database Structure

### `/users/{userId}`

Perfiles de usuario y configuración

```json
{
  "users": {
    "user_123": {
      "profile": {
        "uid": "user_123",
        "email": "user@example.com",
        "displayName": "Collector Name",
        "username": "collector123",
        "userType": "COLLECTOR",
        "photoUrl": "https://...",
        "bannerUrl": "https://...",
        "bio": "Anime figure collector",
        "location": "Tokyo, Japan",
        "isVerified": true,
        "isPrivate": false,
        "createdAt": 1234567890000,
        "lastLoginAt": 1234567890000
      },
      "stats": {
        "totalFigures": 150,
        "totalWishlist": 45,
        "totalValue": 45000.0,
        "followersCount": 200,
        "followingCount": 180,
        "achievementsUnlocked": 25,
        "level": 15,
        "experience": 3500
      },
      "preferences": {
        "currency": "JPY",
        "language": "en",
        "theme": "DARK",
        "showNSFW": false,
        "notifications": {
          "achievements": true,
          "social": true,
          "messages": true,
          "priceAlerts": true
        }
      }
    }
  }
}
```

### `/userCollections/{userId}/{figureId}`

Colecciones personales privadas

```json
{
  "userCollections": {
    "user_123": {
      "fig_001": {
        "id": "uf_001",
        "figureId": "fig_001",
        "userId": "user_123",
        "status": "OWNED",
        "purchasePrice": 15999.0,
        "purchaseDate": "2024-01-15",
        "purchaseLocation": "AmiAmi",
        "storeId": null,
        "condition": "MINT",
        "hasBox": true,
        "boxCondition": "MINT",
        "isSigned": false,
        "notes": "Bought on sale",
        "customImages": ["url1", "url2"],
        "displayLocation": "Shelf A",
        "rating": 5,
        "addedAt": 1234567890000,
        "updatedAt": 1234567890000
      }
    }
  }
}
```

### `/stores/{storeId}`

Datos de tiendas

```json
{
  "stores": {
    "store_001": {
      "id": "store_001",
      "ownerId": "user_456",
      "name": "Anime Figures Shop",
      "description": "Best anime figures in Tokyo",
      "logoUrl": "https://...",
      "address": {
        "street": "1-1 Akihabara",
        "city": "Tokyo",
        "country": "Japan",
        "postalCode": "101-0021",
        "latitude": 35.7023,
        "longitude": 139.7745
      },
      "contact": {
        "phone": "+81-3-1234-5678",
        "email": "info@shop.com",
        "website": "https://shop.com"
      },
      "isVerified": true,
      "rating": {
        "averageRating": 4.8,
        "totalReviews": 250
      },
      "createdAt": 1234567890000
    }
  }
}
```

### `/inventory/{storeId}/{figureId}`

Inventario de tiendas

```json
{
  "inventory": {
    "store_001": {
      "fig_001": {
        "id": "inv_001",
        "storeId": "store_001",
        "figureId": "fig_001",
        "quantity": 5,
        "purchasePrice": 12000.0,
        "salePrice": 15999.0,
        "margin": 3999.0,
        "sku": "FIG-001",
        "location": "Shelf B-3",
        "lowStockThreshold": 2,
        "addedAt": 1234567890000,
        "updatedAt": 1234567890000
      }
    }
  }
}
```

### `/sales/{storeId}/{saleId}`

Historial de ventas

```json
{
  "sales": {
    "store_001": {
      "sale_001": {
        "id": "sale_001",
        "storeId": "store_001",
        "customerId": "user_789",
        "customerName": "Customer Name",
        "items": [
          {
            "figureId": "fig_001",
            "figureName": "Rem Crystal Dress",
            "quantity": 1,
            "unitPrice": 15999.0,
            "subtotal": 15999.0
          }
        ],
        "subtotal": 15999.0,
        "tax": 1599.9,
        "total": 17598.9,
        "paymentMethod": "CARD",
        "status": "COMPLETED",
        "createdAt": 1234567890000
      }
    }
  }
}
```

### `/achievements/{userId}/{achievementId}`

Progreso de logros por usuario

```json
{
  "achievements": {
    "user_123": {
      "ach_001": {
        "id": "ua_001",
        "userId": "user_123",
        "achievementId": "ach_001",
        "progress": 150,
        "isCompleted": true,
        "completedAt": 1234567890000,
        "timesCompleted": 1
      }
    }
  }
}
```

### `/messages/{conversationId}/{messageId}`

Mensajes entre usuarios

```json
{
  "messages": {
    "conv_001": {
      "metadata": {
        "participants": ["user_123", "user_456"],
        "createdAt": 1234567890000,
        "updatedAt": 1234567890000
      },
      "msg_001": {
        "id": "msg_001",
        "senderId": "user_123",
        "senderName": "Collector Name",
        "content": "Hello! Is this figure still available?",
        "type": "TEXT",
        "isRead": false,
        "createdAt": 1234567890000
      }
    }
  }
}
```

### `/feed/posts/{postId}`

Posts públicos del feed social

```json
{
  "feed": {
    "posts": {
      "post_001": {
        "id": "post_001",
        "userId": "user_123",
        "userName": "Collector Name",
        "userPhotoUrl": "https://...",
        "content": "Just got this amazing figure!",
        "media": [
          {
            "id": "media_001",
            "url": "https://...",
            "type": "IMAGE"
          }
        ],
        "taggedFigures": ["fig_001"],
        "isNSFW": false,
        "likes": 45,
        "commentsCount": 12,
        "visibility": "PUBLIC",
        "createdAt": 1234567890000
      }
    }
  }
}
```

---

## 🔍 Firestore Collections

### `figures` Collection

Base de datos global de figuras (para búsquedas)

```javascript
{
  id: "fig_001",
  name: "Rem - Crystal Dress Ver.",
  manufacturer: "FuRyu",
  series: "Re:Zero",
  character: "Rem",
  category: "SCALE_FIGURE",
  scale: "1/7",
  releaseDate: "2024-12",
  msrp: 14999.0,
  jan: "4580416942607",
  mfcId: "500291",
  images: ["url1", "url2"],
  tags: ["Re:Zero", "Rem", "FuRyu"],
  isNSFW: false,
  uploadedBy: "user_123",
  uploadedAt: Timestamp,
  // Campos para búsqueda
  searchTerms: ["rem", "rezero", "furyu", "crystal"],
  _metadata: {
    timesAddedToCollection: 150,
    averageRating: 4.8
  }
}
```

**Índices compuestos:**

- `manufacturer` + `category`
- `series` + `category`
- `releaseDate` + `manufacturer`
- `isNSFW` + `category`

### `users` Collection

Perfiles públicos (para búsquedas)

```javascript
{
  uid: "user_123",
  displayName: "Collector Name",
  username: "collector123",
  photoUrl: "https://...",
  userType: "COLLECTOR",
  isVerified: true,
  isPrivate: false,
  stats: {
    totalFigures: 150,
    followersCount: 200
  },
  searchTerms: ["collector", "name", "collector123"]
}
```

**Índices:**

- `username` (unique)
- `userType`
- `isVerified`

### `stores` Collection

Tiendas (para búsqueda geográfica)

```javascript
{
  id: "store_001",
  name: "Anime Figures Shop",
  location: GeoPoint(35.7023, 139.7745),
  city: "Tokyo",
  country: "Japan",
  rating: 4.8,
  isVerified: true,
  searchTerms: ["anime", "figures", "shop", "tokyo"]
}
```

**Índices:**

- `location` (geohash para búsqueda por proximidad)
- `country` + `rating`
- `isVerified`

---

## 📦 Firebase Storage Structure

```
/figures/{figureId}/
  ├── official/
  │   ├── image_001.jpg        # Imagen principal
  │   ├── image_002.jpg        # Imágenes adicionales
  │   └── thumbnail.jpg        # Thumbnail 200x200
  └── community/
      └── user_{userId}_001.jpg

/users/{userId}/
  ├── profile/
  │   ├── avatar.jpg
  │   └── banner.jpg
  └── posts/
      ├── post_{postId}_001.jpg
      └── post_{postId}_002.jpg

/stores/{storeId}/
  ├── logo.jpg
  ├── banner.jpg
  └── gallery/
      ├── shop_001.jpg
      └── shop_002.jpg
```

---

## 🔐 Security Rules

### Realtime Database Rules

```json
{
  "rules": {
    "users": {
      "$userId": {
        ".read": "auth != null",
        ".write": "$userId === auth.uid",
        "profile": {
          ".validate": "newData.child('uid').val() === $userId"
        }
      }
    },
    "userCollections": {
      "$userId": {
        ".read": "$userId === auth.uid",
        ".write": "$userId === auth.uid"
      }
    },
    "stores": {
      "$storeId": {
        ".read": "auth != null",
        ".write": "root.child('stores').child($storeId).child('ownerId').val() === auth.uid"
      }
    },
    "inventory": {
      "$storeId": {
        ".read": "auth != null",
        ".write": "root.child('stores').child($storeId).child('ownerId').val() === auth.uid"
      }
    },
    "sales": {
      "$storeId": {
        ".read": "root.child('stores').child($storeId).child('ownerId').val() === auth.uid",
        ".write": "root.child('stores').child($storeId).child('ownerId').val() === auth.uid"
      }
    },
    "achievements": {
      "$userId": {
        ".read": "auth != null",
        ".write": "false"
      }
    },
    "messages": {
      "$conversationId": {
        ".read": "root.child('messages').child($conversationId).child('metadata').child('participants').val().contains(auth.uid)",
        ".write": "root.child('messages').child($conversationId).child('metadata').child('participants').val().contains(auth.uid)"
      }
    },
    "feed": {
      "posts": {
        "$postId": {
          ".read": "auth != null",
          ".write": "auth.uid === newData.child('userId').val() || !data.exists()"
        }
      }
    }
  }
}
```

### Firestore Security Rules

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    
    // Figuras - Lectura pública, escritura controlada
    match /figures/{figureId} {
      allow read: if true;
      allow create: if request.auth != null;
      allow update, delete: if request.auth.uid == resource.data.uploadedBy;
    }
    
    // Usuarios - Lectura pública, escritura propia
    match /users/{userId} {
      allow read: if true;
      allow write: if request.auth.uid == userId;
    }
    
    // Tiendas - Lectura pública, escritura del owner
    match /stores/{storeId} {
      allow read: if true;
      allow create: if request.auth != null;
      allow update, delete: if request.auth.uid == resource.data.ownerId;
    }
  }
}
```

### Storage Security Rules

```javascript
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    
    // Figuras - Lectura pública, escritura autenticada
    match /figures/{figureId}/{allPaths=**} {
      allow read: if true;
      allow write: if request.auth != null 
                   && request.resource.size < 5 * 1024 * 1024
                   && request.resource.contentType.matches('image/.*');
    }
    
    // Usuarios - Solo el propietario
    match /users/{userId}/{allPaths=**} {
      allow read: if true;
      allow write: if request.auth.uid == userId
                   && request.resource.size < 5 * 1024 * 1024;
    }
    
    // Tiendas - Solo el propietario
    match /stores/{storeId}/{allPaths=**} {
      allow read: if true;
      allow write: if request.auth != null
                   && request.resource.size < 5 * 1024 * 1024;
    }
  }
}
```

---

## 🔄 Sincronización Estrategias

### Datos Críticos (Tiempo Real)

- Colecciones de usuario → Realtime DB + Room cache
- Inventario de tienda → Realtime DB + Room cache
- Mensajes → Realtime DB (no cache sensible)

### Datos Públicos (Cache Agresivo)

- Figuras globales → Firestore + Room cache (30 días)
- Perfiles públicos → Firestore + Room cache (7 días)
- Posts feed → Firestore + Room cache (24 horas)

### Datos Transaccionales (No Cache)

- Ventas → Realtime DB (solo servidor)
- Pagos → Cloud Functions (no cache)

---

## 📊 Estimación de Costos Firebase

### 10,000 usuarios activos/mes

**Realtime Database:**

- Concurrent connections: 500 avg → ~$25/mes
- Storage: 5 GB → ~$25/mes
- Bandwidth download: 50 GB → ~$60/mes
  **Subtotal: ~$110/mes**

**Firestore:**

- Document reads: 10M → ~$36/mes
- Document writes: 2M → ~$18/mes
- Storage: 2 GB → ~$0.40/mes
  **Subtotal: ~$54/mes**

**Storage:**

- Storage: 50 GB → ~$1.30/mes
- Download: 200 GB → ~$34/mes
  **Subtotal: ~$35/mes**

**Cloud Functions:**

- Invocations: 500K → ~$10/mes
  **Subtotal: ~$10/mes**

**TOTAL: ~$210/mes** para 10K usuarios

---

## 🚀 Setup Inicial

### 1. Crear proyecto Firebase

```bash
firebase login
firebase init
```

### 2. Habilitar servicios

- ✅ Authentication (Email, Google, Phone)
- ✅ Realtime Database
- ✅ Cloud Firestore
- ✅ Storage
- ✅ Cloud Functions
- ✅ Analytics
- ✅ Crashlytics

### 3. Configurar en Android

```kotlin
// app/build.gradle.kts
plugins {
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
}
```

### 4. Inicializar en Application

```kotlin
class CollectorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        
        // Habilitar persistencia offline
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        FirebaseFirestore.getInstance().firestoreSettings = 
            firestoreSettings {
                isPersistenceEnabled = true
                cacheSizeBytes = FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED
            }
    }
}
```

---

## 📖 Referencias

- [Firebase Realtime Database](https://firebase.google.com/docs/database)
- [Cloud Firestore](https://firebase.google.com/docs/firestore)
- [Firebase Storage](https://firebase.google.com/docs/storage)
- [Security Rules](https://firebase.google.com/docs/rules)
- [Firebase Pricing](https://firebase.google.com/pricing)
