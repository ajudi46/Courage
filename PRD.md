# PRD – Courage App (Detailed)

## Part 1: Foundation, Context & Product Intent

---

## 1. Why This Product Exists (Deep Context)

Modern users are not struggling because they make bad decisions. They are struggling because they **do not make decisions at all**.

Across workplaces, families, and social settings, a recurring behavioral pattern is visible:

* People defer decisions upward (to managers, parents, seniors)
* People avoid choosing when outcomes feel uncertain
* People overthink small decisions to avoid accountability
* People freeze when forced to decide under pressure

This behavior compounds over time and creates a **self-reinforcing loop**:

> Low confidence → Decision avoidance → Reduced agency → Even lower confidence

The most damaging part of this loop is not the outcome of decisions, but the **loss of trust in one’s own judgment**.

Existing solutions (self-help content, advice apps, productivity tools, therapy-oriented products) focus on:

* Explaining how to decide
* Teaching frameworks
* Optimizing correctness

They **do not train the act of deciding itself**.

This product exists to address that gap.

---

## 2. What This App Is (and Is Not)

### What This App IS

* A **decision-training environment**
* A **low-risk simulation** for real-life dilemmas
* A daily practice tool for building decisiveness
* A behavioral mirror that surfaces avoidance patterns

The app’s primary function is to **force users to choose**, repeatedly, in realistic scenarios.

### What This App Is NOT

* Not a therapy or mental health product
* Not a personality assessment
* Not a moral judgment engine
* Not a “right vs wrong” quiz app

The app does **not** aim to tell users what is correct. It aims to help users **own their choices**.

---

## 3. Core Product Philosophy

The central belief behind this product is:

> **Confidence is not built by being right. It is built by choosing — again and again.**

In real life:

* People trust those who decide, even if imperfectly
* Avoidance erodes credibility faster than mistakes
* Most decisions are reversible, but indecision is costly

This app intentionally prioritizes:

* Action over optimization
* Ownership over comfort
* Awareness over judgment

---

## 4. The Behavioral Problem Being Solved

### 4.1 Learned Dependence

Many users grew up in environments where:

* Decisions were made for them
* Obedience was rewarded over judgment
* Failure was punished heavily

As adults, this manifests as:

* Seeking validation before acting
* Fear of being blamed
* Avoidance of visible ownership

---

### 4.2 Avoidance as a Default Strategy

Avoidance shows up as:

* Silence in meetings
* Delaying feedback
* Over-analysis
* Letting others decide

Avoidance feels safe short-term, but damages:

* Growth
* Trust
* Leadership potential

The app is explicitly designed to **interrupt avoidance**.

---

## 5. What Success Looks Like for the User

Success is **not**:

* Always choosing the best option
* Maximizing accuracy
* Feeling confident immediately

Success **is**:

* Reduced hesitation
* Increased willingness to choose
* Comfort with imperfect outcomes
* Ability to stand by decisions

The app trains **decision stamina**, not decision perfection.

---

## 6. Courage: The Central Mechanic (Conceptual Explanation)

### 6.1 What Courage Represents

Courage represents the user’s **capacity to tolerate uncertainty and responsibility**.

It is:

* Finite per day
* Replenished daily
* Drained by avoidance, not mistakes

Courage is intentionally framed as a **resource**, not a punishment system.

---

### 6.2 Why Courage Exists

In real life:

* Decision fatigue is real
* Avoidance increases when energy is low

Courage models this reality by:

* Ending sessions when avoidance patterns dominate
* Encouraging reflection instead of shame

The goal is not to push endlessly, but to **build awareness and resilience over time**.

---

## 7. What the App Is Training (Explicitly)

The app is training users to:

* Choose under uncertainty
* Accept trade-offs
* Take responsibility
* Reduce fear of discomfort

It is **not training**:

* Moral correctness
* Perfect logic
* Risk-seeking behavior

---

## 8. Why Swipe-Based Decisions

The swipe interface is intentional:

* Removes overthinking
* Forces commitment
* Mimics real-time decisions

There is:

* No neutral option
* No skip
* No undo

This design reinforces:

> *“Not choosing is not an option.”*

---

## 9. How This App Creates Real-World Impact

The app creates impact through:

1. **Repetition** – repeated exposure to decision tension
2. **Pattern Awareness** – surfacing behavioral tendencies
3. **Emotional Safety** – low-risk failure environment
4. **Identity Shift** – from ‘I hesitate’ to ‘I choose’

Real-world behavior change is expected to be gradual, not immediate.

---

## 10. Summary of Intent (For AI & Builders)

This app should be built with the following intent:

* Do not judge users
* Do not preach correctness
* Do not optimize for scores
* Optimize for decisiveness
* Make avoidance visible, not shameful

Every design, system, and content decision should reinforce:

> **Choosing matters more than being right.**

---

**End of Part 1 – Foundation & Context**

Next parts will cover:

* Detailed user journeys & flows
* Decision themes & scenario mechanics
* Courage dynamics (rules, decay, recovery)
* System behavior & edge cases


# PRD – Courage App (Detailed)

## Part 2: User Journey, Experience & Behavioral Flow

---

## 1. Purpose of This Section

This section explains **how a user experiences the app end-to-end**, and *why* each step exists psychologically.

The intent is not just to describe screens, but to make clear:

