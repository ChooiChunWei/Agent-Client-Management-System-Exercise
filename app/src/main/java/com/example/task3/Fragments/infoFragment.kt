package com.example.task3.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.task3.R
import kotlinx.android.synthetic.main.fragment_scan_doc.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [infoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class infoFragment(private val bundle: Bundle) : Fragment() {
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
        val v =inflater.inflate(R.layout.fragment_info, container,false)

        //Populate the correct info
        val LA1nameTxt = v.findViewById<TextView>(R.id.LA1nameTxt)
        val LA2NameTxt = v.findViewById<TextView>(R.id.LA2NameTxt)

        if(bundle != null){
            var LA1 = bundle.getString("name")
            var LA2 = bundle.getString("LA2")

            LA1nameTxt.text = LA1
            LA2NameTxt.text = LA2

        }

        //Open and close the details
        val LA1Btn = v.findViewById<ImageView>(R.id.LA1updownImg)
        val LA2Btn = v.findViewById<ImageView>(R.id.LA2updownImg)

        LA1Btn.setOnClickListener{
            if(linearLayout1.visibility == View.GONE){
                linearLayout1.visibility = View.VISIBLE
                LA1Btn.setImageResource(R.drawable.ic_up)

            }else{
                linearLayout1.visibility = View.GONE
                LA1Btn.setImageResource(R.drawable.ic_down)
            }
        }

        LA2Btn.setOnClickListener{
            if(linearLayout2.visibility == View.GONE){
                linearLayout2.visibility = View.VISIBLE
                LA2Btn.setImageResource(R.drawable.ic_up)

            }else{
                linearLayout2.visibility = View.GONE
                LA2Btn.setImageResource(R.drawable.ic_down)

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
         * @return A new instance of fragment infoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, bundle: Bundle) =
            infoFragment(bundle).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}