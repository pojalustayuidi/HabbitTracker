    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.platform.LocalContext
    import androidx.lifecycle.viewmodel.compose.viewModel
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import com.example.habbittracker.viewmodel.HabitViewModel
    import com.example.habbittracker.viewmodel.HabitViewModelFactory
    import com.example.habbittracker.data.local.HabitDatabase
    import com.example.habbittracker.data.repository.HabitRepository
    import com.example.habbittracker.ui.screens.HabitSection
    import com.example.habbittracker.ui.screens.HomeScreen
    import com.example.habbittracker.ui.screens.WelcomeScreen

    @Composable
    fun CoinHabitApp(modifier: Modifier = Modifier) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "welcome",
            modifier = modifier
        ) {

            composable("welcome") {
                WelcomeScreen(onNavigateToHome = {
                    navController.navigate("habit_section") {
                        popUpTo("welcome") { inclusive = true }
                    }
                })
            }

    composable("habit_section"){
        val context = LocalContext.current
        val database = HabitDatabase.getDatabase(context)
        val repository = HabitRepository(database.habitDao())
        val factory = HabitViewModelFactory(repository)
        val viewModel: HabitViewModel = viewModel(factory = factory)
        HabitSection(
            viewModel = viewModel,
            onNextClick = {
            navController.navigate("home")
        },
        )
    }

            composable("home") {
                HomeScreen()
            }





        }
    }