* What the user should feel
* What behavior the system is encouraging
* What behavior the system is discouraging

This context is critical so that builders (human or AI) do not accidentally optimize for speed, gamification, or correctness instead of **decisiveness**.

---

## 2. Entry Point: First-Time User Experience

### 2.1 App Install → First Open

**User State:**

* Curious but skeptical
* Possibly low confidence
* Fear of being judged or evaluated

**Design Intent:**

* Reduce anxiety
* Avoid performance framing
* Emphasize safety and experimentation

The app should *not* immediately talk about improvement, scores, or assessment.

---

### 2.2 Authentication (Google Sign-In)

**Why It Exists:**

* Persist progress across reinstalls
* Enable long-term pattern tracking
* Avoid anonymous, throwaway usage

**UX Principles:**

* One-tap sign-in
* No complex account setup
* No passwords

**Behavioral Intent:**

> “This is something you return to, not a one-off game.”

---

## 3. Onboarding Flow (Critical Context Setting)

### 3.1 Onboarding Goal

The onboarding does **not** aim to profile personality.

It aims to:

* Provide contextual relevance
* Set expectations
* Frame the experience correctly

---

### 3.2 Information Collected & Why

| Data                      | Why It Matters              |
| ------------------------- | --------------------------- |
| Age Range                 | Adjusts scenario realism    |
| Occupation                | Enables workplace scenarios |
| Role (IC/Manager/Student) | Controls power dynamics     |
| Experience Level          | Adjusts stakes              |
| Preferred Context         | Improves early relevance    |

No sensitive or psychological data is collected.

---

### 3.3 Onboarding Messaging (Important)

The onboarding must clearly state:

> “This app doesn’t test you. It trains you to choose.”

Users should understand:

* There are no perfect answers
* Discomfort is expected
* Hesitation is normal

---

## 4. Daily Entry Experience

### 4.1 When User Opens the App (Returning User)

**User State:**

* Low mental load tolerance
* Limited time

**System Behavior:**

* No menus first
* No dashboards first
* Immediate scenario presentation

**Why:**
Avoid giving users a chance to postpone deciding.

---

### 4.2 Courage Visibility

* Courage count is visible but subtle
* No warning colors
* No countdown anxiety

**Behavioral Intent:**
Courage is awareness, not pressure.

---

## 5. Core Loop: Decision Experience

This is the most important part of the app.

---

### 5.1 Scenario Presentation

**Rules:**

* One scenario per screen
* No scrolling
* No extra context links

**Scenario Tone:**

* Realistic
* Emotionally grounded
* Ambiguous by design

**User Feeling:**

> “I’ve been here before.”

---

### 5.2 Forced Choice (Swipe Interaction)

**Interaction Design:**

* Swipe left or right only
* No skip
* No neutral
* No undo

**Why This Matters:**
In real life, not choosing *is* a choice. The UI mirrors this truth.

---

### 5.3 Hesitation Tracking (Invisible)

The system tracks:

* Time before swipe
* Micro-pauses

**Important:**

* No visible timer
* No countdown pressure

The goal is to *observe*, not stress.

---

## 6. Immediate Feedback Flow

### 6.1 Step 1: Affirmation

Always shown:

> **“You chose.”**

**Why:**
This reinforces identity before outcome.

---

### 6.2 Step 2: Trade-Off Explanation

Explains:

* What this decision protects
* What it risks

**Rules:**

* No “right/wrong” labels
* No moral language
* No shaming

---

### 6.3 Step 3: Insight (Conditional)

Only shown when:

* A pattern is emerging
* Confidence threshold met

Example:

> “You often choose comfort in conflict situations.”

Tone is observational, not evaluative.

---

## 7. Courage Dynamics in the Experience

### 7.1 When Courage Stays the Same

* User chooses quickly
* User takes ownership
* User chooses uncomfortable option

Even if outcome is suboptimal.

---

### 7.2 When Courage Decreases

* Repeated hesitation
* Repeated avoidance within same theme
* Consistent deferral patterns

**Important:**
Courage loss is *never* immediate or punitive.

---

### 7.3 Zero Courage State

When Courage reaches zero:

* Session ends softly
* Message focuses on awareness
* No failure language

Example:

> “You’ve reached decision fatigue today.”

This mirrors real-life cognitive limits.

---

## 8. Reflection Without Shame

After session end, user may see:

* Pattern summary
* Gentle reflection prompt

**No calls to action like:**

* “Do better”
* “Improve score”

Reflection is optional, never forced.

---

## 9. Progress Experience (Over Time)

Progress is communicated via:

* Reduced hesitation
* Increased Courage retention
* Theme-based awareness

**Not via:**

* Leaderboards
* Rankings
* Accuracy percentages

---

## 10. Emotional Safety Principles

Every user-facing interaction must:

* Preserve dignity
* Avoid judgment
* Normalize discomfort

If users feel evaluated, the product fails.

---

## 11. Anti-Patterns (What Must Be Avoided)

* Gamified streak pressure
* Obvious correct answers
* Shaming language
* Over-instruction
* Excessive explanations

---

## 12. Summary for Builders & AI Agents

When designing any flow or screen, ask:

1. Does this force a decision?
2. Does this reduce avoidance?
3. Does this preserve dignity?
4. Does this reward courage, not correctness?

If the answer to any is no, redesign.

