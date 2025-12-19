# Courage (PowerDecision) — Decision Training App (Android)

Train the *act of deciding* (not “being right”) through fast, swipe-based scenarios that surface avoidance patterns and build decisiveness over time.

## Repo contents

- **Android app**: `android/` (Kotlin, Jetpack Compose, MVVM)
- **Product docs**:
  - `PRD.md`
  - `system_design.md`
  - `scenario.md` (MVP scenarios and tagging schema)

## Tech stack (current)

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM (ViewModel + StateFlow)
- **Local storage**: Room
- **Auth / backend**: Firebase Auth + Firestore repositories (where configured)

## Run locally (Android Studio)

1. Open **Android Studio** → **Open** → select the `android/` folder.
2. Let Gradle sync complete.
3. Run `app` on an emulator or physical device.

## Run locally (CLI)

From the repo root:

```bash
cd android
./gradlew :app:assembleDebug
./gradlew :app:installDebug
```

## Firebase setup notes

This project includes `android/app/google-services.json`. If you swap Firebase projects, replace that file with your own and re-sync Gradle.

## High-level flow (MVP)

- User signs in (Firebase Auth)
- App presents a scenario → user swipes left/right to choose
- Choice is recorded, feedback is shown, and the app tracks behavior patterns over time

## Contributing

- Keep build artifacts out of Git (root `.gitignore` already covers Gradle/Android Studio output).
- Prefer small, focused commits with clear messages.


