package com.courage.app.ui.screens.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.courage.app.ui.components.DropdownField

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel,
    onComplete: () -> Unit,
) {
    val state = viewModel.state.collectAsState().value

    OnboardingScreenContent(
        state = state,
        onAgeRange = viewModel::updateAgeRange,
        onOccupation = viewModel::updateOccupation,
        onRole = viewModel::updateRole,
        onExperience = viewModel::updateExperience,
        onPreferredContext = viewModel::updatePreferredContext,
        onContinue = { viewModel.submit(onComplete) },
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun OnboardingScreenContent(
    state: OnboardingViewModel.UiState,
    onAgeRange: (String) -> Unit,
    onOccupation: (String) -> Unit,
    onRole: (String) -> Unit,
    onExperience: (String) -> Unit,
    onPreferredContext: (String) -> Unit,
    onContinue: () -> Unit,
) {
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
                title = { Text("Setup", style = MaterialTheme.typography.titleMedium) },
            )
        },
        bottomBar = {
            Surface(color = MaterialTheme.colorScheme.surface, tonalElevation = 2.dp) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .windowInsetsPadding(WindowInsets.navigationBars)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                ) {
                    FilledTonalButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onContinue,
                        enabled = !state.isSaving,
                        shape = MaterialTheme.shapes.large,
                        colors = ButtonDefaults.filledTonalButtonColors(),
                    ) {
                        Text(if (state.isSaving) "Saving…" else "Continue")
                    }
                }
            }
        },
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text("A quick setup", style = MaterialTheme.typography.headlineMedium)
            Text(
                "This only helps scenarios feel more relevant. There are no scores, and there’s no ‘right’ answer here.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    DropdownField(
                        label = "Age range",
                        value = state.ageRange,
                        options = AgeRangeOptions,
                        onValueChange = onAgeRange,
                    )
                    DropdownField(
                        label = "Occupation (industry)",
                        value = state.occupation,
                        options = OccupationIndustryOptions,
                        onValueChange = onOccupation,
                    )
                    DropdownField(
                        label = "Role",
                        value = state.role,
                        options = RoleOptions,
                        onValueChange = onRole,
                    )
                    DropdownField(
                        label = "Experience level",
                        value = state.experienceLevel,
                        options = ExperienceOptions,
                        onValueChange = onExperience,
                    )
                    DropdownField(
                        label = "Preferred context",
                        value = state.preferredContext,
                        options = PreferredContextOptions,
                        onValueChange = onPreferredContext,
                    )

                    if (state.error != null) {
                        Text(state.error, color = MaterialTheme.colorScheme.error)
                    }
                }
            }

            // space so the last field isn't glued to the bottom bar
            Spacer(modifier = Modifier.padding(bottom = 24.dp))
        }
    }
}

private val AgeRangeOptions = listOf(
    "18–24",
    "25–34",
    "35–44",
    "45–54",
    "55+",
)

private val OccupationIndustryOptions = listOf(
    "Technology / Software",
    "Consulting / Professional Services",
    "Finance / Banking / Insurance",
    "Healthcare / Pharma",
    "Retail / E-commerce",
    "Consumer / CPG",
    "Manufacturing / Industrial",
    "Logistics / Supply Chain",
    "Energy / Utilities",
    "Media / Entertainment",
    "Telecommunications",
    "Real Estate / Construction",
    "Education",
    "Government / Public Sector",
    "Non-profit",
    "Travel / Hospitality",
    "Legal",
    "Other",
)

private val RoleOptions = listOf(
    // PRD examples
    "Individual Contributor (IC)",
    "Manager (People Manager)",
    "Student",
    // Expanded role suggestions (as requested)
    "Product Manager",
    "Consultant",
    "Engineer",
    "Tester / QA",
    "Designer",
    "Data Analyst",
    "Data Scientist",
    "Marketing",
    "Sales / Business Development",
    "Customer Success",
    "HR / Recruiting",
    "Operations",
    "Founder / Owner",
    "Other",
)

private val ExperienceOptions = listOf(
    "0–1 years",
    "1–3 years",
    "3–5 years",
    "5–10 years",
    "10+ years",
)

private val PreferredContextOptions = listOf(
    "Office",
    "Personal",
)