---

**End of Part 2 – User Journey & Experience**

Next part:

* Part 3: Courage System & Decision Dynamics
# PRD – Courage App (Detailed)

## Part 3: Courage System & Decision Dynamics

---

## 1. Purpose of This Section

This section explains **exactly how the Courage system works**, both conceptually and operationally.

The goal is to ensure that anyone building this app (human or AI) clearly understands:

* What Courage represents
* Why it changes
* When it should *not* change
* How it reinforces decisiveness without judgment

This section must be treated as **foundational logic**, not a gamification layer.

---

## 2. What Courage Represents (Deep Definition)

Courage represents the user’s **capacity to tolerate uncertainty, discomfort, and responsibility while deciding**.

It is **not**:

* A score
* A measure of intelligence
* A measure of correctness
* A measure of morality

Courage is a *state*, not a trait.

It fluctuates daily and is intentionally framed as a **renewable resource**.

---

## 3. Why Courage Exists as a System

In real life:

* Decision-making consumes mental energy
* Avoidance increases as energy drops
* Fatigue leads to deferral and safety-seeking

The Courage system models this reality by:

* Limiting decision volume per day
* Ending sessions when avoidance dominates
* Encouraging rest instead of forcing performance

The system’s role is **awareness and pacing**, not control.

---

## 4. Courage Lifecycle

### 4.1 Daily Reset

* Courage resets once every calendar day
* Default daily Courage: **5**
* Reset occurs regardless of previous day’s performance

**Rationale:**
Avoids shame loops and reinforces that decisiveness is a *practice*, not a permanent label.

---

### 4.2 Visibility to the User

* Courage is always visible
* Presented subtly (icons / dots)
* No alarms, no flashing, no red warnings

Courage should feel like **fuel**, not a countdown.

---

## 5. When Courage Should NOT Change

Courage must **never** be reduced for:

* Choosing an uncomfortable option
* Choosing an option with negative consequences
* Being "wrong"
* Taking responsibility
* Making a fast decision

If Courage is reduced for these, the product fails its core philosophy.

---

## 6. Courage Decay: What Actually Reduces Courage

Courage decreases **only** when patterns of avoidance are detected.

Avoidance is defined as **behavior that consistently minimizes short-term discomfort at the cost of long-term agency**.

---

### 6.1 Hesitation-Based Decay

**What is measured:**

* Time-to-decision
* Repeated long pauses

**Rule:**

* Single hesitation does nothing
* Repeated hesitation across multiple scenarios triggers decay

**Why:**
Hesitation signals fear of ownership, not thoughtfulness.

---

### 6.2 Pattern-Based Avoidance

Courage decreases when the user:

* Repeatedly selects avoidant postures within the same theme
* Consistently chooses comfort over ownership
* Defers responsibility across different contexts

Decay only occurs after **statistical confidence** (not one-off events).

---

### 6.3 Second-Guessing & Reversal Patterns

Detected when:

* User repeatedly changes stance on similar decisions
* User avoids standing by past choices

This reflects a lack of trust in personal judgment.

---

### 6.4 Safety Bias Accumulation

If the user consistently chooses:

* Silence
* Delay
* Deflection

Across *different* themes, Courage decays more slowly but persistently.

This captures chronic avoidance.

---

## 7. Courage Decay Rules (Operational)

* Courage never drops more than **1 point at a time**
* Courage decay events are **rate-limited**
* No cascading penalties

This prevents the system from feeling punitive.

---

## 8. Courage Preservation & Recovery

### 8.1 Courage Preservation

Courage is preserved when users:

* Decide quickly after prior hesitation
* Choose ownership-heavy options
* Maintain consistency across similar scenarios

These do not increase Courage but **protect it**.

---

### 8.2 Optional Courage Recovery (Advanced)

Optional future mechanic:

* Regain 1 Courage for decisive behavior after fatigue

This must be subtle and never gamified.

---

## 9. Zero Courage State (Session Termination)

When Courage reaches zero:

* The current session ends
* No more scenarios are served
* The user is not labeled as failed

**Messaging Principles:**

* Calm
* Normalizing
* Reflective

Example:

> “You’ve reached decision fatigue today. That’s okay.”

---

## 10. Relationship Between Courage & Patterns

Courage is influenced by **patterns**, not individual decisions.

Patterns are evaluated across:

* Themes
* Contexts
* Time

This ensures:

* Fairness
* Accuracy
* Emotional safety

---

## 11. Guardrails (Non-Negotiable)

* Never show Courage loss immediately after a single decision
* Never explain Courage loss as "wrong choice"
* Never rank users by Courage
* Never compare Courage across users

Violating any of these undermines trust.

---

## 12. How Builders Should Think About Courage

Courage should be implemented as:

* A **state machine**, not a score counter
* A pacing mechanism
* A feedback signal for awareness

If Courage feels like punishment, the implementation is wrong.

---

## 13. Summary for AI & Engineering

When implementing Courage, prioritize:

* Pattern detection over event reaction
* Longitudinal behavior over single actions
* Dignity over discipline

Courage exists to answer one question:

> “Is the user avoiding decisions today?”

---

**End of Part 3 – Courage System & Decision Dynamics**

Next part:

* Part 4: Scenario System & Content Philosophy
# PRD – Courage App (Detailed)

## Part 3: Courage System & Decision Dynamics

