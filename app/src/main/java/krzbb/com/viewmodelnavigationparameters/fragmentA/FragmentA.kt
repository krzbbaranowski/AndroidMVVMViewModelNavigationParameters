package krzbb.com.viewmodelnavigationparameters.fragmentA

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.fragment_a.argument_value
import kotlinx.android.synthetic.main.fragment_b.*
import krzbb.com.viewmodelnavigationparameters.base.BaseFragment
import krzbb.com.viewmodelnavigationparameters.R


class FragmentA : BaseFragment<ViewModelA>(ViewModelA::class.java)
{
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?
    {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        btn?.setOnClickListener {
            viewModel.navigateToB()
        }

        viewModel.myParameter.observe(viewLifecycleOwner, Observer {
            argument_value.text = it
        })
    }
}
