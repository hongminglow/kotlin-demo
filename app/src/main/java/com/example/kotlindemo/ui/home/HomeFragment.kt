package com.example.kotlindemo.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindemo.LoginActivity
import com.example.kotlindemo.MainActivity
import com.example.kotlindemo.data.User
import com.example.kotlindemo.databinding.FragmentHomeBinding
import com.example.kotlindemo.ui.login.LoginViewModel
import com.example.kotlindemo.R

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var viewFlipper: ViewFlipper

    private var backButtonVisible  = false
//    private lateinit var userViewModel: HomeViewModel
//    private lateinit var userRepository: UserRepository

    companion object {
        private const val username_tag = "Username"
        private const val home_tag = "Home"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize Repository
//        val apiService = RetrofitClient.apiService
//        userRepository = UserRepository(apiService)

        //Initialize View Model
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

//        userViewModel = ViewModelProvider(this, HomeViewModelFactory(userRepository)).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        viewFlipper = binding.root.findViewById(R.id.view_flipper)
        val user = getUserData(requireContext())


        Log.d("Shared preference..", "Username : ${user?.username}")


        homeViewModel.text.observe(viewLifecycleOwner) {
            Log.d(home_tag, "The ans is...$it")
            textView.text = it
        }

//        userViewModel.userLists.observe(viewLifecycleOwner, { users ->
//            Log.d("User List", "User lists..$users")
//        })



        binding.logOutButton.setOnClickListener {
            logout()
        }

        return root
    }

    private fun logout() {
        // Perform any logout operations here (e.g., clear session, remove tokens)

        // Navigate to LoginActivity
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()  // Close the current activity
    }

    private fun getUserData(context: Context): User? {
        val sharedPref = context.getSharedPreferences(
            "user_details", Context.MODE_PRIVATE
        )
        val username = sharedPref.getString("username", null)

        return if (username != null) {
            User(username)
        } else {
            null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cardView: CardView = view.findViewById(R.id.user_profile_switcher_tab)
        val userInformation: TextView = view.findViewById(R.id.user_information)
        val userDetails: TextView = view.findViewById(R.id.user_details)
        val applicationDetails: TextView = view.findViewById(R.id.application_details)
        val backButton : TextView = view.findViewById(R.id.back_button)
        val backButton2 : TextView = view.findViewById(R.id.back_button_2)
        val backButton3 : TextView = view.findViewById(R.id.back_button_3)

        userInformation.setOnClickListener {
            viewFlipper.displayedChild = 1
            (activity as MainActivity).updateBackButtonVisibility(true)
        }

        userDetails.setOnClickListener {
            viewFlipper.displayedChild = 2
            (activity as MainActivity).updateBackButtonVisibility(true)
            adjustCardViewHeight(cardView, true)
        }

        applicationDetails.setOnClickListener {
            viewFlipper.displayedChild = 3
            (activity as MainActivity).updateBackButtonVisibility(true)
        }

        backButton.setOnClickListener {
            viewFlipper.displayedChild = 0
            (activity as MainActivity).updateBackButtonVisibility(false)
        }

        backButton2.setOnClickListener {
            viewFlipper.displayedChild = 0
            (activity as MainActivity).updateBackButtonVisibility(false)
            adjustCardViewHeight(cardView, false)
        }

        backButton3.setOnClickListener {
            viewFlipper.displayedChild = 0
            (activity as MainActivity).updateBackButtonVisibility(false)
        }

        // Optionally, set the default view
        viewFlipper.displayedChild = 0


    }

    private fun adjustCardViewHeight(cardView: CardView, changeLayout:Boolean) {

        //based on the flip content view to auto adjust the content
//        val currentView = viewFlipper.currentView
//        currentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
//        val height = currentView.measuredHeight
//        cardView.layoutParams.height = height
//        cardView.requestLayout()

        if(changeLayout){
            cardView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        }else{
            // Convert 190dp to pixels
            val heightInDp = 190
            val heightInPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                heightInDp.toFloat(),
                resources.displayMetrics
            ).toInt()

            // Set the height of the CardView directly
            cardView.layoutParams.height = heightInPx
        }
        cardView.requestLayout()
    }

    fun backToFirstView() {
        Log.d("OptionsMenu", "Revert back..")
        viewFlipper.displayedChild = 0 // Display the first child view
    }
}