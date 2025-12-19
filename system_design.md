# System Architecture & Design
## Decision Training App – Android (Courage System)

---

## 1. Overview

This document describes the **system architecture and design** for the Android version of the decision-training app focused on building **Courage** (decision-taking capacity).

The system is designed to:
- Force decision-making
- Detect behavioral patterns across scenarios
- Decay Courage based on avoidance (not correctness)
- Support infinite, high-quality scenario flow
- Scale from B2C (individual users) to B2B (corporate training)

---

## 2. Architecture Style

**Client–Server Architecture with AI-assisted content generation**

```
Android App (Client)
      ↓
API Gateway
      ↓
-------------------------------------------------
| Auth | User | Scenario | Courage | Analytics |
-------------------------------------------------
      ↓
Data Layer + AI Services
```

---

## 3. Android Client Architecture

### 3.1 Tech Stack (Suggested)
- Language: **Kotlin**
- UI: **Jetpack Compose**
- Architecture: **MVVM**
- Networking: **Retrofit + OkHttp**
- State Management: **ViewModel + StateFlow**
- Local Cache: **Room DB**
- Auth: **Google Sign-In (Firebase Auth)**

---

### 3.2 Client Modules

#### 1. Authentication Module
- Google Sign-In
- Token storage & refresh
- Silent login on app reopen

#### 2. Scenario Player Module
- Swipe-based card UI
- Decision timer (hesitation tracking)
- Gesture handling (left / right)
- Offline fallback using cached scenarios

#### 3. Courage Meter UI
- Displays remaining Courage for the day
- Subtle visual indicator (no alarms)
- Updates after server confirmation

#### 4. Feedback Module
- "You chose" affirmation screen
- Trade-off explanation
- Optional reflection message

#### 5. Local Persistence
- Cache last N scenarios
- Store pending decisions (offline mode)
- Sync when network is available

---

## 4. Backend Architecture (Core Services)

### 4.1 API Gateway
- Entry point for all clients
- Auth validation
- Rate limiting
- Service routing

---

### 4.2 Authentication Service
- OAuth via Google
- Firebase Auth or equivalent
- Maps external ID → internal user ID

---

### 4.3 User Profile Service

Stores user identity and preferences.

**Key Data:**
- User ID
- Age range
- Occupation / role
- Experience level
- Current Courage state

**Example Schema:**
```json
{
  "user_id": "uuid",
  "profile": {
    "age_range": "25-34",
    "occupation": "Corporate",
    "role": "IC",
    "experience": "3-5"
  },
  "courage": {
    "current": 4,
    "last_reset": "2025-06-16"
  }
}
```

---

### 4.4 Scenario Service

**Core responsibility:** deliver the *right* scenario at the *right* time.

#### Inputs
- User profile
- Recent themes served
- Detected behavioral patterns
- Difficulty level

#### Outputs
- One scenario payload with tags

#### Constraints
- Avoid repetition
- Rotate themes
- Match user context (office / personal)

---

### 4.5 Courage Engine

Tracks decision stamina instead of correctness.

#### Inputs
- Hesitation time
- Decision posture (avoidant / assertive / ownership)
- Pattern repetition

#### Outputs
- Courage increment / decrement
- Trigger reason (for analytics & feedback)

#### Phase 1
- Rule-based thresholds

#### Phase 2
- ML-assisted decay sensitivity

---

### 4.6 Decision Evaluation Service

Processes each swipe event.

**Captures:**
- Scenario ID
- Option chosen
- Time to decide
- Posture selected

**Returns:**
- Feedback text
- Courage impact
- Pattern signal update

---

### 4.7 Pattern Detection Engine

Aggregates behavior across scenarios.

Tracks:
- Avoidance frequency per theme
- Authority anxiety
- Approval-seeking behavior
- Overthinking patterns

**Example Pattern State:**
```json
{
  "theme": "Conflict vs Comfort",
  "avoidant_ratio": 0.72,
  "confidence_level": "low"
}
```

Triggers insights only after sufficient data.

---

## 5. AI Layer

### 5.1 Scenario Generation Engine (Hybrid)

AI generates scenarios **only within strict templates**.

**Inputs:**
- Theme
- Tension axis
- Context
- Role
- Stakes

**Outputs:**
- Scenario text
- Two balanced options
- Consequence outlines

Human-curated seed scenarios act as guardrails.

---

### 5.2 Explanation Generator

- Converts internal consequence logic into human-readable feedback
- Emotion-first, neutral tone
- No moral judgment

---

### 5.3 Personalization Engine

- Adjusts theme frequency
- Scales difficulty
- Tunes Courage decay sensitivity

---

## 6. Data Architecture

### Core Collections / Tables

- **Users** – profile & Courage state
- **Scenarios** – seed + AI-generated
- **Decisions** – per-swipe logs
- **Courage Logs** – decay reasons
- **Pattern Summaries** – aggregated behavior

---

## 7. End-to-End Scenario Flow

```
User opens app
   ↓
Auth validated
   ↓
Scenario Service selects scenario
   ↓
User swipes decision
   ↓
Decision Evaluation Service
   ↓
Courage Engine updates Courage
   ↓
Pattern Engine updates signals
   ↓
Feedback generated
   ↓
Next scenario served
```

---

## 8. Infinite Scenario Strategy

- Seed 200–300 human-written scenarios
- AI generates variations (context, stakes, role)
- Themes repeat, stories change
- Difficulty adapts with user growth

This ensures the scenario stream never exhausts.

---

## 9. Analytics & Observability

Track:
- Time-to-decision
- Courage decay triggers
- Theme-wise avoidance
- Session length
- Drop-off points

Used for:
- Product iteration
- Personalization
- B2B insights (future)

---

## 10. Security & Privacy

- No clinical mental health data
- No diagnosis or labeling
- Anonymized analytics
- GDPR-compliant storage

---

## 11. Design Principles Recap

- Decisions > correctness
- Patterns > single events
- Courage > lives
- Simple UX, deep logic

---

## 12. Next Steps

- Update PRD with Courage system
- Define API contracts
- Create Android wireframes
- Design AI prompts & moderation rules

---

**End of system-design.md**

