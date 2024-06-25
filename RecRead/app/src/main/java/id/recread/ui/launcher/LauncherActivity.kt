package id.recread.ui.launcher

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import id.recread.preferences.UserPreferences.KEY_IS_LOGIN
import id.recread.preferences.UserPreferences.USER_PREF_NAME
import id.recread.ui.MainActivity
import id.recread.ui.login.LoginActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        if (isLoggedIn()) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        finish()
    }

    private fun isLoggedIn(): Boolean {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences(USER_PREF_NAME, MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_IS_LOGIN, false)
    }
}