---

## 1. Purpose of This Section

This section explains **exactly how the Courage system works**, both conceptually and operationally.

The goal is to ensure that anyone building this app (human or AI) clearly understands:

* What Courage represents
* Why it changes
* When it should *not* change
* How it reinforces decisiveness without judgment

This section must be treated as **foundational logic**, not a gamification layer.

---

## 2. What Courage Represents (Deep Definition)

Courage represents the user’s **capacity to tolerate uncertainty, discomfort, and responsibility while deciding**.

It is **not**:

* A score
* A measure of intelligence
* A measure of correctness
* A measure of morality

Courage is a *state*, not a trait.

It fluctuates daily and is intentionally framed as a **renewable resource**.

---

## 3. Why Courage Exists as a System

In real life:

* Decision-making consumes mental energy
* Avoidance increases as energy drops
* Fatigue leads to deferral and safety-seeking

The Courage system models this reality by:

* Limiting decision volume per day
* Ending sessions when avoidance dominates
* Encouraging rest instead of forcing performance

The system’s role is **awareness and pacing**, not control.

---

## 4. Courage Lifecycle

### 4.1 Daily Reset

* Courage resets once every calendar day
* Default daily Courage: **5**
* Reset occurs regardless of previous day’s performance

**Rationale:**
Avoids shame loops and reinforces that decisiveness is a *practice*, not a permanent label.

---

### 4.2 Visibility to the User

* Courage is always visible
* Presented subtly (icons / dots)
* No alarms, no flashing, no red warnings

Courage should feel like **fuel**, not a countdown.

---

## 5. When Courage Should NOT Change

Courage must **never** be reduced for:

* Choosing an uncomfortable option
* Choosing an option with negative consequences
* Being "wrong"
* Taking responsibility
* Making a fast decision

If Courage is reduced for these, the product fails its core philosophy.

---

## 6. Courage Decay: What Actually Reduces Courage

Courage decreases **only** when patterns of avoidance are detected.

Avoidance is defined as **behavior that consistently minimizes short-term discomfort at the cost of long-term agency**.

---

### 6.1 Hesitation-Based Decay

**What is measured:**

* Time-to-decision
* Repeated long pauses

**Rule:**

* Single hesitation does nothing
* Repeated hesitation across multiple scenarios triggers decay

**Why:**
Hesitation signals fear of ownership, not thoughtfulness.

---

### 6.2 Pattern-Based Avoidance

Courage decreases when the user:

* Repeatedly selects avoidant postures within the same theme
* Consistently chooses comfort over ownership
* Defers responsibility across different contexts

Decay only occurs after **statistical confidence** (not one-off events).

---

### 6.3 Second-Guessing & Reversal Patterns

Detected when:

* User repeatedly changes stance on similar decisions
* User avoids standing by past choices

This reflects a lack of trust in personal judgment.

---

### 6.4 Safety Bias Accumulation

If the user consistently chooses:

* Silence
* Delay
* Deflection

Across *different* themes, Courage decays more slowly but persistently.

This captures chronic avoidance.

---

## 7. Courage Decay Rules (Operational)

* Courage never drops more than **1 point at a time**
* Courage decay events are **rate-limited**
* No cascading penalties

This prevents the system from feeling punitive.

---

## 8. Courage Preservation & Recovery

### 8.1 Courage Preservation

Courage is preserved when users:

* Decide quickly after prior hesitation
* Choose ownership-heavy options
* Maintain consistency across similar scenarios

These do not increase Courage but **protect it**.

---

### 8.2 Optional Courage Recovery (Advanced)

Optional future mechanic:

* Regain 1 Courage for decisive behavior after fatigue

This must be subtle and never gamified.

---

## 9. Zero Courage State (Session Termination)

When Courage reaches zero:

* The current session ends
* No more scenarios are served
* The user is not labeled as failed

**Messaging Principles:**

* Calm
* Normalizing
* Reflective

Example:

> “You’ve reached decision fatigue today. That’s okay.”

---

## 10. Relationship Between Courage & Patterns

Courage is influenced by **patterns**, not individual decisions.

Patterns are evaluated across:

* Themes
* Contexts
* Time

This ensures:

* Fairness
* Accuracy
* Emotional safety

---

## 11. Guardrails (Non-Negotiable)

* Never show Courage loss immediately after a single decision
* Never explain Courage loss as "wrong choice"
* Never rank users by Courage
* Never compare Courage across users

Violating any of these undermines trust.

---

## 12. How Builders Should Think About Courage

Courage should be implemented as:

* A **state machine**, not a score counter
* A pacing mechanism
* A feedback signal for awareness

If Courage feels like punishment, the implementation is wrong.

---

## 13. Summary for AI & Engineering

When implementing Courage, prioritize:

* Pattern detection over event reaction
* Longitudinal behavior over single actions
* Dignity over discipline

Courage exists to answer one question:

> “Is the user avoiding decisions today?”

---

**End of Part 3 – Courage System & Decision Dynamics**

Next part:

* Part 4: Scenario System & Content Philosophy
# PRD – Courage App (Detailed)

## Part 3: Courage System & Decision Dynamics

---

## 1. Purpose of This Section

This section explains **exactly how the Courage system works**, both conceptually and operationally.

The goal is to ensure that anyone building this app (human or AI) clearly understands:

