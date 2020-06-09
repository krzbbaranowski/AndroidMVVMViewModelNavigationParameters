package krzbb.com.viewmodelnavigationparameters.fragmentA

import androidx.lifecycle.MutableLiveData
import krzbb.com.viewmodelnavigationparameters.NavigationBackListener
import krzbb.com.viewmodelnavigationparameters.NavigationCommand
import krzbb.com.viewmodelnavigationparameters.base.BaseViewModel
import krzbb.com.viewmodelnavigationparameters.common.MyDataNavParam
import kotlin.random.Random

class ViewModelA : BaseViewModel()
{
    val myParameter = MutableLiveData<String>()

    fun navigateToB()
    {
        val customData = "Data: ${Random.nextInt(5, 1000)}"
        val navigationData = MyDataNavParam(customData)

        navigateTo(NavigationCommand.To(FragmentADirections.actionFragmentAToFragmentB(navigationData)))
    }

    override fun onBackNavigationResult(result: Any?)
    {
        if(result is MyDataNavParam)
        {
            myParameter.postValue(result.customData)
        }
    }
}
