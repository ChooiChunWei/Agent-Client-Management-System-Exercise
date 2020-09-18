package com.example.task3.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.task3.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [policyServicingCaseDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class policyServicingCaseDetailsFragment(private val bundle: Bundle) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =inflater.inflate(R.layout.fragment_policy_servicing_case_details, container, false)

        val processTxt = v.findViewById<TextView>(R.id.processTxt)
        val laNameTxt = v.findViewById<TextView>(R.id.cdLifeANameTxt)
        val laIDTxt = v.findViewById<TextView>(R.id.cdLifeAIDTxt)
        val poNameTxt = v.findViewById<TextView>(R.id.cdPolicyONameTxt)
        val poIDTxt = v.findViewById<TextView>(R.id.cdPolicyOIDTxt)

        if(bundle != null){
            var name = bundle.getString("name")
            var NRIC = bundle.getString("NRIC")
            var Passport = bundle.getString("Passport")
            var Process = bundle.getString("Process")

            processTxt.text = Process
            laNameTxt.text = name
            poNameTxt.text = name

            if(NRIC == ""){
                laIDTxt.text = Passport
                poIDTxt.text = Passport
            }else if(Passport == ""){
                laIDTxt.text = NRIC
                poIDTxt.text = NRIC
            }
        }

        // Inflate the layout for this fragment
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment policyServicingCaseDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, bundle: Bundle) =
            policyServicingCaseDetailsFragment(bundle).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}