* What Courage represents
* Why it changes
* When it should *not* change
* How it reinforces decisiveness without judgment

This section must be treated as **foundational logic**, not a gamification layer.

---

## 2. What Courage Represents (Deep Definition)

Courage represents the user’s **capacity to tolerate uncertainty, discomfort, and responsibility while deciding**.

It is **not**:

* A score
* A measure of intelligence
* A measure of correctness
* A measure of morality

Courage is a *state*, not a trait.

It fluctuates daily and is intentionally framed as a **renewable resource**.

---

## 3. Why Courage Exists as a System

In real life:

* Decision-making consumes mental energy
* Avoidance increases as energy drops
* Fatigue leads to deferral and safety-seeking

The Courage system models this reality by:

* Limiting decision volume per day
* Ending sessions when avoidance dominates
* Encouraging rest instead of forcing performance

The system’s role is **awareness and pacing**, not control.

---

## 4. Courage Lifecycle

### 4.1 Daily Reset

* Courage resets once every calendar day
* Default daily Courage: **5**
* Reset occurs regardless of previous day’s performance

**Rationale:**
Avoids shame loops and reinforces that decisiveness is a *practice*, not a permanent label.

---

### 4.2 Visibility to the User

* Courage is always visible
* Presented subtly (icons / dots)
* No alarms, no flashing, no red warnings

Courage should feel like **fuel**, not a countdown.

---

## 5. When Courage Should NOT Change

Courage must **never** be reduced for:

* Choosing an uncomfortable option
* Choosing an option with negative consequences
* Being "wrong"
* Taking responsibility
* Making a fast decision

If Courage is reduced for these, the product fails its core philosophy.

---

## 6. Courage Decay: What Actually Reduces Courage

Courage decreases **only** when patterns of avoidance are detected.

Avoidance is defined as **behavior that consistently minimizes short-term discomfort at the cost of long-term agency**.

---

### 6.1 Hesitation-Based Decay

**What is measured:**

* Time-to-decision
* Repeated long pauses

**Rule:**

* Single hesitation does nothing
* Repeated hesitation across multiple scenarios triggers decay

**Why:**
Hesitation signals fear of ownership, not thoughtfulness.

---

### 6.2 Pattern-Based Avoidance

Courage decreases when the user:

* Repeatedly selects avoidant postures within the same theme
* Consistently chooses comfort over ownership
* Defers responsibility across different contexts

Decay only occurs after **statistical confidence** (not one-off events).

---

### 6.3 Second-Guessing & Reversal Patterns

Detected when:

* User repeatedly changes stance on similar decisions
* User avoids standing by past choices

This reflects a lack of trust in personal judgment.

---

### 6.4 Safety Bias Accumulation

If the user consistently chooses:

* Silence
* Delay
* Deflection

Across *different* themes, Courage decays more slowly but persistently.

This captures chronic avoidance.

---

## 7. Courage Decay Rules (Operational)

* Courage never drops more than **1 point at a time**
* Courage decay events are **rate-limited**
* No cascading penalties

This prevents the system from feeling punitive.

---

## 8. Courage Preservation & Recovery

### 8.1 Courage Preservation

Courage is preserved when users:

* Decide quickly after prior hesitation
* Choose ownership-heavy options
* Maintain consistency across similar scenarios

These do not increase Courage but **protect it**.

---

### 8.2 Optional Courage Recovery (Advanced)

Optional future mechanic:

* Regain 1 Courage for decisive behavior after fatigue

This must be subtle and never gamified.

---

## 9. Zero Courage State (Session Termination)

When Courage reaches zero:

* The current session ends
* No more scenarios are served
* The user is not labeled as failed

**Messaging Principles:**

* Calm
* Normalizing
* Reflective

Example:

> “You’ve reached decision fatigue today. That’s okay.”

---

## 10. Relationship Between Courage & Patterns

Courage is influenced by **patterns**, not individual decisions.

Patterns are evaluated across:

* Themes
* Contexts
* Time

This ensures:

* Fairness
* Accuracy
* Emotional safety

---

## 11. Guardrails (Non-Negotiable)

* Never show Courage loss immediately after a single decision
* Never explain Courage loss as "wrong choice"
* Never rank users by Courage
* Never compare Courage across users

Violating any of these undermines trust.

---

## 12. How Builders Should Think About Courage

Courage should be implemented as:

* A **state machine**, not a score counter
* A pacing mechanism
* A feedback signal for awareness

If Courage feels like punishment, the implementation is wrong.

---

## 13. Summary for AI & Engineering

When implementing Courage, prioritize:

* Pattern detection over event reaction
* Longitudinal behavior over single actions
* Dignity over discipline

Courage exists to answer one question:

> “Is the user avoiding decisions today?”

---

**End of Part 3 – Courage System & Decision Dynamics**

Next part:

* Part 4: Scenario System & Content Philosophy
# PRD – Courage App (Detailed)

## Part 3: Courage System & Decision Dynamics

---

## 1. Purpose of This Section

This section explains **exactly how the Courage system works**, both conceptually and operationally.

The goal is to ensure that anyone building this app (human or AI) clearly understands:

* What Courage represents
* Why it changes
* When it should *not* change
* How it reinforces decisiveness without judgment

This section must be treated as **foundational logic**, not a gamification layer.

---

