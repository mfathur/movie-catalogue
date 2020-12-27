package com.mfathurz.moviecatalogue.util

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {
    @Test
    fun changeDateFormat() {
        val stringDate = "2020-04-17"
        val date = UtilsHelper.changeDateFormat(stringDate)

        assertEquals("April 17, 2020", date)
    }
}