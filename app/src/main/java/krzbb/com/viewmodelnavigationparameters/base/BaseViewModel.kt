package krzbb.com.viewmodelnavigationparameters.base

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import krzbb.com.viewmodelnavigationparameters.NavigationCommand
import krzbb.com.viewmodelnavigationparameters.SingleLiveEvent

open class BaseViewModel : ViewModel()
{
    val navigationCommands = SingleLiveEvent<NavigationCommand>()

    open fun prepare(args: Bundle?)
    {
        Log.w("App", "prepare: ${this.javaClass.simpleName}")
    }

    fun navigateTo(directions: NavigationCommand)
    {
        navigationCommands.postValue(directions)
    }

    open fun onBackNavigationResult(result: Any?)
    {
        Log.w("App", "onNavigationResult: ${this.javaClass.simpleName}")
    }

    fun onBackAction() {
        Log.w("App", "onBackAction: ${this.javaClass.simpleName}")
    }

    open fun getOnBackNavData(): Any? = null
}