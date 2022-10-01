package com.intern.evtutors.ui.customer.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intern.evtutors.base.viewmodel.BaseViewModel
import com.intern.evtutors.data.models.Account
import com.intern.evtutors.data.models.getjwtToken
import com.intern.evtutors.data.repositories.JsonLoginRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RegisterViewModel @Inject constructor(private val jsonLoginRepositories: JsonLoginRepositories)
    : BaseViewModel() {

    private var _listPosts = MutableLiveData<getjwtToken>()
    val listPots: LiveData<getjwtToken>
        get() = _listPosts

    override fun fetchDataLogin(email:String,passWord: String) {
        showLoading(true)
        var account = Account()
        account.userName=email
        account.userPassword=passWord
        parentJob = viewModelScope.launch(handler) {
            val post = jsonLoginRepositories.getAllAccount(account)
            _listPosts.postValue(post)

        }
        registerJobFinish()
    }
}