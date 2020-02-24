package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rental.R
import com.rental.customer.dashboard.view.fragment.*
import com.rental.customer.dashboard.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity :AppCompatActivity(){

    lateinit var homeViewModel: HomeViewModel
    val veichleList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottom_view.setSelectedItemId(R.id.navigation_home);
        setDefaultFragment()
    }

    private fun setDefaultFragment(){
        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_home -> {
                setDefaultFragment();
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_wishlist -> {
                val fragment = WishListFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_order -> {
                val fragment = OrderFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_profile -> {
                val fragment = ProfieFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_support -> {
                val fragment = SupportFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}