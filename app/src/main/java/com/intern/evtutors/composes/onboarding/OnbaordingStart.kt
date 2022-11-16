package com.intern.evtutors.composes.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.FloatRange
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.intern.evtutors.R
import com.intern.evtutors.activities.Login
import com.intern.evtutors.activities.Register

import com.intern.evtutors.data.models.OnBoardingData
import com.intern.evtutors.ui.customer.profile.ui.theme.BlueText
import com.intern.evtutors.ui.customer.profile.ui.theme.whitebacground
import com.miggue.mylogin01.ui.theme.*
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FatherOfAppsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

                    val items = ArrayList<OnBoardingData>()

                    items.add(
                        OnBoardingData(
                            R.drawable.ic_onboarding_start,
                            "Shop Awesome Products",
                            "We have products in different categories including Apparels, Electronics, Accessories, Footwear etc."
                        )
                    )
                    items.add(
                        OnBoardingData(
                            R.drawable.ic_ondoarding_sencond,
                            "Shop Awesome Products",
                            "We have products in different categories including Apparels, Electronics, Accessories, Footwear etc."
                        )
                    )
                    items.add(
                        OnBoardingData(
                            R.drawable.img_onboarding,
                            "Shop Awesome Products",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras netus mauris pulvinar suspendisse. Et sit ac lacus in rhoncus."
                        )
                    )



                    var pagerState = rememberPagerState(
                        pageCount = items.size,
                        initialOffscreenLimit = 2,
                        infiniteLoop = false,
                        initialPage = 0,
                    )

                    OnBoardingPager(
                        item = items, pagerState = pagerState, modifier = Modifier
                            .fillMaxWidth()
                            .background(color = whitebacground)
                    )

                }
            }
        }
}

    @ExperimentalPagerApi
    @Composable
    fun rememberPagerState(
        @androidx.annotation.IntRange(from = 0) pageCount: Int,
        @androidx.annotation.IntRange(from = 0) initialPage: Int = 0,
        @FloatRange(from = 0.0, to = 1.0) initialPageOffset: Float = 0f,
        @androidx.annotation.IntRange(from = 1) initialOffscreenLimit: Int = 1,
        infiniteLoop: Boolean = false
    ): PagerState = rememberSaveable(saver = PagerState.Saver) {
        PagerState(
            pageCount = pageCount,
            currentPage = initialPage,
            currentPageOffset = initialPageOffset,
            offscreenLimit = initialOffscreenLimit,
            infiniteLoop = infiniteLoop
        )
    }
    @Composable
    fun PagerIndicator(size: Int, currentPage: Int) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 10.dp)
        ) {
            repeat(size) {
                Indicator(isSelected = it == currentPage)
            }
        }
    }
    @Composable
    fun Indicator(isSelected: Boolean) {
        val width = animateDpAsState(targetValue = if (isSelected) 25.dp else 10.dp)
        Box(
            modifier = Modifier
                .padding(1.dp)
                .height(10.dp)
                .width(width.value)
                .clip(CircleShape)
                .background(
                    if (isSelected) MaterialTheme.colors.primary else BlueText.copy(alpha = 0.5f)
                )
        )
    }
    @ExperimentalPagerApi
    @Composable
    fun OnBoardingPager(
        item: List<OnBoardingData>,
        pagerState: PagerState,
        modifier: Modifier = Modifier,
    ) {

        Box(modifier = modifier) {
            Column(Modifier.fillMaxHeight(0.85f),
                verticalArrangement=Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                HorizontalPager(state = pagerState) { page ->
                    Column(
                        modifier = Modifier
                            .padding(top = 60.dp)

                            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = item[page].image),
                            contentDescription = item[page].title,
                            modifier = Modifier
                                .height(350.dp)
                                .fillMaxWidth()
                        )
                        Text(
                            text = item[page].desc,
                            modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 30.dp),
                            color = Color.Gray,
                            style = Typographyonboar.body1,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )

                    }
                }
                PagerIndicator(item.size, pagerState.currentPage)
            }

            Box(modifier = Modifier.align(Alignment.BottomCenter)){
                BottomSection(pagerState.currentPage)
            }
        }
    }

    @Composable
    fun BottomSection(currentPager: Int) {
        val context = LocalContext.current
        Row(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement =  Arrangement.Center
        ) {

            if (currentPager == 2){
                OutlinedButton(
                    onClick = {
                        var intent: Intent = Intent(context, Login::class.java)
                        context.startActivity(intent)
                    },
                    shape = RoundedCornerShape(50), // = 40% percent
                ) {
                    Text(
                        text = "Get Started",
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                        color = blueText,
                        fontWeight= FontWeight.Bold
                    )
                }
            }else{
                SkipNextButton("Skip",Modifier.padding(start = 20.dp))
//                SkipNextButton("Next",Modifier.padding(end = 20.dp))
            }

        }
    }
    @Composable
    fun SkipNextButton(text: String, modifier: Modifier) {
        val context = LocalContext.current
        TextButton(onClick = {
            var intent: Intent = Intent(context, Login::class.java)
            context.startActivity(intent)
        }){
            Text(
                text = text, color = blueText, modifier = modifier, fontSize = 18.sp,
                style = Typographyonboar.body1,
                fontWeight = FontWeight.Medium
            )
        }


    }

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    FatherOfAppsTheme {
        //Greeting("Android")
    }
}