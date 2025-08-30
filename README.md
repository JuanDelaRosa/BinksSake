# akibaroom 🍶  
A modular Android app to browse local Sake Shops built with Jetpack Compose, Clean Architecture, and Kotlin.

## 📱 Features

- 🗂 Modular architecture with feature-based separation (`core`, `app`, `feature:stores`)
- 🎨 UI fully built using Jetpack Compose and Material 3
- 🔄 Navigation using `NavController` with safe handling and deep linking
- 🧭 Supports screen rotation and state restoration
- 🗺 Clickable address that opens in Google Maps
- 🌐 Website opens using Chrome Custom Tabs
- ⭐ Star-based rating display
- 📷 Image loading with Coil and loading indicator
- 🔁 ViewModel-powered MVI state management
- 🚫 Error handling with fallback UI
- 🧪 Unit testing support
- ✅ Scroll position preserved when returning from detail

## 🧱 Architecture Overview

- **Clean Architecture**:
  - `core`: Shared UI, utilities, and base viewmodel classes
  - `app`: Entry point and dependency wiring
  - `feature:stores`: Implements list and detail of sake shops
  - `feature:figures-api`: Public models and navigation contracts

- **Libraries Used**:
  - `Jetpack Compose`
  - `Navigation Compose`
  - `Koin` for DI
  - `KotlinX Serialization` for JSON parsing
  - `Coil` for image loading
  - `Chrome Custom Tabs` for website redirection

## 🚀 Getting Started

1. Clone this repository
2. Open in Android Studio Hedgehog or newer
3. Sync Gradle and run the app on an emulator or device

## 🧪 Testing

Basic unit test support is configured. You can run tests via:

```bash
./gradlew testDebug
