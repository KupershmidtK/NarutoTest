package com.example.narutotest.ui.navigation

import com.example.narutotest.ui.navigation.CharactersScreenDest.pageNumbArg

interface NavigationDestination {
    val route: String
}

object HomeScreenDest: NavigationDestination {
    override val route = "home"
}

object CharactersScreenDest: NavigationDestination {
    override val route = "characters"
    val pageNumbArg = "pageNumb"
    val routeWithArgs = "$route/{$pageNumbArg}"
}

object CharactersDetailsScreenDest: NavigationDestination {
    override val route = "chardetail"
    val charNameArg = "charname"
    val routeWithArgs = "$route/{$charNameArg}"
}

