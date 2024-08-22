package com.compose.mikasa

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.compose.mikasa.components.NormalTextComponent
import com.compose.mikasa.screens.HomeScreen

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    private val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.compose.mikasa", appContext.packageName)
    }

    @Test
    fun myComposeUnitTest() {
        composeTestRule.setContent {
            NormalTextComponent("testTest")
        }

        val textField = composeTestRule.onNodeWithText("NormalTestComponenet")
        assertTrue(textField.isDisplayed())
        //val textField = composeTestRule.onNodeWithText("Enter your name:")
        //assertTrue(textField.isVisibleToUser())

        // Interacting with the UI component
        composeTestRule.onNodeWithText("Submit").performClick()

        // Confirming expected results
        val snackbar = composeTestRule.onNodeWithText("Name cannot be empty!")
        assertTrue(snackbar.isDisplayed())
    }
}

//api , repository, rick and morty (rama main), llamar el metodo que acabo de mencionar y en el test colocar datos falsos par asimular la respuesta

//assert mandarle los datos ficticios, simula la piticion y dar la respuesta