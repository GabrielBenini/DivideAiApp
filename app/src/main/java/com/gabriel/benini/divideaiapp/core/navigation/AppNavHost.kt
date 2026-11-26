package com.gabriel.benini.divideaiapp.core.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.gabriel.benini.divideaiapp.core.session.presentation.viewmodel.SessionViewModel
import com.gabriel.benini.divideaiapp.feature.expense.presentation.screen.ExpensesScreen
import com.gabriel.benini.divideaiapp.feature.group.presentation.screen.GroupDetailsScreen
import com.gabriel.benini.divideaiapp.feature.home.presentation.screen.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    sessionViewModel: SessionViewModel = hiltViewModel(),
) {

    val sessionState by sessionViewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Route.Splash
    ) {
        composable<Route.Splash> {
            LaunchedEffect(sessionState) {
                when {
                    sessionState.isLoading -> {

                    }

                    sessionState.errorMessage != null -> {

                    }

                    sessionState.userId == null -> {
                        navController.navigate(Route.Welcome) {
                            popUpTo<Route.Splash> {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }

                    else -> {
                        navController.navigate(Route.Home) {
                            popUpTo<Route.Splash> {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }

                }
            }
            Text(sessionState.errorMessage ?: "Splash Screen")
        }

        composable<Route.Welcome> {
            Text("Welcome Screen")
        }

        composable<Route.Home> {
            HomeScreen(
                onNavigateToProfile = { navController.navigate(Route.Profile) },
                onNavigateToCreateGroup = { navController.navigate(Route.CreateGroup) },
                onNavigateToGroupDetails = { groupId ->
                    navController.navigate(Route.GroupDetails(groupId))
                }
            )
        }

        composable<Route.Profile> {
            Text("Profile Screen")
        }

        composable<Route.CreateGroup> {
            Text("Create Group Screen")
        }

        composable<Route.GroupDetails> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.GroupDetails>()
            val groupId = route.groupId

            GroupDetailsScreen(
                groupId = groupId,
                onNavigateToMembers = { id ->
                    navController.navigate(Route.Members(id))
                },
                onNavigateToExpenses = { id ->
                    navController.navigate(Route.Expenses(id))
                },
                onNavigateToAddExpenses = { id ->
                    navController.navigate(Route.AddExpense(id))
                },
                onNavigateToSettlement = { id ->
                    navController.navigate(Route.Settlement(id))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<Route.Members> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.Members>()
            val groupId = route.groupId

            Text("Members Screen - ID $groupId")
        }

        composable<Route.Expenses> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.Expenses>()
            val groupId = route.groupId

            ExpensesScreen(
                groupId = groupId,
                onNavigateToEditExpense = { expenseId ->
                    navController.navigate(Route.EditExpense(expenseId))
                }
            )
        }

        composable<Route.AddExpense> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.AddExpense>()
            val groupId = route.groupId
            Text("AddExpense Screen - ID $groupId")
        }

        composable<Route.EditExpense> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.EditExpense>()
            val expenseId = route.expenseId
            Text("EditExpense Screen - ID $expenseId")
        }

        composable<Route.Settlement> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.Settlement>()
            val groupId = route.groupId
            Text("Settlement Screen - ID $groupId")
        }


    }
}