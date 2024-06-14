package com.xdroid.app.mathematics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.android.gms.ads.MobileAds
import com.xdroid.app.mathematics.ui.adscreen.BannerAdView
import com.xdroid.app.mathematics.ui.adscreen.loadInterstitial
import com.xdroid.app.mathematics.ui.layouts.MyApp
import com.xdroid.app.mathematics.ui.theme.MathematicsTheme
import com.xdroid.app.mathematics.ui.theme.background
import com.xdroid.app.mathematics.utils.helpers.NetworkHelper
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val networkHelper: NetworkHelper by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this) {}
        loadInterstitial(this)
        setContent {
            MathematicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = background
                ) {
//                    ConstraintLayout() {
                    /* val (container, ads) = createRefs()
                     Box(modifier = Modifier.fillMaxWidth().constrainAs(container) {
                         top.linkTo(parent.top)
                         start.linkTo(parent.start)
                         end.linkTo(parent.end)
                         bottom.linkTo(ads.top)
                     })
                     {
                         MyApp()
                     }


                     if (networkHelper.isNetworkConnected()) {
                         Box(modifier =  Modifier.fillMaxWidth() .constrainAs(ads) {
                             bottom.linkTo(parent.bottom)
                             start.linkTo(parent.start)
                             end.linkTo(parent.end)
                         }
                             // Adjust the height of the ad banner container
                         ) {
                             BannerAdView()
                         }
                     }*/
//                }
                   /* Box() {
                        Box(modifier = Modifier.fillMaxWidth())
                        {
                            MyApp()
                        }


                        if (networkHelper.isNetworkConnected()) {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter))
                            {
                                BannerAdView()
                            }
                        }
                    }*/
                    Column() {
                        Box(modifier = Modifier.fillMaxWidth().weight(1f))
                        {
                            MyApp()
                        }


                        if (networkHelper.isNetworkConnected()) {
                            Box(modifier = Modifier
                                .fillMaxWidth())
                            {
                                BannerAdView()
                            }
                        }
                    }
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MathematicsTheme {
            MyApp()
        }
    }
}