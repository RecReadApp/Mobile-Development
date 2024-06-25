package id.recread.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.recread.R
import id.recread.databinding.ActivityMainBinding
import id.recread.ui.article.ArticleFragment
import id.recread.ui.home.HomeFragment
import id.recread.ui.profile.ProfileFragment
import id.recread.ui.saved.SavedFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: HomeFragment
    private lateinit var articleFragment: ArticleFragment
    private lateinit var savedFragment: SavedFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()

        initFragment()
        initListener()
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun initFragment() {
        homeFragment = HomeFragment()
        articleFragment = ArticleFragment()
        savedFragment = SavedFragment()
        profileFragment = ProfileFragment()

        setCurrentFragment(homeFragment)
    }

    private fun initListener() {
        binding.bottomNavigation.itemActiveIndicatorColor
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> setCurrentFragment(homeFragment)
                R.id.menu_article -> setCurrentFragment(articleFragment)
                R.id.menu_saved -> setCurrentFragment(savedFragment)
                R.id.menu_profile -> setCurrentFragment(profileFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                // Do nothing
                true
            }

            R.id.action_settings -> {
                // Do nothing
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}