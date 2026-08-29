package com.nyxbox.app.util

import com.nyxbox.app.data.AppsRepository
import com.nyxbox.app.data.FakeLocationRepository
import com.nyxbox.app.data.GmsRepository

import com.nyxbox.app.view.apps.AppsFactory
import com.nyxbox.app.view.fake.FakeLocationFactory
import com.nyxbox.app.view.gms.GmsFactory
import com.nyxbox.app.view.list.ListFactory



object InjectionUtil {

    private val appsRepository = AppsRepository()



    private val gmsRepository = GmsRepository()

    private val fakeLocationRepository = FakeLocationRepository()

    fun getAppsFactory() : AppsFactory {
        return AppsFactory(appsRepository)
    }

    fun getListFactory(): ListFactory {
        return ListFactory(appsRepository)
    }


    fun getGmsFactory():GmsFactory{
        return GmsFactory(gmsRepository)
    }

    fun getFakeLocationFactory():FakeLocationFactory{
        return FakeLocationFactory(fakeLocationRepository)
    }
}