## 2. What Courage Represents (Deep Definition)

Courage represents the user’s **capacity to tolerate uncertainty, discomfort, and responsibility while deciding**.

It is **not**:

* A score
* A measure of intelligence
* A measure of correctness
* A measure of morality

Courage is a *state*, not a trait.

It fluctuates daily and is intentionally framed as a **renewable resource**.

---

## 3. Why Courage Exists as a System

In real life:

* Decision-making consumes mental energy
* Avoidance increases as energy drops
* Fatigue leads to deferral and safety-seeking

The Courage system models this reality by:

* Limiting decision volume per day
* Ending sessions when avoidance dominates
* Encouraging rest instead of forcing performance

The system’s role is **awareness and pacing**, not control.

---

## 4. Courage Lifecycle

### 4.1 Daily Reset

* Courage resets once every calendar day
* Default daily Courage: **5**
* Reset occurs regardless of previous day’s performance

**Rationale:**
Avoids shame loops and reinforces that decisiveness is a *practice*, not a permanent label.

---

### 4.2 Visibility to the User

* Courage is always visible
* Presented subtly (icons / dots)
* No alarms, no flashing, no red warnings

Courage should feel like **fuel**, not a countdown.

---

## 5. When Courage Should NOT Change

Courage must **never** be reduced for:

* Choosing an uncomfortable option
* Choosing an option with negative consequences
* Being "wrong"
* Taking responsibility
* Making a fast decision

If Courage is reduced for these, the product fails its core philosophy.

---

## 6. Courage Decay: What Actually Reduces Courage

Courage decreases **only** when patterns of avoidance are detected.

Avoidance is defined as **behavior that consistently minimizes short-term discomfort at the cost of long-term agency**.

---

### 6.1 Hesitation-Based Decay

**What is measured:**

* Time-to-decision
* Repeated long pauses

**Rule:**

* Single hesitation does nothing
* Repeated hesitation across multiple scenarios triggers decay

**Why:**
Hesitation signals fear of ownership, not thoughtfulness.

---

### 6.2 Pattern-Based Avoidance

Courage decreases when the user:

* Repeatedly selects avoidant postures within the same theme
* Consistently chooses comfort over ownership
* Defers responsibility across different contexts

Decay only occurs after **statistical confidence** (not one-off events).

---

### 6.3 Second-Guessing & Reversal Patterns

Detected when:

* User repeatedly changes stance on similar decisions
* User avoids standing by past choices

This reflects a lack of trust in personal judgment.

---

### 6.4 Safety Bias Accumulation

If the user consistently chooses:

* Silence
* Delay
* Deflection

Across *different* themes, Courage decays more slowly but persistently.

This captures chronic avoidance.

---

## 7. Courage Decay Rules (Operational)

* Courage never drops more than **1 point at a time**
* Courage decay events are **rate-limited**
* No cascading penalties

This prevents the system from feeling punitive.

---

## 8. Courage Preservation & Recovery

### 8.1 Courage Preservation

Courage is preserved when users:

* Decide quickly after prior hesitation
* Choose ownership-heavy options
* Maintain consistency across similar scenarios

These do not increase Courage but **protect it**.

---

### 8.2 Optional Courage Recovery (Advanced)

Optional future mechanic:

* Regain 1 Courage for decisive behavior after fatigue

This must be subtle and never gamified.

---

## 9. Zero Courage State (Session Termination)

When Courage reaches zero:

* The current session ends
* No more scenarios are served
* The user is not labeled as failed

**Messaging Principles:**

* Calm
* Normalizing
* Reflective

Example:

> “You’ve reached decision fatigue today. That’s okay.”

---

## 10. Relationship Between Courage & Patterns

Courage is influenced by **patterns**, not individual decisions.

Patterns are evaluated across:

* Themes
* Contexts
* Time

This ensures:

* Fairness
* Accuracy
* Emotional safety

---

## 11. Guardrails (Non-Negotiable)

* Never show Courage loss immediately after a single decision
* Never explain Courage loss as "wrong choice"
* Never rank users by Courage
* Never compare Courage across users

Violating any of these undermines trust.

---

## 12. How Builders Should Think About Courage

Courage should be implemented as:

* A **state machine**, not a score counter
* A pacing mechanism
* A feedback signal for awareness

If Courage feels like punishment, the implementation is wrong.

---

## 13. Summary for AI & Engineering

When implementing Courage, prioritize:

* Pattern detection over event reaction
* Longitudinal behavior over single actions
* Dignity over discipline

Courage exists to answer one question:

> “Is the user avoiding decisions today?”

---

**End of Part 3 – Courage System & Decision Dynamics**

Next part:

* Part 4: Scenario System & Content Philosophy
# PRD – Courage App (Detailed)

## Part 4: Scenario System & Content Philosophy

---

## 1. Purpose of This Section

This section explains **how scenarios are designed, generated, categorized, and evolved**.

Scenarios are the *core training material* of the app. Poor scenarios will destroy trust, retention, and behavioral impact — regardless of how good the system is.

This section exists to ensure:

* Scenarios train decisiveness, not correctness
* Content feels realistic and respectful
* AI generation never degrades quality or intent

---

## 2. What a Scenario Really Is

A scenario is **not a question**.

A scenario is:

* A moment of tension
* A forced trade-off
* A snapshot of uncertainty

