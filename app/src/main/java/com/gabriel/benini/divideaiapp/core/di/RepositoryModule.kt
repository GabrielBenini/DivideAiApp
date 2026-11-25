package com.gabriel.benini.divideaiapp.core.di

import com.gabriel.benini.divideaiapp.feature.expense.data.repository.ExpenseRepositoryImpl
import com.gabriel.benini.divideaiapp.feature.expense.domain.repository.ExpenseRepository
import com.gabriel.benini.divideaiapp.feature.group.data.repository.GroupRepositoryImpl
import com.gabriel.benini.divideaiapp.feature.group.domain.repository.GroupRepository
import com.gabriel.benini.divideaiapp.feature.settlement.data.repository.SettlementRepositoryImpl
import com.gabriel.benini.divideaiapp.feature.settlement.domain.repository.SettlementRepository
import com.gabriel.benini.divideaiapp.feature.user.data.repository.UserRepositoryImpl
import com.gabriel.benini.divideaiapp.feature.user.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindGroupRepository(groupRepositoryImpl: GroupRepositoryImpl): GroupRepository

    @Binds
    abstract fun bindExpenseRepository(expenseRepositoryImpl: ExpenseRepositoryImpl): ExpenseRepository

    @Binds
    abstract fun bindSettlementRepository(settlementRepositoryImpl: SettlementRepositoryImpl): SettlementRepository
}