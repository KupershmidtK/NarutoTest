package com.example.narutotest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.narutotest.data.model.NarutoItem
import com.example.narutotest.ui.characters.CharacterDetailsScreen
import com.example.narutotest.ui.characters.CharacterViewModel
import com.example.narutotest.ui.clans.ClansScreen
import com.example.narutotest.ui.clans.ClansViewModel
import com.example.narutotest.ui.clans.VillagesViewModel
import com.example.narutotest.ui.components.ItemListScreen
import com.example.testdraw.ui.home.HomeScreen

@Composable
fun NarutoNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val charViewModel: CharacterViewModel = hiltViewModel()
    val characters: LazyPagingItems<NarutoItem> = charViewModel.narutoCharsFlow.collectAsLazyPagingItems()

    val clansViewModel: ClansViewModel = hiltViewModel()
    val clanUiState by clansViewModel.uiState.collectAsState()

    val villagesViewModel: VillagesViewModel = hiltViewModel()
    val villageUiState by villagesViewModel.uiState.collectAsState()

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
            ItemListScreen(
                itemList = characters,
                title = stringResource(id = CharactersScreenDest.title),
                navToDetails = { navController.navigate(route = "${CharactersDetailsScreenDest.route}/$it")},
                navToClanList = { navController.navigate(route = ClansScreenDest.route )},
                navBack = { navController.popBackStack() }
            )
        }

        composable(
            route = CharactersDetailsScreenDest.routeWithArgs,
            arguments = listOf(navArgument(CharactersDetailsScreenDest.charNameArg){ type = NavType.StringType })
        ) {
            CharacterDetailsScreen(
//                navBack = { navController.popBackStack() }
            )
        }

        composable(
            route = ClansScreenDest.route
        ) {
            ClansScreen(
                uiState = clanUiState,
                title = ClansScreenDest.title,
                navBack = { navController.popBackStack() }
            )
        }


        composable(
            route = VillagesScreenDest.route
        ) {
            ClansScreen(
                uiState = villageUiState,
                title = VillagesScreenDest.title,
                navBack = { navController.popBackStack() }
            )
        }
    }
}