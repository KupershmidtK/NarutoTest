package com.example.narutotest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.narutotest.ui.characters.CharDetailsScreen
import com.example.narutotest.ui.characters.CharListScreen
import com.example.narutotest.ui.viewmodel.ClanViewModel
import com.example.narutotest.ui.clans.VillageClanScreen
import com.example.narutotest.ui.viewmodel.AppViewModelProvider
import com.example.narutotest.ui.viewmodel.VillageViewModel
import com.example.narutotest.ui.home.HomeScreen

@Composable
fun NarutoNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
//    val charViewModel: CharListViewModel = hiltViewModel()

    val clanViewModel: ClanViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val clanUiState = clanViewModel.uiState
    val clanUiSearchState = clanViewModel.uiSearchState.collectAsState()

    val villageViewModel: VillageViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val villageUiState = villageViewModel.uiState
    val villageUiSearchState = villageViewModel.uiSearchState.collectAsState()

    NavHost (
        navController = navController,
        startDestination = HomeScreenDest.route,
        modifier = modifier
    ){
        composable(route = HomeScreenDest.route) {
            HomeScreen(
                charactersOnClick = { navController.navigate(route = CharactersScreenDest.route) },
                clansOnClick = { navController.navigate(route = ClansScreenDest.route) },
                villagesOnClick = { navController.navigate(route = VillagesScreenDest.route) }
            )
        }

        composable(
            route = CharactersScreenDest.route
            ) {
            CharListScreen(
                title = stringResource(id = CharactersScreenDest.title),
                navToDetails = { navController.navigate(route = "${CharactersDetailsScreenDest.route}/$it")},
                navBack = { navController.popBackStack() },
            )
        }

        composable(
            route = CharactersDetailsScreenDest.routeWithArgs,
            arguments = listOf(navArgument(CharactersDetailsScreenDest.charNameArg){ type = NavType.StringType })
        ) {
            CharDetailsScreen(
                navBack = { navController.popBackStack() },
            )
        }

        composable(route = ClansScreenDest.route) {
            VillageClanScreen(
                uiState = clanUiState,
                uiSearchState = clanUiSearchState,
                title = ClansScreenDest.title,
                searchStringChange = clanViewModel::setSearchText,
                navBack = { navController.popBackStack() }
            )
        }

        composable(route = VillagesScreenDest.route) {
            VillageClanScreen(
                uiState = villageUiState,
                uiSearchState = villageUiSearchState,
                title = VillagesScreenDest.title,
                searchStringChange = villageViewModel::setSearchText,
                navBack = { navController.popBackStack() }
            )
        }
    }
}