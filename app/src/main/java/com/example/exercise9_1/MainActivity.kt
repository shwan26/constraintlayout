package com.example.exercise9_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.exercise9_1.ui.theme.Exercise91Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Exercise91Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Exercise2(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Exercise1(modifier: Modifier) {
    ConstraintLayout (modifier = Modifier.fillMaxSize()) {
        val (box1, box2, box3, box4) = createRefs()
        BoxItem(1, Modifier.constrainAs(box1) {
            start.linkTo(parent.start, 16.dp)
            top.linkTo(parent.top, 16.dp)
        })
        BoxItem(2, Modifier.constrainAs(box2) {
            start.linkTo(box1.end, 16.dp)
            top.linkTo(box1.top)
        })
        BoxItem(3, Modifier.constrainAs(box3) {
            start.linkTo(box2.end, 16.dp)
            top.linkTo(box2.top)
        })
        BoxItem(4, Modifier.constrainAs(box4) {
            start.linkTo(box3.end, 16.dp)
            top.linkTo(box3.top)
        })
    }

}
@Composable
fun BoxItem(counter: Int, modifier: Modifier) {
    Box(modifier = modifier.border(width = 2.dp, color = Color.Black).size(60.dp)) {
        Column {
            Text(text = counter.toString(),
                modifier.fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally))
            Text(text = "Item $counter",
                modifier = Modifier.fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally))
        }

    }
}

@Composable
fun Exercise2(modifier: Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (textA, textB, textC) = createRefs()
        val startGuideLine = createGuidelineFromStart(16.dp)
        val endGuideLine = createGuidelineFromEnd(16.dp)
        val topGuideLine = createGuidelineFromTop(16.dp)
        val bottomGuideLine = createGuidelineFromBottom(16.dp)
        val verticalGuideLine = createGuidelineFromStart(0.5f)
        val horizontalGuideLine = createGuidelineFromTop(0.5f)
        Text(text = "A", modifier = Modifier.constrainAs(textA) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            start.linkTo(startGuideLine)
            end.linkTo(verticalGuideLine)
            top.linkTo(topGuideLine)
            bottom.linkTo(horizontalGuideLine)
        }.background(Color.Yellow). wrapContentSize(Alignment.Center))
        Text(text = "B", modifier = Modifier.constrainAs(textB) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            start.linkTo(verticalGuideLine)
            end.linkTo(endGuideLine)
            top.linkTo(topGuideLine)
            bottom.linkTo(horizontalGuideLine)
        }.background(Color.Green).wrapContentSize(Alignment.Center))
        Text(text = "C", modifier = Modifier.constrainAs(textC) {
            start.linkTo(startGuideLine)
            end.linkTo(endGuideLine)
            top.linkTo(horizontalGuideLine)
        })
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Exercise91Theme {
        Exercise2(modifier = Modifier)
    }
}