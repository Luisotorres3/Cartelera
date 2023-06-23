package com.pdm.cartelera

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.pdm.cartelera.Model.Film
import com.pdm.cartelera.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        var screenSplash= installSplashScreen()
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inicializar()
        funcionesBotones()



    }

    override fun onStop(){
        super.onStop()
    }

    override fun onPause(){
        super.onPause()
    }

    fun inicializar(){

        bottomNav= findViewById<BottomNavigationView>(R.id.bottom_navigation)
        cambiarFragment(Home.newInstance("Empty",""))
        bottomNav.selectedItemId=R.id.nav_films

        drawerLayout = findViewById(R.id.drawer_layout)


    }

    fun funcionesBotones(){
        bottomNav.setOnNavigationItemSelectedListener {menuItem->
            val fragment = supportFragmentManager.findFragmentById(R.id.mainFrame)
            when (menuItem.itemId) {
                R.id.nav_films-> {
                    if(!(fragment is Home)){
                        cambiarFragment(Home.newInstance("",""))
                    }
                    true
                }
                R.id.nav_cinema-> {
                    if(!(fragment is Cinema)){
                        cambiarFragment(Cinema.newInstance(Film()))
                    }
                    true
                }
                R.id.nav_find-> {
                    if(!(fragment is Find)){
                        cambiarFragment(Find.newInstance("",""))
                    }
                    true
                }R.id.nav_extras-> {
                    drawerLayout.openDrawer(GravityCompat.END)
                    true
                }
                else -> false
            }
        }

        val navigation_view = findViewById<NavigationView>(R.id.navigation_view)
        navigation_view.setNavigationItemSelectedListener  {menuItem->
            val fragment = supportFragmentManager.findFragmentById(R.id.mainFrame)
            when (menuItem.itemId) {
                R.id.nav_contact-> {
                    if(!(fragment is Contact)){
                        cambiarFragment(Contact.newInstance("",""))
                        drawerLayout.closeDrawer(GravityCompat.END)
                    }
                    true
                }
                /*
                R.id.nav_boookings-> {
                    if(!(fragment is Bookings)){
                        cambiarFragment(Bookings.newInstance("",""))
                        drawerLayout.closeDrawer(GravityCompat.END)
                    }
                    true
                }

                 */
                else -> false
            }
        }



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    fun cambiarFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.mainFrame,fragment).commit()

    }
}