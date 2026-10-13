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
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.gson.Gson

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InputFragment : Fragment() {

    var PREFS_FILE = "Account"
    var PREF_NAME = "Name"




    lateinit var preferences: SharedPreferences

    lateinit var ed: SharedPreferences.Editor

    lateinit var addButton : Button

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
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Находим кнопку по её ID из layout-файла
        addButton = view.findViewById(R.id.addBtn)

        // Обрабатываем нажатие
        addButton.setOnClickListener {
            // Действие при клике
            handler(view)
        }
    }

    fun handler(view: View)
    {
        var theme1 : EditText = view.findViewById(R.id.themeEdit1)
        var theme2 : EditText = view.findViewById(R.id.themeEdit2)
        var theme3 : EditText = view.findViewById(R.id.themeEdit3)
        var theme4 : EditText = view.findViewById(R.id.themeEdit4)

        preferences = requireActivity().getSharedPreferences(PREFS_FILE,MODE_PRIVATE)

        if (theme1.text.toString().isNotEmpty() && theme2.text.toString().isNotEmpty() && theme3.text.toString()
                .isNotEmpty() && theme4.text.toString().isNotEmpty()
        ) {
            val themeObject: ExamThemes = ExamThemes(
                theme1.text.toString(),
                theme2.text.toString(),
                theme3.text.toString(),
                theme4.text.toString()
            )


            val JSONThemes = Gson().toJson(themeObject)

            val alert = AlertDialog.Builder(requireContext())
                .setTitle("Загружено")
                .setMessage(JSONThemes)
                .setPositiveButton("OK",null)
                .create()
                .show()

            ed = preferences.edit()

            ed.putString("themeObject", JSONThemes)

            ed.apply()

            val fragmentManager: FragmentManager = parentFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.fragment_container_view, OutputFragment())
            fragmentTransaction.addToBackStack(InputFragment.toString())
            fragmentTransaction.commit()
        }
        else
        {
            val alert = AlertDialog.Builder(requireContext())
                .setTitle("Ошибка")
                .setMessage("Одно или несколько из полей пусты")
                .setPositiveButton("OK",null)
                .create()
                .show()
        }



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InputFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InputFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun addBtn(view: View) {}
}