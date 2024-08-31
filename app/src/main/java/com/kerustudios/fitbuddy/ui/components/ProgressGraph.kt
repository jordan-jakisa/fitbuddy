package com.kerustudios.fitbuddy.ui.components

import                                                  androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine


@Composable
fun ProgressGraph(modifier: Modifier = Modifier) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val pointsData: List<Point> =
        listOf(Point(1f, 90f), Point(2f, 40f), Point(3f, 60f), Point(4f, 80f), Point(5f, 40f))

    val xAxisData = AxisData.Builder()
        .axisStepSize((screenWidth / 7))
        .backgroundColor(MaterialTheme.colorScheme.surface)
        .steps(pointsData.size - 1)
        .labelData { i -> i.getDate() }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(pointsData.size - 1)
        .backgroundColor(MaterialTheme.colorScheme.surface)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = 100 / 25
            (i * yScale)
            i.toString()
        }.build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = MaterialTheme.colorScheme.surface
    )
    LineChart(
        modifier = modifier
            .fillMaxWidth(),
        lineChartData = lineChartData
    )
}

fun Int.getDate(): String {
    return when (this) {
        1 -> "Mon"
        2 -> "Tue"
        3 -> "Wed"
        4 -> "Thur"
        5 -> "Fri"
        6 -> "Sat"
        7 -> "Sun"
        else -> ""
    }
}