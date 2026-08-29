package com.nyxbox.app.view.list

import androidx.lifecycle.MutableLiveData
import com.nyxbox.app.bean.InstalledAppBean
import com.nyxbox.app.data.AppsRepository
import com.nyxbox.app.view.base.BaseViewModel


class ListViewModel(private val repo: AppsRepository) : BaseViewModel() {

    val appsLiveData = MutableLiveData<List<InstalledAppBean>>()

    val loadingLiveData = MutableLiveData<Boolean>()

    fun previewInstalledList() {
        launchOnUI { repo.previewInstallList() }
    }

    fun getInstallAppList(userID: Int) {
        launchOnUI { repo.getInstalledAppList(userID, loadingLiveData, appsLiveData) }
    }
}
