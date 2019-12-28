package com.example.easysend.utils

import java.text.NumberFormat
import java.util.*

fun ConvertToCurrency(ammountDbl:Double?=null, ammountStr:String?=null): String{
    var value:Double? = null
    if (ammountStr!=null)
        value = ammountStr.toDouble()
    else if (ammountDbl!=null)
        value = ammountDbl
    val localeID = Locale("in", "ID")
    val format = NumberFormat.getCurrencyInstance(localeID)
    //format.setMaximumFractionDigits(0)
    //format.setCurrency(Currency.getInstance("IDR"))
    var result = format.format(value)
    result = StringBuffer(result).insert(2, ". ").toString()
    result = StringBuffer(result).insert(result.length, ",-").toString()
    return result
}

fun ConvertCurrencyToDouble(input: String): Double {
    return input.replace("[Rp. ]".toRegex(), "").toDouble()
}