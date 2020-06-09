package krzbb.com.viewmodelnavigationparameters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), NavigationBackListener
{
    val navController: NavController by lazy { findNavController(R.id.nav_host_fragment_container) }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onNavigationResult(result: Any?)
    {
        val childFragmentManager = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)?.childFragmentManager

        var backStackListener: FragmentManager.OnBackStackChangedListener by Delegates.notNull()

        backStackListener = FragmentManager.OnBackStackChangedListener {
            (childFragmentManager?.fragments?.get(0) as NavigationBackListener).onNavigationResult(result)

            childFragmentManager.removeOnBackStackChangedListener(backStackListener)
        }

        childFragmentManager?.addOnBackStackChangedListener(backStackListener)
        navController.popBackStack()
    }
}

