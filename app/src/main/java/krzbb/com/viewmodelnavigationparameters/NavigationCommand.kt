package krzbb.com.viewmodelnavigationparameters

import androidx.navigation.NavDirections

sealed class NavigationCommand
{
    data class To(val directions: NavDirections) : NavigationCommand()
    data class Back(val backArgs: Any?) : NavigationCommand()
}