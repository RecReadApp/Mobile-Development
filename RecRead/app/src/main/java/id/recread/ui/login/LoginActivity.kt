package id.recread.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.recread.R
import id.recread.databinding.ActivityLoginBinding
import id.recread.network.NetworkInstance
import id.recread.network.NetworkService
import id.recread.preferences.UserPreferences.KEY_IS_LOGIN
import id.recread.preferences.UserPreferences.USER_PREF_NAME
import id.recread.repository.LoginRepository
import id.recread.ui.MainActivity
import id.recread.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initListener()
    }

    private fun initViewModel() {
        val service: NetworkService = NetworkInstance.retrofit.create(NetworkService::class.java)
        val factory = LoginViewModelFactory(LoginRepository(service))
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
    }

    private fun initListener() {
        viewModel.state.observe(this) { success ->
            if (success == true) {
                updateStatusLoginStatus()
                openHomePage()
                viewModel.successLogin()
                finish()
            }
        }
        binding.textCreateAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.buttonLogin.setOnClickListener {
            val username = binding.inputTextUsername.text.toString()
            val password = binding.inputTextPassword.text.toString()

            if (username.isEmpty()) {
                binding.inputLayoutUsername.error = getString(R.string.app_error_required_field)
            }

            if (password.isEmpty()) {
                binding.inputLayoutPassword.error = getString(R.string.app_error_required_field)
            }

            if (username.isNotEmpty() && password.isNotEmpty()) {
                binding.inputLayoutUsername.error = null
                binding.inputLayoutPassword.error = null

                viewModel.doLogin(username, password)
            }
        }
    }

    private fun openHomePage() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun updateStatusLoginStatus() {
        val sharedPreferences = getSharedPreferences(USER_PREF_NAME, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_IS_LOGIN, true)
        editor.apply()
    }

}