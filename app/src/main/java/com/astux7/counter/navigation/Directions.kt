package com.astux7.counter.navigation

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
            get() = "enterDate"
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }
}