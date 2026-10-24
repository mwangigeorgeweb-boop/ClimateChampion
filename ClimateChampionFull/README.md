# 🌍 Climate Champion — Android App

A gamified climate education app built with **Kotlin**, **Firebase**, and **MVVM**.

---

## Project Structure

```
ClimateChampion/
├── app/
│   ├── build.gradle                  # App dependencies
│   ├── google-services.json          # 🔑 Replace with your Firebase config
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/climatechampion/
│       │   ├── model/                # Data classes
│       │   ├── network/              # Retrofit + NetworkResult
│       │   ├── repository/           # Firestore + API repos
│       │   ├── viewmodel/            # 6 ViewModels
│       │   ├── ui/                   # Activities + Fragments + Adapters
│       │   │   ├── auth/             # Splash, Login, Register
│       │   │   ├── home/             # Home screen
│       │   │   ├── topics/           # Topic list + detail
│       │   │   ├── video/            # Video list + player
│       │   │   ├── quiz/             # Quiz + result
│       │   │   ├── leaderboard/      # Leaderboard
│       │   │   └── profile/          # User profile
│       │   └── utils/                # Constants, Extensions, PointsCalculator
│       └── res/
│           ├── layout/               # All XML layouts
│           ├── navigation/           # nav_graph.xml
│           ├── menu/                 # Bottom nav menu
│           ├── drawable/             # Vector icons + backgrounds
│           ├── values/               # strings, colors, themes
│           └── anim/                 # Splash animations
├── functions/
│   └── src/index.ts                  # Firebase Cloud Functions (all endpoints)
├── firestore.rules                   # Firestore security rules
├── seed_data.json                    # 10 topics, 20 videos, 25 questions, 5 users
└── README.md
```

---

## Setup Instructions

### 1. Firebase Project
1. Create a project at https://console.firebase.google.com
2. Enable **Authentication** (Email/Password + Google)
3. Enable **Firestore Database**
4. Enable **Storage**
5. Download `google-services.json` → replace `app/google-services.json`

### 2. Update Constants
- `app/build.gradle` → set your `BASE_URL` (Cloud Functions URL)
- `res/values/strings.xml` → set `default_web_client_id` (from Firebase console)

### 3. Seed the Database
Run the seed script or import `seed_data.json` using the Firebase console or a custom script.

### 4. Deploy Cloud Functions
```bash
cd functions
npm install
npm run build
firebase deploy --only functions
```
Note the deployed URL → update `BASE_URL` in `build.gradle`.

### 5. Deploy Firestore Rules
```bash
firebase deploy --only firestore:rules
```

### 6. Build the App
Open the project in **Android Studio**, sync Gradle, and run.

---

## Points System
| Action | Points |
|---|---|
| Complete a topic reading | +50 |
| Watch a full video (≥5 min) | +30 |
| Pass a quiz (≥70%) | +100 |
| Perfect quiz score (100%) | +200 |
| Daily login | +10 |

## Levels
| Level | Points Required |
|---|---|
| Beginner | 0 – 1,999 |
| Intermediate | 2,000 – 4,999 |
| Expert | 5,000+ |
