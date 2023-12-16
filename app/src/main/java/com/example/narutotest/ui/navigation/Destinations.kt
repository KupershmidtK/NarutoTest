package com.example.narutotest.ui.navigation

import com.example.narutotest.R

interface NavigationDestination {
    val route: String
    val title: Int
}

object HomeScreenDest: NavigationDestination {
    override val route = "home"
    override val title: Int = R.string.app_name
}

object CharactersScreenDest: NavigationDestination {
    override val route = "characters"
    override val title: Int = R.string.char_title
}

object CharactersDetailsScreenDest: NavigationDestination {
    override val route = "chardetail"
    override val title: Int = R.string.char_info
    val charNameArg = "charname"
    val routeWithArgs = "$route/{$charNameArg}"
}

object ClansScreenDest: NavigationDestination {
    override val route = "clans"
    override val title: Int = R.string.clans_title
}

object VillagesScreenDest: NavigationDestination {
    override val route = "villages"
    override val title: Int = R.string.villages_title
}