Each scenario must recreate the *emotional friction* that causes people to hesitate in real life.

---

## 3. Design Principles for Scenarios (Non-Negotiable)

Every scenario must:

1. Feel realistic and familiar
2. Present two *plausible* choices
3. Avoid obvious right/wrong answers
4. Trigger discomfort or uncertainty
5. Force ownership

If a scenario feels like a test, it has failed.

---

## 4. Decision Themes (Why Themes Matter More Than Stories)

Scenarios are grouped by **decision themes**, not topics.

Themes represent **recurring behavioral tensions** that appear across different life contexts.

### Core Themes (MVP)

* Conflict vs Comfort
* Ownership vs Safety
* Autonomy vs Approval
* Speed vs Perfection
* Authority vs Assertion

The same theme can appear in:

* Office scenarios
* Personal scenarios
* Authority scenarios

This allows the system to detect **patterns**, not one-off preferences.

---

## 5. Tension Axis (The Spine of Every Scenario)

Each scenario must map to a **single tension axis**:

> Option A ←→ Option B

Examples:

* Speak up ←→ Stay silent
* Decide now ←→ Delay
* Take ownership ←→ Deflect

Without a clear axis, pattern detection becomes impossible.

---

## 6. Decision Postures (Invisible but Critical)

Each option is tagged with a **decision posture**, such as:

* Avoidant
* Assertive
* Ownership-heavy
* Approval-seeking
* Decisive

Users never see these labels, but the system uses them to detect behavior.

---

## 7. Scenario Tagging Schema (Mandatory)

Each scenario must include:

* Theme
* Context (Office / Personal)
* Role
* Power Dynamic
* Stakes Level
* Tension Axis
* Option A posture
* Option B posture
* Avoidance indicator

Without full tagging, scenarios cannot be used reliably.

---

## 8. Stakes Level & Progression

Stakes determine emotional intensity, not importance.

### Stakes Levels

* Low: Minimal consequences
* Medium: Social or professional friction
* High: Responsibility, visibility, leadership

Stakes should increase gradually as user tolerance improves.

---

## 9. Feedback Philosophy for Scenarios

Feedback must:

* Explain trade-offs
* Normalize discomfort
* Avoid preaching

Feedback must never:

* Moralize
* Shame
* Declare correctness

The goal is awareness, not instruction.

---

## 10. Scenario Generation Strategy

### Phase 1 – Human-Curated Core

* 200–300 hand-written scenarios
* High realism
* Strong tagging

This forms the **quality baseline**.

---

### Phase 2 – AI-Assisted Variation

AI is allowed to:

* Change context
* Adjust stakes
* Swap roles

AI is NOT allowed to:

* Invent new themes
* Change tension axes
* Introduce moral language

---

### Phase 3 – Controlled AI Generation

AI generates full scenarios only within strict templates.

Every generated scenario must pass:

* Axis clarity check
* Plausibility check
* Tone check

---

## 11. Infinite Scenario Flow (How It Never Ends)

Scenarios are infinite because:

* Themes repeat
* Stories vary
* Contexts rotate
* Stakes adapt

The user never exhausts decision patterns, only surface stories.

---

## 12. Content Guardrails (Very Important)

Scenarios must NEVER:

* Include extreme trauma
* Involve illegal activity
* Force moral absolutes
* Shame or insult the user

Content must feel safe but challenging.

---

## 13. Summary for Builders & AI Agents

When generating or reviewing scenarios, ask:

1. Does this recreate real hesitation?
2. Are both choices defensible?
3. Is the tension clear?
4. Does this train choosing, not judging?

If not, discard the scenario.

---

**End of Part 4 – Scenario System & Content Philosophy**
# PRD – Courage App (Detailed)

## Part 5: System Behavior & Technical Expectations (Product View)

---

## 1. Purpose of This Section

This section explains **how the system should behave as a whole**, from a *product logic* perspective.

This is not a low-level engineering document. Instead, it defines **what the system must support and enforce** to preserve product intent.

---

## 2. Core System Responsibilities

The system must:

1. Force decision-making
2. Detect avoidance patterns
3. Protect user dignity
4. Pace cognitive load
5. Preserve long-term trust

Every service and feature must align with these responsibilities.

---

## 3. Scenario Selection Logic (Product-Level)

The system must:

* Rotate themes intentionally
* Avoid repeating the same tension back-to-back
* Re-surface themes where avoidance is detected

### Important Principle

> The system responds to *patterns*, not single decisions.

---

## 4. Pattern Detection Expectations

Patterns should only be surfaced when:

* Enough data exists
* Behavior is consistent
* Signal confidence is high

The system must avoid premature insights.

---

## 5. Courage Enforcement Rules

The system must ensure:

* Courage decay is slow and explainable
* No sudden drops
* No opaque penalties

Courage exists to **end sessions gracefully**, not to control behavior.

---

## 6. Feedback Timing Rules

Feedback should:

* Be immediate but calm
* Prioritize affirmation
* Delay insights until confidence threshold

Avoid interrupting decision flow unnecessarily.

---

## 7. Failure & Edge Case Handling

### Examples:

* User quits mid-session → no penalty
* User returns after days → fresh Courage
* Inconsistent usage → no negative framing

The system must never punish disengagement.

---

## 8. Data Integrity & Trust

The system must:

