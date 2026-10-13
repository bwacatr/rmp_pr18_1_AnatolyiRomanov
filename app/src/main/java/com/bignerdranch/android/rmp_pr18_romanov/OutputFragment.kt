package com.bignerdranch.android.rmp_pr18_romanov

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.gson.Gson

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OutputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OutputFragment : Fragment() {

    lateinit var goBackBtn : Button

    lateinit var preferences: SharedPreferences

    lateinit var ed: SharedPreferences.Editor

    var PREFS_FILE = "Account"
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        goBackBtn= view.findViewById(R.id.goBackBtn)


        goBackBtn.setOnClickListener {

            handler(view)
        }

        preferences = requireActivity().getSharedPreferences(PREFS_FILE,MODE_PRIVATE)

        ed = preferences.edit()

        val themeOutput1 : TextView = view.findViewById(R.id.themeOutput1)
        val themeOutput2 : TextView = view.findViewById(R.id.themeOutput2)
        val themeOutput3 : TextView = view.findViewById(R.id.themeOutput3)
        val themeOutput4 : TextView = view.findViewById(R.id.themeOutput4)

        val savedThemes : ExamThemes = Gson().fromJson(preferences.getString("themeObject", ""),
            ExamThemes::class.java)

        themeOutput1.text = savedThemes.theme1
        themeOutput2.text = savedThemes.theme2
        themeOutput3.text = savedThemes.theme3
        themeOutput4.text = savedThemes.theme4
    }

    fun handler(view: View)
    {
        val fragmentManager: FragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container_view, InputFragment())
        fragmentTransaction.addToBackStack(OutputFragment.toString())
        fragmentTransaction.commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_output, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OutputFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OutputFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}