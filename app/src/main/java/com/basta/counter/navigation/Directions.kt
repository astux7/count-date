package com.basta.counter.navigation

import androidx.navigation.NamedNavArgument

interface NavigationCommand {
    val name: String
    val arguments: List<NamedNavArgument>
}

object Directions {
    val home = object : NavigationCommand {
        override val name: String
            get() = "home"
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }

    val enterDate = object : NavigationCommand {
        override val name: String
            get() = "enter_date"
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }
}