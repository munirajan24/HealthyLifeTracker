# Healthy Life Tracker 🏃‍♂️💧🍎

An Android health tracking application built with Jetpack Compose that helps users monitor their water intake, food consumption, and gym activities with intelligent timeline predictions and AI-powered insights.

## Features ✨

### Core Tracking
- **💧 Water Logging**: Track daily water intake with smart timing rules
- **🍎 Food Logging**: Log meals with calorie tracking and meal type categorization
- **🏋️ Gym Logging**: Record workouts with duration and calorie burn tracking

### Smart Features
- **📊 Dashboard**: Real-time timeline showing recommended activities
- **📅 Calendar & Streaks**: Track consistency and build healthy habits
- **📈 Analytics**: Weekly water consumption charts and trends
- **🤖 AI Dietitian**: Personalized recommendations based on your activity

### Time-Based Intelligence
- **Smart Water Rules**: Prevents drinking water 30 mins after meals, warns 20 mins before meals
- **Meal Timing**: Recommends 3-hour gaps between meals
- **Workout Suggestions**: Suggests gym time 1 hour after meals

## Tech Stack 🛠️

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material 3
- **Architecture**: MVVM with manual dependency injection
- **Database**: Room (SQLite)
- **Async**: Kotlin Coroutines & Flow
- **Background Work**: WorkManager
- **Charts**: MPAndroidChart
- **Image Loading**: Coil

## Architecture 🏗️

The app follows clean architecture principles with manual dependency injection:

```
app/
├── data/
│   ├── database/        # Room database, DAOs, entities
│   └── repository/      # Data layer abstraction
├── domain/
│   └── logic/          # Business logic (TimeRuleEngine, TimelinePredictor, etc.)
├── ui/
│   ├── screens/        # Composable screens (Dashboard, Water, Food, Gym, etc.)
│   ├── components/     # Reusable UI components
│   ├── theme/          # App theming (colors, typography)
│   └── navigation/     # Navigation setup
├── di/
│   └── AppContainer.kt # Manual DI container
├── workers/            # Background tasks
└── util/              # Utilities (NotificationHelper)
```

### Dependency Injection
This project uses **manual dependency injection** with an `AppContainer` pattern instead of Hilt/Dagger for simplicity and transparency.

## Setup 🚀

### Prerequisites
- Android Studio Hedgehog or later
- JDK 8 or higher
- Android SDK with minimum API 24 (Android 7.0)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/munirajan24/HealthyLifeTracker.git
   cd HealthyLifeTracker
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory

3. **Sync Gradle**
   - Android Studio will automatically sync Gradle
   - Wait for dependencies to download

4. **Run the app**
   - Connect an Android device or start an emulator
   - Click the "Run" button or press `Shift + F10`

## Building 🔨

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

### Run Tests
```bash
./gradlew test
```

## Project Structure 📁

### Key Components

#### ViewModels
- `DashboardViewModel`: Manages timeline events and predictions
- `WaterLoggingViewModel`: Handles water intake tracking with time rules
- `FoodLoggingViewModel`: Manages meal logging
- `GymLoggingViewModel`: Tracks workout sessions
- `CalendarViewModel`: Manages streak calculations
- `AnalyticsViewModel`: Provides weekly analytics data
- `AIInsightsViewModel`: Generates personalized recommendations

#### Domain Logic
- `TimeRuleEngine`: Enforces timing rules for water and meals
- `TimelinePredictor`: Predicts next recommended activities
- `StreakManager`: Calculates activity streaks
- `RecommendationEngine`: Generates AI-powered health insights

## Database Schema 📊

### Entities
- **WaterLogEntity**: `id`, `timestamp`, `amountMl`
- **MealEntity**: `id`, `timestamp`, `type`, `foodName`, `calories`, `protein`, `carbs`, `fat`
- **WorkoutLogEntity**: `id`, `startTime`, `endTime`, `type`, `caloriesBurned`

## Screenshots 📱

_Coming soon_

## Contributing 🤝

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Roadmap 🗺️

- [ ] Add user authentication
- [ ] Sync data across devices
- [ ] Export data to CSV/PDF
- [ ] Integration with fitness trackers
- [ ] Meal photo recognition
- [ ] Social features (share achievements)
- [ ] Dark mode improvements
- [ ] Widget support

## License 📄

This project is open source and available under the [MIT License](LICENSE).

## Contact 📧

Munirajan - [@munirajan24](https://github.com/munirajan24)

Project Link: [https://github.com/munirajan24/HealthyLifeTracker](https://github.com/munirajan24/HealthyLifeTracker)

## Acknowledgments 🙏

- Jetpack Compose for modern Android UI
- Material Design 3 for beautiful components
- MPAndroidChart for data visualization
- The Android developer community

---

**Note**: This app was recently refactored to remove Hilt dependency injection in favor of a simpler manual DI approach using `AppContainer` and `ViewModelFactory`.
