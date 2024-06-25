package id.recread.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.recread.databinding.FragmentProfileBinding
import id.recread.preferences.UserPreferences.KEY_IS_LOGIN
import id.recread.preferences.UserPreferences.USER_PREF_NAME
import id.recread.ui.login.LoginActivity

class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonLogout?.setOnClickListener {
            updateStatusLoginStatus()
            startActivity(Intent(context, LoginActivity::class.java))
            activity?.finish()
        }
    }

    private fun updateStatusLoginStatus() {
        val sharedPreferences =
            context?.getSharedPreferences(USER_PREF_NAME, AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putBoolean(KEY_IS_LOGIN, false)
        editor?.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}