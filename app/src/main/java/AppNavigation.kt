import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habbittracker.presentation.habits.HabitViewModel
import com.example.habbittracker.data.local.HabitDatabase
import com.example.habbittracker.data.local.SharedPrefsHelper
import com.example.habbittracker.data.local.TokenManager
import com.example.habbittracker.data.repository.AuthRepository
import com.example.habbittracker.data.repository.HabitRepository
import com.example.habbittracker.presentation.auth.AuthViewModel
import com.example.habbittracker.presentation.auth.AuthViewModelFactory
import com.example.habbittracker.presentation.auth.HabitViewModelFactory
import com.example.habbittracker.ui.screens.HabitConfigScreen
import com.example.habbittracker.ui.screens.HabitSection
import com.example.habbittracker.ui.screens.HomeScreen
import com.example.habbittracker.ui.screens.RegisterScreen
// import com.example.habbittracker.ui.screens.WelcomeScreen // TODO: вернуть вместе с блоком welcome ниже

@Composable
fun CoinHabitApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val database = HabitDatabase.getDatabase(context)
    val prefsHelper = SharedPrefsHelper(context)
    val repository = HabitRepository(database.habitDao(), prefsHelper)
    val factory = HabitViewModelFactory(repository)
    val sharedViewModel: HabitViewModel = viewModel(factory = factory)

        //data layer
    val tokenManager = remember { TokenManager(context) }
    val authRepository = remember { AuthRepository(tokenManager) }
    val authFactory = AuthViewModelFactory(authRepository, tokenManager)
    val authViewModel: AuthViewModel = viewModel(factory = authFactory)

    NavHost(
        navController = navController,
        // TODO: вернуть на startDestination = if (sharedViewModel.isOnboardingCompleted()) "home" else "welcome"
        startDestination = "register_screen"
    ) {
        /*
        composable("welcome") {
            WelcomeScreen(onNavigateToHome = {
                navController.navigate("register_screen") {
                    popUpTo("welcome") { inclusive = true }
                }
            })
        }
        */

        composable("register_screen") {
            RegisterScreen(
                viewModel = authViewModel,
                onNextClick = {
                    navController.navigate("habit_section")
                },
            )
        }
        composable("habit_section") {
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
                    sharedViewModel.completeOnboarding()
                    navController.navigate("home") {
                        // TODO: вернуть popUpTo("welcome") вместе с блоком welcome выше
                        popUpTo("register_screen") { inclusive = true }
                    }
                }
            )
        }
        composable("home") {
            HomeScreen(
                viewModel = sharedViewModel,
                onAddHabitClick = {
                    navController.navigate("habit_section") {
                        popUpTo("register_screen") { inclusive = true }
                    }
                }
            )
        }
    }
}