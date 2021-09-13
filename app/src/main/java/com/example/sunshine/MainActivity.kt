package com.example.sunshine

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sunshine.databinding.ActivityMainBinding
import com.example.sunshine.ui.home.HomeViewModel
import com.example.sunshine.utils.Constants
import com.google.android.gms.location.LocationServices
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val fusedLocationProvider by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }
    private val homeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkLocationPermissions()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        // Manually setting toolbar characteristics to override setupActionBarWithNavController
        // which sets toolbar characteristics automatically.
        navController.addOnDestinationChangedListener { _, _, _ ->
            binding.appBarMain.toolbar.setTitleTextAppearance(
                applicationContext,
                R.style.Theme_Sunshine_ActionBar
            )
            binding.appBarMain.toolbar.setNavigationIcon(R.drawable.menu)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // Check for Location Permissions on Request Permissions Result.
    // If allowed, acquire location and send lat/long to HomeViewModel.
    // If not allowed, send null lat/long to HomeViewModel.
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constants.PERMISSIONS_REQUEST_CODE -> {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    homeViewModel.setCoordinates(null, null)
                    return
                }
                fusedLocationProvider.lastLocation.addOnSuccessListener { location ->
                    homeViewModel.setCoordinates(location?.latitude, location?.longitude)
                }.addOnFailureListener {
                    homeViewModel.setCoordinates(null, null)
                }
            }
        }
    }

    // Check for Location Permissions on Activity creation.
    // If allowed, acquire location and send lat/long to HomeViewModel.
    // If not allowed, request permissions.
    private fun checkLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                Constants.PERMISSIONS_REQUEST_CODE
            )
            return
        }
        fusedLocationProvider.lastLocation.addOnSuccessListener { location ->
            homeViewModel.setCoordinates(location?.latitude, location?.longitude)
        }.addOnFailureListener {
            homeViewModel.setCoordinates(null, null)
        }
    }
}