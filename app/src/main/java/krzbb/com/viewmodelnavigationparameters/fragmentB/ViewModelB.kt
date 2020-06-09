package krzbb.com.viewmodelnavigationparameters.fragmentB

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import krzbb.com.viewmodelnavigationparameters.NavigationCommand
import krzbb.com.viewmodelnavigationparameters.base.BaseViewModel
import krzbb.com.viewmodelnavigationparameters.common.MyDataNavParam
import kotlin.random.Random

class ViewModelB : BaseViewModel()
{
    var backData = "Fragment B result ${Random.nextInt(5, 1000)}"

    val myParameter = MutableLiveData<String>()

    override fun prepare(args: Bundle?)
    {
        args?.let {
            val navigationParam = FragmentBArgs.fromBundle(it).data
            myParameter.postValue(navigationParam.customData)
        }
    }

    fun onDemandBack() {
        navigateTo(NavigationCommand.Back(getOnBackNavData()))
    }

    override fun getOnBackNavData(): Any? = MyDataNavParam(backData)
}
