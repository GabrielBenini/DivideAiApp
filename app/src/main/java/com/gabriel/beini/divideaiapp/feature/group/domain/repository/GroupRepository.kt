package com.gabriel.beini.divideaiapp.feature.group.domain.repository

import com.gabriel.beini.divideaiapp.feature.group.domain.model.Group

interface GroupRepository {

    suspend fun getGroupById(id: Long): Group
}