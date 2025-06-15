package com.example.navigation.extesion

import androidx.navigation.NavController


/**
 * Navigates to the specified screen while ensuring that only a single instance
 * of the destination exists in the back stack.
 *
 * This is useful when you want to avoid multiple copies of the same screen being
 * stacked as a result of repeated navigation actions.
 *
 * @param route The route of the destination screen. It should be a serializable
 *              route object or a route string that identifies the target screen.
 *  @author Ali Mansoura
 */
fun  NavController.navigateToScreen(
    route :  Any,
){
    navigate(
        route = route
    ){
        launchSingleTop = true
    }
}

/**
 * Performs navigation within persistent UI components like bottom navigation,
 * drawers, or tabs, ensuring that the back stack is optimized for reuse and state restoration.
 *
 * This function helps prevent building up multiple copies of the same destination
 * and allows preserving and restoring the state of screens.
 *
 * @param route The target screen's route. It can be a @Serializable route object or a string representing the route.
 * @param startDestination The start destination of the graph. This destination will remain on top of the back stack,
 *                         and any intermediate destinations will be cleared up to it.
 *
 * Usage recommendation: Use this method when switching between root-level destinations like tabs
 * or bottom navigation items to maintain a clean back stack and restore state.
 *
 * @author Ali Mansoura
 */
fun NavController.switchToTab(
    route: Any,
    startDestination: Any
){
    navigate(
        route = route
    ){
        popUpTo(startDestination){
            inclusive = false
            saveState = true
        }
        restoreState = true
        launchSingleTop = true
    }
}