package pl.edu.pja.kdudek.pja_kom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import pl.edu.pja.kdudek.pja_kom.ui.theme.PjakomTheme
import pl.edu.pja.kdudek.pja_kom.ui.view.CartScreen
import pl.edu.pja.kdudek.pja_kom.ui.view.DetailsScreen
import pl.edu.pja.kdudek.pja_kom.ui.view.ShoppingListScreen
import pl.edu.pja.kdudek.pja_kom.ui.viewmodel.CartViewModel
import pl.edu.pja.kdudek.pja_kom.ui.viewmodel.EditViewModel
import pl.edu.pja.kdudek.pja_kom.ui.viewmodel.ListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val cartViewModel: CartViewModel by viewModels()
    val editViewModel: EditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PjakomTheme {
                Home(
                    cartViewModel = cartViewModel,
                    editViewModel = editViewModel
                )
            }
        }
    }
}

object Destinations {
    const val ARG_ID = "id"
    const val LIST_DESTINATION = "list"
    const val DETAILS_DESTINATION = "details/{$ARG_ID}"
    const val CART_DESTINATION = "cart"

    fun getRouteForDetails(id: Int): String {
        return DETAILS_DESTINATION.replace("{$ARG_ID}", id.toString())
    }
}

@Composable
private fun Home(
    cartViewModel: CartViewModel,
    editViewModel: EditViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.LIST_DESTINATION,
    ) {
        composable(Destinations.LIST_DESTINATION) {
            val viewModel: ListViewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                viewModel.load()
            }
            val products by viewModel.state.collectAsStateWithLifecycle()
            ShoppingListScreen(
                products = products,
                onProductClicked = {
                    navController.navigate(
                        Destinations.getRouteForDetails(
                            it
                        )
                    )
                },
                onCartClicked = { navController.navigate(Destinations.CART_DESTINATION) }
            )
        }
        composable(Destinations.CART_DESTINATION) {
            val viewModel: CartViewModel = cartViewModel
            LaunchedEffect(Unit) {
                viewModel.load()
            }

            val state by viewModel.state.collectAsStateWithLifecycle()

            CartScreen(
                cartUiState = state,
                onCartClear = viewModel::onCartClear,
                onAdd = viewModel::onAdd,
                onRemove = viewModel::onRemove,
                onDelete = viewModel::onDelete
            )
        }
        composable(
            Destinations.DETAILS_DESTINATION,
            arguments = listOf(
                navArgument(Destinations.ARG_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(Destinations.ARG_ID) ?: -1
            val viewModel: EditViewModel = editViewModel
            LaunchedEffect(id) {
                viewModel.load(id)
            }
            val product by viewModel.state.collectAsStateWithLifecycle()
            product?.let { product ->
                DetailsScreen(
                    product = product,
                    onNavigationUp = navController::popBackStack,
                    onNavigationEdit = { /* TODO */ },
                    onAddToCart = viewModel::addToCart
                )
            }
        }
    }
}
