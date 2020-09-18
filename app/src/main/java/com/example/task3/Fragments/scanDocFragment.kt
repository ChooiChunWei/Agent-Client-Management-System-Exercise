package com.example.task3.Fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.task3.R
import kotlinx.android.synthetic.main.fragment_scan_doc.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [scanDocFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class scanDocFragment(private val bundle: Bundle) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var test: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_scan_doc, container, false)

        //Populate the correct info
        val LA1nameTxt = v.findViewById<TextView>(R.id.LA1nameTxt)
        val LA2NameTxt = v.findViewById<TextView>(R.id.LA2NameTxt)

        if (bundle != null) {
            var LA1 = bundle.getString("name")
            var LA2 = bundle.getString("LA2")

            LA1nameTxt.text = LA1
            LA2NameTxt.text = LA2

        }

        //Open and close the details
        val LA1Btn = v.findViewById<ImageView>(R.id.LA1updownImg)
        val LA2Btn = v.findViewById<ImageView>(R.id.LA2updownImg)

        LA1Btn.setOnClickListener {
            if (linearLayout1.visibility == View.GONE) {
                linearLayout1.visibility = View.VISIBLE
                LA1Btn.setImageResource(R.drawable.ic_up)

            } else {
                linearLayout1.visibility = View.GONE
                LA1Btn.setImageResource(R.drawable.ic_down)
            }
        }

        LA2Btn.setOnClickListener {
            if (linearLayout2.visibility == View.GONE) {
                linearLayout2.visibility = View.VISIBLE
                LA2Btn.setImageResource(R.drawable.ic_up)

            } else {
                linearLayout2.visibility = View.GONE
                LA2Btn.setImageResource(R.drawable.ic_down)

            }
        }

        //Disable this bp1 when it is created
        val bp1 = v.findViewById<ConstraintLayout>(R.id.bp1)
        bp1.isEnabled = false

        //upload document button
        val uploadBtn = v.findViewById<CardView>(R.id.bp1Card)
        val fSizeTxt = v.findViewById<TextView>(R.id.bp1FileSizeTxt)
        val fPageTxt = v.findViewById<TextView>(R.id.bp1PageNumTxt)
        val submitLayout = v.findViewById<ConstraintLayout>(R.id.submitLayout)
        val bp1ImageVIew = v.findViewById<ImageView>(R.id.bp1ImageView)

        //when upload document button is pressed
        uploadBtn.setOnClickListener {
            fSizeTxt.visibility = View.VISIBLE
            fPageTxt.visibility = View.VISIBLE
            submitLayout.visibility = View.VISIBLE
            bp1ImageVIew.setImageResource(R.drawable.ic_draft)
            bp1ImageVIew.setBackgroundColor(Color.parseColor("#f0f0f0"))

            //Disable this button
            uploadBtn.isEnabled = false

        }

        //When submit button is pressed
        val submitBtn = v.findViewById<TextView>(R.id.submitBtn)
        val scrollview = v.findViewById<ScrollView>(R.id.scrollview)
        val gh1 = v.findViewById<ConstraintLayout>(R.id.gh1)
        val bp2 = v.findViewById<ConstraintLayout>(R.id.bp2)
        val gh2 = v.findViewById<ConstraintLayout>(R.id.gh2)

        submitBtn.setOnClickListener {
            if (submitBtn.text == "Submit") {
                submitBtn.setText("Cancel")

                //change the outlook of the submit button
                submitBtn.setTextColor(Color.parseColor("#EF190A"))
                submitBtn.setBackgroundColor(Color.parseColor("#FFFFFF"))
                //Add the fake persistent bottom sheet
                scrollview.layoutParams.height = 900
                scrollview.requestLayout()
                //blur out all the other options
                gh1.alpha = 0.5f
                gh2.alpha = 0.5f
                bp2.alpha = 0.5f

                //enable the bp1
                bp1.isEnabled = true
            } else {
                //refresh this fragment
                val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
                ft.detach(this).attach(this).commit()
            }
        }

        //When the first item is being selected
        val submitSelected = v.findViewById<TextView>(R.id.submitSelectedTxt)
        val bp1ShrinkCard = v.findViewById<CardView>(R.id.bp1ShrinkCard)

        bp1.setOnClickListener {
            submitSelected.setBackgroundColor(Color.RED)
            submitSelected.setTextColor(Color.WHITE)

            //change shadow colour
            bp1.outlineSpotShadowColor = Color.BLUE
            //add the border effect
            val param = bp1ShrinkCard.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(12, 12, 12, 12)
            bp1ShrinkCard.layoutParams = param
            bp1.setBackgroundResource(R.drawable.cardview_rounded_border_selected)
            ViewCompat.setElevation(bp1, 50F)

            //enable the user press the "submit selected"
            submitSelected.isEnabled = true

            //disable this bp1
            bp1.isEnabled = false
        }

        //User pressed the "submit selected"
        val closeBtn = v.findViewById<ImageView>(R.id.close_icon)

        submitSelected.setOnClickListener {
            submitSelected.setText("Documents Submitted Successfully")
            //Change submit selected bottom sheet from red to green
            submitSelected.setBackgroundColor(Color.parseColor("#1db440"))
            //Set the close icon visible
            closeBtn.visibility = View.VISIBLE

            //Set submit layout to gone
            submitLayout.visibility = View.GONE

            //Change the icon in the bp1
            bp1ImageVIew.setImageResource(R.drawable.ic_upload)
            bp1ImageVIew.setBackgroundColor(Color.parseColor("#FF9800"))

            //change shadow colour back to white
            bp1.outlineSpotShadowColor = Color.BLACK

            //remove the border effect
            val param = bp1ShrinkCard.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(0, 0, 0, 0)
            bp1ShrinkCard.layoutParams = param
            bp1.setBackgroundResource(R.drawable.cardview_rounded_border)
            ViewCompat.setElevation(bp1, 40F)

            //Resize the scrollview
            scrollview.layoutParams.height = 1100
            scrollview.requestLayout()

            //remove the blur out effect for all the other options
            gh1.alpha = 1f
            gh2.alpha = 1f
            bp2.alpha = 1f


            test = true

        }

        //when the close button is being pressed
        closeBtn.setOnClickListener {
            //Resize the scrollview
            scrollview.layoutParams.height = 1340
            scrollview.requestLayout()
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
         * @return A new instance of fragment scanDocFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, bundle: Bundle) =
            scanDocFragment(bundle).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }

}