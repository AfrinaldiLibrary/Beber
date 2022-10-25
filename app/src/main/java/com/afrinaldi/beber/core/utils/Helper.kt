package com.afrinaldi.beber.core.utils

import com.afrinaldi.beber.R
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Helper {
    fun formatDate(currentDate: String) : String? {
        val currentFormat = "yyyy-MM-dd'T'hh:mm:ss'Z'"
        val targetFormat = "dd MMM yyyy"
        val timezone = "GMT"
        val currentDf: DateFormat = SimpleDateFormat(currentFormat, Locale.getDefault())
        currentDf.timeZone = TimeZone.getTimeZone(timezone)
        val targetDf: DateFormat = SimpleDateFormat(targetFormat, Locale.getDefault())
        var targetDate: String? = null
        try {
            val date = currentDf.parse(currentDate)
            if (date != null) {
                targetDate = targetDf.format(date)
            }
        } catch (ex: ParseException) {
            ex.printStackTrace()
        }
        return targetDate
    }

    fun setStatusBookmark(statusBookmark: Boolean) : Int{
        return if (statusBookmark) {
            R.drawable.ic_bookmark_filled
        } else {
            R.drawable.ic_bookmark
        }
    }

    fun setStatusBookmarkItem(statusBookmark: Boolean) : Int{
        return if (statusBookmark) {
            R.color.blue
        } else {
            R.color.grey
        }
    }
}