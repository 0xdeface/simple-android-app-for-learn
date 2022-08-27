package ru.katrix.ksanqr.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel() : ViewModel() {
    var branches: MutableLiveData<List<String>> = MutableLiveData()
    var sellers: MutableLiveData<List<String>> = MutableLiveData()
    var selectedBranch = ""
    var selectedSeller = ""

    init {
        branches.value = listOf<String>("не выбрано")
        sellers.value = listOf<String>("не выбрано")
    }

    fun getBranches(): LiveData<List<String>> {
        return branches
    }

    fun getSellers(): LiveData<List<String>> {
        return sellers
    }
}