import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                navController.navigate("home") {
                    popUpTo("welcome") { inclusive = true }
                }
            })
        }

        composable("home") {
            HomeScreen()
        }
    }
}