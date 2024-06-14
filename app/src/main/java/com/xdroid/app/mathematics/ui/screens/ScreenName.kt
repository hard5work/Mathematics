package com.xdroid.app.mathematics.ui.screens


object ScreenName {
    const val Login = "login"
    const val OTP = "otp"
    const val Home = "home"
    const val SignUp = "signup"
    const val SignupSecond = "signUpSecond"
    const val MovieDetail = "movieDetail/{movieId}"
    const val MovieList = "movieList/{movieTitle}/{movieList}"
    const val Subscription = "subscription/{subs}"
    const val ChangePassword = "changePassword"
    const val MediaPlayer = "mediaPlayer"
    const val TrailerPlayer = "trailerPlayer/{trailer}"
    const val EditProfile = "editProfile/{profile}"

    const val Recharge = "Recharge"
    const val Offer = "Offer"
    const val Profile = "Profile"
    const val Detail = "Detail"

    //Services
    const val DataPack = "DataPack"
    const val MyPlan = "MyPlan"
    const val GiftPacks = "GiftPacks"
    const val PRBT = "Prbt"
    const val BalanceTransfer = "BalanceTransfer"
    const val FreeSms = "FreeSms"
    const val LiveTv = "LiveTv"
    const val UsageHistory = "UsageHistory"
    const val AllTones = "AllTones"
    const val NavigationScreen = "NavigationScreen"
    const val SimpleInterest = "SimpleInterest"
    const val TimeConverter = "TimeConverter"

    fun detailRoute(name:String,url: String): String {
        return "$name?table=$url"
    }

}