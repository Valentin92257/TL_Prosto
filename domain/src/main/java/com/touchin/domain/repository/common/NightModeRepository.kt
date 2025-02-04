package com.touchin.domain.repository.common

import io.reactivex.Observable

interface NightModeRepository {

    var nightMode: Int


    fun toggleNightMode()

    fun observeNightModeChanges(): Observable<Int>

}
