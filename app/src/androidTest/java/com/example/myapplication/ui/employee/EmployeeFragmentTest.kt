package com.example.myapplication.ui.employee

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.myapplication.R
import com.example.myapplication.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class EmployeeFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun clickNavigate() {
        val navController = mock(NavController::class.java)
//        `when`(navController.popBackStack()).thenReturn()
        launchFragmentInHiltContainer<EmployeeFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

//        onView(withId(R.id.fab)).perform(click())

//        pressBack()
        
        verify(navController).navigate(
            EmployeeFragmentDirections.actionEmployeeFragmentToDetailFragment()
        )

//        onView(withId(R.id.fab)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<EmployeeAdapter.EmployeeViewHolder>(
//                0,
//                click()
//            )
//        )
//
//        verify(navController).popBackStack()

        // No animation emulator
        // adb shell settings put global window_animation_scale 0
        // adb shell settings put global transition_animation_scale 0
        // adb shell settings put global animator_duration_scale 0
    }
}