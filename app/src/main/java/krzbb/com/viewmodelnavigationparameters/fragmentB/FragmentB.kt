package krzbb.com.viewmodelnavigationparameters.fragmentB

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_b.*
import krzbb.com.viewmodelnavigationparameters.base.BaseFragment
import krzbb.com.viewmodelnavigationparameters.R

class FragmentB : BaseFragment<ViewModelB>(ViewModelB::class.java)
{
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?
    {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        viewModel.myParameter.observe(viewLifecycleOwner, Observer {
            argument_value.text = it
        })

        bck.setOnClickListener {
            viewModel.onDemandBack()
        }
    }
}
