package com.example.narutotest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.narutotest.ui.characters.NarutoCharDetailsScreen
import com.example.narutotest.ui.characters.NarutoCharsScreen
import com.example.testdraw.ui.home.HomeScreen

@Composable
fun NarutoNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost (
        navController = navController,
        startDestination = HomeScreenDest.route,
        modifier = modifier
    ){
        composable(route = HomeScreenDest.route) {
            HomeScreen(
                charactersOnClick = { navController.navigate(route = CharactersScreenDest.route) }
            )
        }
        composable(
            route = CharactersScreenDest.route
            ) {
            NarutoCharsScreen(
                navToDetails = { navController.navigate(route = "${CharactersDetailsScreenDest.route}/$it")}
            )
        }
        composable(
            route = CharactersDetailsScreenDest.routeWithArgs,
            arguments = listOf(navArgument(CharactersDetailsScreenDest.charNameArg){ type = NavType.StringType })
        ) {
            NarutoCharDetailsScreen()
        }
    }
}