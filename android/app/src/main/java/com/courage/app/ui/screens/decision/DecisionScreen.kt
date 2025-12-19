package com.courage.app.ui.screens.decision

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.courage.app.domain.model.ChoiceSide
import com.courage.app.ui.components.CouragePill
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DecisionScreen(
    viewModel: DecisionViewModel,
    onNavigateToFeedback: (Long) -> Unit,
    onNavigateToSessionEnd: () -> Unit,
    onNavigateToSessionSummary: () -> Unit = {},
) {
    val state = viewModel.state.collectAsState().value
    DecisionScreenContent(
        state = state,
        onRetry = { viewModel.loadScenario() },
        onSwipe = { side ->
            viewModel.onChoice(
                side = side,
                onNavigateToFeedback = onNavigateToFeedback,
                onNavigateToSessionEnd = onNavigateToSessionEnd,
                onNavigateToSessionSummary = onNavigateToSessionSummary,
            )
        },
        onNavigateToSessionSummary = onNavigateToSessionSummary,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DecisionScreenContent(
    state: DecisionViewModel.UiState,
    onRetry: () -> Unit,
    onSwipe: (ChoiceSide) -> Unit,
    onNavigateToSessionSummary: () -> Unit,
) {
    val scenario = state.scenario
    val scope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }

    LaunchedEffect(state.sessionCompleted) {
        if (state.sessionCompleted) onNavigateToSessionSummary()
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                scrollBehavior = scrollBehavior,
                title = {
                    CouragePill(current = state.courage)
                },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            when {
                state.isLoading -> {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Loading…",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

                state.error != null -> {
                    Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(state.error, color = MaterialTheme.colorScheme.error)
                        Spacer(Modifier.size(12.dp))
                        Text(
                            text = "Tap to retry",
                            modifier = Modifier.clickable { onRetry() },
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }

                state.sessionCompleted -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(
                            text = "That’s enough for today.",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Text(
                            text = "You’ve completed 20 decisions in this session.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        Text(
                            text = "Tap to view summary",
                            modifier = Modifier.clickable { onNavigateToSessionSummary() },
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }

                scenario != null -> {
                    Card(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth()
                            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                            .pointerInput(scenario.id) {
                                detectDragGestures(
                                    onDrag = { change, dragAmount ->
                                        change.consume()
                                        scope.launch {
                                            offsetX.snapTo(offsetX.value + dragAmount.x)
                                        }
                                    },
                                    onDragEnd = {
                                        val thresholdPx = 140f
                                        val current = offsetX.value
                                        if (abs(current) >= thresholdPx) {
                                            val side = if (current < 0) ChoiceSide.Left else ChoiceSide.Right
                                            scope.launch {
                                                offsetX.animateTo(
                                                    targetValue = if (side == ChoiceSide.Left) -1200f else 1200f,
                                                    animationSpec = tween(durationMillis = 180),
                                                )
                                                offsetX.snapTo(0f)
                                            }
                                            onSwipe(side)
                                        } else {
                                            scope.launch {
                                                offsetX.animateTo(0f, animationSpec = tween(durationMillis = 160))
                                            }
                                        }
                                    },
                                )
                            }
                            .padding(horizontal = 6.dp),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                    ) {
                        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                            Text(
                                text = "Scenario",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            Text(
                                text = scenario.scenarioText,
                                style = MaterialTheme.typography.headlineSmall,
                            )

                            Spacer(modifier = Modifier.size(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                            ) {
                                Surface(
                                    modifier = Modifier.weight(1f),
                                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                                    contentColor = MaterialTheme.colorScheme.onSurface,
                                    shape = MaterialTheme.shapes.large,
                                ) {
                                    Column(
                                        modifier = Modifier.padding(14.dp),
                                        verticalArrangement = Arrangement.spacedBy(6.dp),
                                    ) {
                                        Text(
                                            text = "Swipe left",
                                            style = MaterialTheme.typography.labelLarge,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        )
                                        Text(
                                            text = scenario.optionLeftText,
                                            style = MaterialTheme.typography.bodyMedium,
                                        )
                                    }
                                }

                                Surface(
                                    modifier = Modifier.weight(1f),
                                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                                    contentColor = MaterialTheme.colorScheme.onSurface,
                                    shape = MaterialTheme.shapes.large,
                                ) {
                                    Column(
                                        modifier = Modifier.padding(14.dp),
                                        verticalArrangement = Arrangement.spacedBy(6.dp),
                                    ) {
                                        Text(
                                            text = "Swipe right",
                                            style = MaterialTheme.typography.labelLarge,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        )
                                        Text(
                                            text = scenario.optionRightText,
                                            style = MaterialTheme.typography.bodyMedium,
                                        )
                                    }
                                }
                            }

                            Text(
                                text = "Drag the card left or right to choose.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }
            }
        }
    }
}


