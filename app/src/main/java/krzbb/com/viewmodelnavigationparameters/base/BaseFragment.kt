package krzbb.com.viewmodelnavigationparameters.base

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import krzbb.com.viewmodelnavigationparameters.NavigationBackListener
import krzbb.com.viewmodelnavigationparameters.NavigationCommand
import krzbb.com.viewmodelnavigationparameters.R

open class BaseFragment<T : BaseViewModel>(private val vmType: Class<T>) : Fragment(),
        NavigationBackListener
{
    protected lateinit var viewModel: T
    private val navController by lazy {
        requireActivity().findNavController(R.id.nav_host_fragment_container)
    }

    private var backCallback: OnBackPressedCallback? = null

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel()
    {
        viewModel = ViewModelProvider(this).get(vmType)
        this.viewModel.prepare(arguments)

        this.viewModel.navigationCommands.observe(viewLifecycleOwner, Observer { command ->
            resolveNavigationCommand(command)
        })

        backCallback =
                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, true) {
                    viewModel.onBackAction()

                    val data = viewModel.getOnBackNavData()
                    resolveNavigationCommand(NavigationCommand.Back(data))
                }
    }

    private fun resolveNavigationCommand(command: NavigationCommand)
    {
        when (command)
        {
            is NavigationCommand.To ->
            {
                navController.navigate(command.directions)
            }

            is NavigationCommand.Back ->
            {
                if (command.backArgs == null)
                    navController.popBackStack()
                else
                {
                    (requireActivity() as NavigationBackListener).onNavigationResult(command.backArgs)
                }
            }
        }
    }


    override fun onNavigationResult(result: Any?)
    {
        this.viewModel.onBackNavigationResult(result)
    }

    override fun onDestroyView()
    {
        super.onDestroyView()

        backCallback?.remove()
        backCallback = null
    }
}



