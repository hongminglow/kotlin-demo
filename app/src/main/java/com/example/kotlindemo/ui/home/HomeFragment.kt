package com.example.kotlindemo.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindemo.LoginActivity
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
        val usernameView: TextView = binding.username
        val user = getUserData(requireContext())


        Log.d("Shared preference..", "Username : ${user?.username}")


        homeViewModel.text.observe(viewLifecycleOwner) {
            Log.d(home_tag, "The ans is...$it")
            textView.text = it
        }

//        userViewModel.userLists.observe(viewLifecycleOwner, { users ->
//            Log.d("User List", "User lists..$users")
//        })


        // Observe changes in username LiveData
        loginViewModel.username.observe(viewLifecycleOwner) { username ->
            Log.d(username_tag, "Username updated: $username")
            usernameView.text = username ?: "Guest"  // Default text if username is null
        }

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

        val userInformation: TextView = view.findViewById(R.id.user_information)
        val userDetails: TextView = view.findViewById(R.id.user_details)
        val applicationDetails: TextView = view.findViewById(R.id.application_details)
        val viewFlipper: ViewFlipper = view.findViewById(R.id.view_flipper)
        val backButton : TextView = view.findViewById(R.id.back_button)

        userInformation.setOnClickListener {
            viewFlipper.displayedChild = 1
        }

        userDetails.setOnClickListener {
            viewFlipper.displayedChild = 2
        }

        applicationDetails.setOnClickListener {
            viewFlipper.displayedChild = 3
        }

        backButton.setOnClickListener {
            viewFlipper.displayedChild = 0
        }

        // Optionally, set the default view
        viewFlipper.displayedChild = 0


    }
}