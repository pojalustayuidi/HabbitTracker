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
    import com.example.habbittracker.data.local.SharedPrefsHelper
    import com.example.habbittracker.data.repository.HabitRepository
    import com.example.habbittracker.ui.screens.HabitConfigScreen
    import com.example.habbittracker.ui.screens.HabitSection
    import com.example.habbittracker.ui.screens.HomeScreen
    import com.example.habbittracker.ui.screens.WelcomeScreen

    @Composable
    fun CoinHabitApp(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        val context = LocalContext.current
        val database = HabitDatabase.getDatabase(context)
        val prefsHelper = SharedPrefsHelper(context)
        val repository = HabitRepository(database.habitDao(), prefsHelper)
        val factory = HabitViewModelFactory(repository)
        val sharedViewModel: HabitViewModel = viewModel(factory = factory)
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
        HabitSection(
            viewModel = sharedViewModel,
            onNextClick = {
            navController.navigate("habit_config")
        },
        )
    }
            composable("habit_config") {
                HabitConfigScreen(
                    viewModel = sharedViewModel,
                    onFinishClick = {
                        navController.navigate("home") {
                            popUpTo("welcome") { inclusive = true }
                        }
                    }
                )
            }

            composable("home") {
                HomeScreen(viewModel = sharedViewModel, onAddHabitClick = {navController.navigate("habit_section") {
                    popUpTo("welcome") { inclusive = true }
                }})
            }





        }
    }