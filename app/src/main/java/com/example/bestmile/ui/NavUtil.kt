package com.example.bestmile.ui

import androidx.navigation.NavController
import androidx.navigation.NavDirections

//https://stackoverflow.com/questions/63805655/navigation-component-kotlin-cannot-be-found-from-the-current-destination
fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
}