package com.mfathurz.moviecatalogue

import com.mfathurz.moviecatalogue.core.utils.Helpers
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {
    @Test
    fun changeDateFormat() {
        val stringDate = "2020-04-17"
        val date = Helpers.changeDateFormat(stringDate)

        assertEquals("April 17, 2020", date)
    }
}