* Preserve decision history
* Never rewrite past behavior
* Never compare users

Trust depends on consistency.

---

## 9. AI System Guardrails

AI systems must:

* Operate within defined templates
* Never override Courage logic
* Never generate evaluative language

Human intent always supersedes AI autonomy.

---

## 10. Scalability & Future-Proofing

The system should support:

* New themes
* New contexts (e.g., leadership, founders)
* B2B usage

Without changing core philosophy.

---

## 11. Observability Requirements

The system must log:

* Hesitation metrics
* Courage decay triggers
* Pattern confidence levels

These are used for learning, not judgment.

---

## 12. Product Guardrails (Non-Negotiable)

The system must NEVER:

* Rank users
* Score correctness
* Create streak anxiety
* Compare performance socially

Breaking any of these breaks the product.

---

## 13. Summary for Builders & AI Agents

If a system behavior:

* Encourages fear → remove it
* Encourages perfectionism → redesign it
* Encourages ownership → keep it

The system exists to answer one question:

> “Is this helping the user choose?”

---

**End of Part 5 – System Behavior & Technical Expectations**
# PRD – Courage App (Detailed)

## Part 6: Success Metrics, Validation & Risks

---

## 1. Purpose of This Section

This section defines **how success is measured**, **how the hypothesis is validated**, and **what risks must be actively managed**.

For this product, success is **behavioral**, not vanity-based. Traditional app metrics alone (DAU, sessions) are insufficient to judge whether the product is working.

This section exists to prevent the product from being optimized for engagement at the cost of its core mission.

---

## 2. What “Success” Means for This Product

Success does **not** mean:

* Users always choose assertive options
* Users never hesitate
* Users feel confident immediately

Success **does** mean:

* Users hesitate less over time
* Users avoid decisions less frequently
* Users show increased tolerance for discomfort
* Users begin to trust their own judgment

The product succeeds if it **breaks the avoidance loop**.

---

## 3. Core Hypothesis to Validate

> Repeated, low-risk decision-making in realistic scenarios reduces hesitation and avoidance in real life.

This hypothesis must be tested early and continuously.

---

## 4. Primary Success Metrics (Behavioral)

### 4.1 Hesitation Reduction

* Average time-to-decision
* Trend over 7–14 days

**Expected signal:**

> Downward trend without increased avoidance.

---

### 4.2 Avoidance Ratio by Theme

* % of avoidant postures selected per theme
* Trend over time

**Expected signal:**

> Reduced avoidance, especially in repeated themes.

---

### 4.3 Courage Retention

* Courage remaining at session end
* Reduction in early fatigue

**Expected signal:**

> Users sustain Courage longer over time.

---

## 5. Secondary Success Metrics (Product)

* Decisions per session
* Sessions per week
* Day 7 and Day 30 retention
* Voluntary reflections viewed

These metrics support behavioral outcomes but do not replace them.

---

## 6. Qualitative Validation (Critical)

### 6.1 In-App Reflection Prompts

Occasional prompts such as:

* “Did this feel familiar?”
* “Did you hesitate less today?”

Used sparingly to avoid fatigue.

---

### 6.2 User Interviews (Early MVP)

Key questions:

* Do scenarios feel realistic?
* Do you feel judged?
* Does the app make deciding easier?

Qualitative feedback is weighted heavily.

---

## 7. MVP Validation Plan (Concrete)

### MVP Duration

* 7 to 14 days

### Sample Size

* 30–50 users (early professionals)

### MVP Success Criteria

The MVP is considered successful if:

* ≥60% users complete 20+ decisions in 7 days
* ≥50% users show reduced hesitation
* Majority report increased awareness of avoidance

Failure to meet these does **not** mean failure of the idea — it signals iteration.

---

## 8. Risks & Failure Modes

### 8.1 Users Feel Judged

**Risk:**

* Language or feedback feels evaluative

**Mitigation:**

* Strict tone guidelines
* Trade-off framing only

---

### 8.2 Over-Gamification

**Risk:**

* Courage becomes a score
* Users optimize points instead of behavior

**Mitigation:**

* No streaks
* No leaderboards
* No rewards tied to Courage

---

### 8.3 False Confidence

**Risk:**

* Users feel confident but act poorly

**Mitigation:**

* Pattern-based feedback
* Emphasis on trade-offs, not winning

---

### 8.4 Scenario Fatigue

**Risk:**

* Content feels repetitive

**Mitigation:**

* Strong theme rotation
* Story variation
* Periodic new scenario drops

---

## 9. Ethical & Product Guardrails

The product must:

* Never diagnose mental health conditions
* Never pressure users emotionally
* Never shame avoidance

If users feel unsafe, the product has failed.

---

## 10. What Should Never Be Optimized

Never optimize for:

* Maximum sessions per day
* Longest session length
* Competitive performance

Always optimize for:

* Reduced avoidance
* Sustained usage over time
* Trust and safety

---

## 11. Long-Term Indicators of Success

Over months, successful users may:

* Speak up more at work
* Take ownership earlier
* Hesitate less on routine decisions

These are directional indicators, not guarantees.

---

## 12. Final Summary for Builders & AI Agents

This product is successful when:

> Users stop freezing — and start choosing.

All metrics, experiments, and optimizations must serve this outcome.

---

**End of Part 6 – Success Metrics, Validation & Risks**
