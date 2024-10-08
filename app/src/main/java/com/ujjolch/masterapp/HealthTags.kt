package com.ujjolch.masterapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import com.example.masterapp.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BlueTag(text: String,) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val TagWidth = if(screenWidth<600.dp) (screenWidth.value/3.275).dp else  (screenWidth.value/6.275).dp
    Box(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.Slider_Blue),
                shape = RoundedCornerShape(16.dp)
            )
            .width(TagWidth)
            .height(30.dp)

            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row (Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
            Text(
                text = text,
                fontSize = 12.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}
@Composable
fun GreenTag(text: String) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val TagWidth = if(screenWidth<600.dp) (screenWidth.value/3.275).dp else  (screenWidth.value/6.275).dp
    Box(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.Slider_Green),
                shape = RoundedCornerShape(16.dp)
            )
            .width(TagWidth)
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) {
        Row (Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = text,
                fontSize = 12.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}
@Composable
fun OrangeTag(text: String) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val TagWidth = if(screenWidth<600.dp) (screenWidth.value/3.275).dp else  (screenWidth.value/6.275).dp
    Box(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.Slider_Orange),
                shape = RoundedCornerShape(16.dp)
            )
            .width(TagWidth)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row (Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = text,
                fontSize = 12.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun RedTag(text: String) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val TagWidth = if(screenWidth<600.dp) (screenWidth.value/3.275).dp else  (screenWidth.value/6.275).dp
    Box(
        modifier = Modifier
            .background(color = Color.Red, shape = RoundedCornerShape(16.dp))
            .width(TagWidth)
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) {
        Row (Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = text,
                fontSize = 12.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}
@Composable
fun DarkGreenTag(text: String) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val TagWidth = if(screenWidth<600.dp) (screenWidth.value/3.275).dp else  (screenWidth.value/6.275).dp
    Box(
        modifier = Modifier
            .background(color = colorResource(id = R.color.Slider_DarkGreen), shape = RoundedCornerShape(16.dp))
            .width(TagWidth)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row (Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = text,
                fontSize = 12.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BlueTagPreview() {
   BlueTag(text = "Underweight")
}
@Preview(showBackground = true)
@Composable
fun GreenTagPreview() {
    GreenTag(text = "Standard")
}

@Preview(showBackground = true)
@Composable
fun BlueTagPreview2() {
    BlueTag(text = "Low")
}
