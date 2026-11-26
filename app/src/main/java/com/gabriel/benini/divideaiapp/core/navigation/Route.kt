package com.gabriel.benini.divideaiapp.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route{

    @Serializable
    data object Splash: Route
    @Serializable
    data object Welcome: Route
    @Serializable
    data object Home: Route
    @Serializable
    data object Profile: Route
    @Serializable
    data object CreateGroup: Route
    @Serializable
    data class GroupDetails(val groupId: Long): Route
    @Serializable
    data class Members(val groupId: Long): Route
    @Serializable
    data class Expenses(val groupId: Long): Route
    @Serializable
    data class AddExpense(val groupId: Long): Route
    @Serializable
    data class EditExpense(val expenseId: Long): Route
    @Serializable
    data class Settlement(val groupId: Long): Route
}