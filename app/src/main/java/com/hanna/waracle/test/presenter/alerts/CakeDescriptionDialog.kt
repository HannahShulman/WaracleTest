package com.hanna.waracle.test.presenter.alerts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hanna.waracle.test.R
import com.hanna.waracle.test.databinding.CakeDescriptionDialogLayoutBinding
import com.hanna.waracle.test.utils.ui.viewBinding

class CakeDescriptionDialog : BottomSheetDialogFragment() {

    private val binding: CakeDescriptionDialogLayoutBinding by viewBinding(
        CakeDescriptionDialogLayoutBinding::bind
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cake_description_dialog_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cakeDescription.text =
            arguments?.getString(DESCRIPTION) ?: "No description found"
    }

    companion object {
        const val DESCRIPTION = "description"

        fun newInstance(description: String): CakeDescriptionDialog {
            return CakeDescriptionDialog().apply {
                arguments = bundleOf(DESCRIPTION to description)
            }
        }
    }
}