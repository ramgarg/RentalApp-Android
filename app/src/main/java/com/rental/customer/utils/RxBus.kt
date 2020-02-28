package com.rental.customer.utils

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.util.function.Consumer


object RxBus {

    private val sSubject = PublishSubject.create<Any>()

    private fun RxBus() { // hidden constructor
    }


    fun subscribe(action: Consumer<Any?>): Disposable? {
        return sSubject.subscribe()
    }

    fun publish(message: Any) {
        sSubject.onNext(message)
    }

}


