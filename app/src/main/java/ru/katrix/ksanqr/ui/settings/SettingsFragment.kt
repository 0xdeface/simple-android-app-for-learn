package ru.katrix.ksanqr.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import ru.katrix.ksanqr.R
import ru.katrix.ksanqr.databinding.FragmentSettingsBinding
import ru.katrix.ksanqr.models.SettingsViewModel
import java.lang.RuntimeException

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private var onSave: SaveSettingsClickListener? = null
    private val viewModel: SettingsViewModel by lazy {
        ViewModelProvider(this).get(SettingsViewModel::class.java)
    }

    companion object {
        const val branch = "branch";
        const val key = "settings_fragment";
        const val seller = "seller";
        const val token = "token";
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SaveSettingsClickListener) {
            onSave = context
        } else {
            throw RuntimeException("must implement SaveSettingsListener")
        }
    }

    override fun onCreateView(inf: LayoutInflater, cont: ViewGroup?, savedState: Bundle?): View? {
        _binding = FragmentSettingsBinding.inflate(inf, cont, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addSaveBtnClick()
        val branchArrayAdapter = ArrayAdapter<String>(
            requireActivity(),
            R.layout.spinner_item,
            listOf()
        )
        viewModel.getBranches().observe(viewLifecycleOwner) {
            branchArrayAdapter.clear()
            branchArrayAdapter.addAll(it)
            branchArrayAdapter.notifyDataSetChanged()
        }

        binding.branch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                binding.branch.setSelection(p2)
                branchArrayAdapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        binding.branch.adapter = branchArrayAdapter
    }

    interface SaveSettingsClickListener {
        fun onSaveSettingsClicked(token: String, seller: String, branch: String)
    }

    private fun addSaveBtnClick() {
        binding.settingSaveBtn.setOnClickListener {
            onSave?.onSaveSettingsClicked("1", "2", "3")
            setFragmentResult(
                key, bundleOf(
                    token to "dfsf",
                    seller to "binding.seller",
                    branch to "binding.branch"
                )
            )


        }
    }

}