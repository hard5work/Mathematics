package com.xdroid.app.mathematics.utils.vm

import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.xdroid.app.mathematics.BuildConfig
import com.xdroid.app.mathematics.cmodel.DefaultRequestModel
import com.xdroid.app.mathematics.data.UrlName
import com.xdroid.app.mathematics.data.repository.MainRepository
import com.xdroid.app.mathematics.ui.base.BaseViewModel
import com.xdroid.app.mathematics.utils.enums.*
import com.xdroid.app.mathematics.utils.helpers.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal


class HomeViewModel(mainRepository: MainRepository, networkHelper: NetworkHelper) :
    BaseViewModel(mainRepository, networkHelper) {

    private var imageRequest = MutableStateFlow<Resource<JsonObject>>(Resource.idle())

    val imageResponse: StateFlow<Resource<JsonObject>>
        get() = imageRequest

    private var result = MutableStateFlow<Resource<String>>(Resource.idle())

    val myResult: StateFlow<Resource<String>>
        get() = result

    var isInit = false

    fun getAllImage() {
        if (!isInit) {
            isInit = true
            val request = DefaultRequestModel()
            request.url = UrlName.allImageList

            requestGetMethodDispose(request, imageRequest)
        }
    }

    fun calculateSimpleInterest(
        principal: String,
        rate: String,
        time: String,
        interest: String
    ) {
        result.value = Resource.loading()

        var res = ""
        var msg = ""
        if (principal.isEmpty() && time.isEmpty() && rate.isEmpty() && interest.isEmpty()) {
            result.value = Resource.error("Only one field must be empty.")
        } else {
            if (principal.isEmpty()) {
                res = ((interest.float() * 100) / (rate.float() * time.float())).toString()
                msg = "(Principal) = $res"
                result.value = Resource.success(msg)

            } else if (rate.isEmpty()) {

                res = ((interest.float() * 100) / (principal.int() * time.float())).toString()
                msg = "(Rate) = $res"
                result.value = Resource.success(msg)
            } else if (time.isEmpty()) {

                res = ((interest.float() * 100) / (rate.float() * principal.float())).toString()
                msg = "(Time) = $res"
                result.value = Resource.success(msg)
            } else if (interest.isEmpty()) {

                res = ((principal.float() * rate.float() * time.float()) / 100).toString()
                msg = "(Interest) = $res"
                result.value = Resource.success(msg)
            }
        }

    }

    fun convertTime(from: String, fromString: String, toString: String) {
        var res = ""
        val value = from.long()
        result.value = Resource.loading()
        viewModelScope.launch {
            val fromUnit = stringToTimeFrame(fromString)
            val toUnit = stringToTimeFrame(toString)
            res = (value * fromUnit.nanosecondsPerUnit / toUnit.nanosecondsPerUnit).toString()
            result.value = Resource.success(res)
        }
    }
    fun calculateTime(from: String, fromTime: String, toTime: String) {
        var res = ""
        val frm = from.double()

        viewModelScope.launch {
            val fromUnit = stringToMiniTimeFrame(fromTime)
            val toUnit = stringToMiniTimeFrame(toTime)
            when (fromUnit) {
                MiniTimeFrame.Week -> {
                    when (toUnit){
                        MiniTimeFrame.Week -> {
                            result.value = Resource.success(from)
                        }
                        MiniTimeFrame.Month -> {
                            res = (frm /4.34524).toString()
                            result.value = Resource.success(res)
                        }
                        MiniTimeFrame.Year -> {
                            res = (frm / 52.0).toString()
                            result.value = Resource.success(res)
                        }
                    }
                }
                MiniTimeFrame.Month -> {
                    when (toUnit){
                        MiniTimeFrame.Week -> {
                            res = (frm * 4.34524).toString()
                            result.value = Resource.success(res)
                        }
                        MiniTimeFrame.Month -> {
                            result.value = Resource.success(from)
                        }
                        MiniTimeFrame.Year -> {
                            res = (frm / 12.0).toString()
                            result.value = Resource.success(res)
                        }
                    }
                }
                MiniTimeFrame.Year -> {
                    when (toUnit){
                        MiniTimeFrame.Week -> {
                            res = (frm * 52.0).toString()
                            result.value = Resource.success(res)
                        }
                        MiniTimeFrame.Month -> {
                            res = (frm * 12.0).toString()
                            result.value = Resource.success(res)
                        }
                        MiniTimeFrame.Year -> {
                            result.value = Resource.success(from)
                        }
                    }
                }
            }
        }
    }
/*
    fun calculateTime(from: String, fromTime: String, toTime: String) {
        var res = ""
        val frm = from.double()

        viewModelScope.launch {
            val fromUnit = stringToTimeFrames(fromTime)
            val toUnit = stringToTimeFrames(toTime)
            when (fromUnit) {
                TimeFrame.Nanosecond -> {
                    when (toUnit) {
                        TimeFrame.Nanosecond -> {
                            result.value = Resource.success(from)
                        }
                        TimeFrame.Microsecond -> {
                            res = (frm / 1000).toString()
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Millisecond -> {
                            res = (frm /1000000).toString()
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Second -> {
                            res = (frm /1000000000).toString()
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Minute -> {
                            res = (frm / (60 * 1000000000)).toString() // Convert seconds to minutes
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Hour -> {
                            res = (frm / 3600).toString() // Convert seconds to hours
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Day -> {
                            res = (frm / 86400).toString() // Convert seconds to days
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Week -> {
                            res = (frm / 604800).toString() // Convert seconds to weeks
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Month -> {
                            // Approximate calculation: 30 days per month
                            val months = frm / (30 * 24 * 3600)
                            res = months.toString()
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Year -> {
                            // Approximate calculation: 365 days per year
                            val years = frm / (365 * 24 * 3600)
                            res = years.toString()
                            result.value = Resource.success(res)
                        }
                    }
                }
                TimeFrame.Second -> {
                    when (toUnit) {
                        TimeFrame.Nanosecond -> {
                            res = (frm * 1000000000).toString()
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Microsecond -> {
                            res = (frm * 1000000).toString()
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Millisecond -> {
                            res = (frm * 1000).toString()
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Second -> {
                            result.value = Resource.success(from)
                        }
                        TimeFrame.Minute -> {
                            res = (frm / 60).toString() // Convert seconds to minutes
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Hour -> {
                            res = (frm / 3600).toString() // Convert seconds to hours
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Day -> {
                            res = (frm / 86400).toString() // Convert seconds to days
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Week -> {
                            res = (frm / 604800).toString() // Convert seconds to weeks
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Month -> {
                            // Approximate calculation: 30 days per month
                            val months = frm / (30 * 24 * 3600)
                            res = months.toString()
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Year -> {
                            // Approximate calculation: 365 days per year
                            val years = frm / (365 * 24 * 3600)
                            res = years.toString()
                            result.value = Resource.success(res)
                        }
                    }
                }
                TimeFrame.Microsecond -> {
                    when (toUnit) {
                        TimeFrame.Nanosecond -> {
                            res = (frm * 1000).toString()
                            result.value = Resource.success(res)

                        }
                        TimeFrame.Microsecond -> {
                            result.value = Resource.success(from)
                        }
                        TimeFrame.Millisecond -> {


                        }
                        TimeFrame.Second -> TODO()
                        TimeFrame.Minute -> TODO()
                        TimeFrame.Hour -> TODO()
                        TimeFrame.Day -> TODO()
                        TimeFrame.Week -> TODO()
                        TimeFrame.Month -> TODO()
                        TimeFrame.Year -> TODO()
                    }

                }
                TimeFrame.Millisecond -> {

                    when (toUnit) {
                        TimeFrame.Nanosecond -> {
                            res = (frm * 1000000).toString()
                            result.value = Resource.success(res)
                        }
                        TimeFrame.Microsecond -> TODO()
                        TimeFrame.Millisecond -> TODO()
                        TimeFrame.Second -> TODO()
                        TimeFrame.Minute -> TODO()
                        TimeFrame.Hour -> TODO()
                        TimeFrame.Day -> TODO()
                        TimeFrame.Week -> TODO()
                        TimeFrame.Month -> TODO()
                        TimeFrame.Year -> TODO()
                    }
                }
                TimeFrame.Minute -> {
                    when (toUnit) {
                        TimeFrame.Nanosecond -> TODO()
                        TimeFrame.Microsecond -> TODO()
                        TimeFrame.Millisecond -> TODO()
                        TimeFrame.Second -> TODO()
                        TimeFrame.Minute -> TODO()
                        TimeFrame.Hour -> TODO()
                        TimeFrame.Day -> TODO()
                        TimeFrame.Week -> TODO()
                        TimeFrame.Month -> TODO()
                        TimeFrame.Year -> TODO()
                    }
                }
                TimeFrame.Hour -> {
                    when (toUnit) {
                        TimeFrame.Nanosecond -> TODO()
                        TimeFrame.Microsecond -> TODO()
                        TimeFrame.Millisecond -> TODO()
                        TimeFrame.Second -> TODO()
                        TimeFrame.Minute -> TODO()
                        TimeFrame.Hour -> TODO()
                        TimeFrame.Day -> TODO()
                        TimeFrame.Week -> TODO()
                        TimeFrame.Month -> TODO()
                        TimeFrame.Year -> TODO()
                    }
                }
                TimeFrame.Day -> {
                    when (toUnit) {
                        TimeFrame.Nanosecond -> TODO()
                        TimeFrame.Microsecond -> TODO()
                        TimeFrame.Millisecond -> TODO()
                        TimeFrame.Second -> TODO()
                        TimeFrame.Minute -> TODO()
                        TimeFrame.Hour -> TODO()
                        TimeFrame.Day -> TODO()
                        TimeFrame.Week -> TODO()
                        TimeFrame.Month -> TODO()
                        TimeFrame.Year -> TODO()
                    }
                }
                TimeFrame.Week -> {
                    when (toUnit) {
                        TimeFrame.Nanosecond -> TODO()
                        TimeFrame.Microsecond -> TODO()
                        TimeFrame.Millisecond -> TODO()
                        TimeFrame.Second -> TODO()
                        TimeFrame.Minute -> TODO()
                        TimeFrame.Hour -> TODO()
                        TimeFrame.Day -> TODO()
                        TimeFrame.Week -> TODO()
                        TimeFrame.Month -> TODO()
                        TimeFrame.Year -> TODO()
                    }
                }
                TimeFrame.Month -> {
                    when (toUnit) {
                        TimeFrame.Nanosecond -> TODO()
                        TimeFrame.Microsecond -> TODO()
                        TimeFrame.Millisecond -> TODO()
                        TimeFrame.Second -> TODO()
                        TimeFrame.Minute -> TODO()
                        TimeFrame.Hour -> TODO()
                        TimeFrame.Day -> TODO()
                        TimeFrame.Week -> TODO()
                        TimeFrame.Month -> TODO()
                        TimeFrame.Year -> TODO()
                    }
                }
                TimeFrame.Year -> {
                    when (toUnit) {
                        TimeFrame.Nanosecond -> TODO()
                        TimeFrame.Microsecond -> TODO()
                        TimeFrame.Millisecond -> TODO()
                        TimeFrame.Second -> TODO()
                        TimeFrame.Minute -> TODO()
                        TimeFrame.Hour -> TODO()
                        TimeFrame.Day -> TODO()
                        TimeFrame.Week -> TODO()
                        TimeFrame.Month -> TODO()
                        TimeFrame.Year -> TODO()
                    }
                }
            }
        }
    }*/


}