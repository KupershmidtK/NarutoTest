package com.example.narutotest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.narutotest.data.model.NarutoItem
import com.example.narutotest.ui.characters.CharDetailsScreen
import com.example.narutotest.ui.characters.CharListViewModel
import com.example.narutotest.ui.clans.VillageClanScreen
import com.example.narutotest.ui.clans.ClanViewModel
import com.example.narutotest.ui.clans.VillageViewModel
import com.example.narutotest.ui.characters.CharListScreen
import com.example.testdraw.ui.home.HomeScreen

@Composable
fun NarutoNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val charListViewModel: CharListViewModel = hiltViewModel()
    val characters: LazyPagingItems<NarutoItem> = charListViewModel.narutoCharsFlow.collectAsLazyPagingItems()

    val clanViewModel: ClanViewModel = hiltViewModel()
    val clanUiState by clanViewModel.uiState.collectAsState()

    val villageViewModel: VillageViewModel = hiltViewModel()
    val villageUiState by villageViewModel.uiState.collectAsState()

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
                itemList = characters,
                title = stringResource(id = CharactersScreenDest.title),
                navToDetails = { navController.navigate(route = "${CharactersDetailsScreenDest.route}/$it")},
                navBack = { navController.popBackStack() }
            )
        }

        composable(
            route = CharactersDetailsScreenDest.routeWithArgs,
            arguments = listOf(navArgument(CharactersDetailsScreenDest.charNameArg){ type = NavType.StringType })
        ) {
            CharDetailsScreen(
//                navBack = { navController.popBackStack() }
            )
        }

        composable(
            route = ClansScreenDest.route
        ) {
            VillageClanScreen(
                uiState = clanUiState,
                title = ClansScreenDest.title,
                navBack = { navController.popBackStack() }
            )
        }


        composable(
            route = VillagesScreenDest.route
        ) {
            VillageClanScreen(
                uiState = villageUiState,
                title = VillagesScreenDest.title,
                navBack = { navController.popBackStack() }
            )
        }
    }
}