package com.example.clocks.view

import android.content.Context
import android.graphics.*
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.clocks.R
import java.util.*
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.properties.Delegates

class ClockView(context: Context, private val attributeSet: AttributeSet) : View(context, attributeSet) {

    private val clockNumbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

    private lateinit var canvas: Canvas

    private var clockAngle = ((Math.PI/30) - (Math.PI/2))

    private lateinit var clockPaint: Paint
    private lateinit var clockRect: Rect

    private var clockSeconds by Delegates.notNull<Int>()
    private var clockMinutes by Delegates.notNull<Int>()
    private var clockHours by Delegates.notNull<Int>()

    private var clockCircleColor by Delegates.notNull<Int>()
    var cirlceColor
        get() = clockCircleColor
        set(value) {
            clockCircleColor = value
        }

    private var clockCircleInnerColor by Delegates.notNull<Int>()
    var cirlceInnerColor
        get() = clockCircleInnerColor
        set(value) {
            clockCircleInnerColor = value
        }

    private var clockHandHourColor by Delegates.notNull<Int>()
    var handHourColor
        get() = clockHandHourColor
        set(value) {
            clockHandHourColor = value
        }

    private var clockHandMinuteColor by Delegates.notNull<Int>()
    var handMinuteColor
        get() = clockHandMinuteColor
        set(value) {
            clockHandMinuteColor = value
        }

    private var clockHandSecondColor by Delegates.notNull<Int>()
    var handSecondColor
        get() = clockHandSecondColor
        set(value) {
            clockHandSecondColor = value
        }

    private var clockRadius by Delegates.notNull<Float>()
    private var clockWidth by Delegates.notNull<Float>()
    private var clockHeight by Delegates.notNull<Float>()
    private var clockMinSide by Delegates.notNull<Float>()

    private var clockCenterX by Delegates.notNull<Float>()
    private var clockCenterY by Delegates.notNull<Float>()

    private var clockHandHourRadius by Delegates.notNull<Float>()
    private var clockHandMinuteRadius by Delegates.notNull<Float>()
    private var clockHandSecondRadius by Delegates.notNull<Float>()

    private var clockHandHourWidth by Delegates.notNull<Float>()
    var handHourWidth
        get() = clockHandHourWidth
        set(value) {
            clockHandHourWidth = value
        }

    private var clockHandMinuteWidth by Delegates.notNull<Float>()
    var handMinuteWidth
        get() = clockHandMinuteWidth
        set(value) {
            clockHandMinuteWidth = value
        }

    private var clockHandSecondWidth by Delegates.notNull<Float>()
    var handSecondWidth
        get() = clockHandSecondWidth
        set(value) {
            clockHandSecondWidth = value
        }

    private var clockCircleWidth by Delegates.notNull<Float>()
    var circleWidth
        get() = clockCircleWidth
        set(value) {
            clockCircleWidth = value
        }

    private var clockMarkerLength by Delegates.notNull<Float>()
    var markerLength
        get() = clockMarkerLength
        set(value) {
            clockMarkerLength = value
        }

    private var clockMarkerWidth by Delegates.notNull<Float>()
    var markerWidth
        get() = clockMarkerWidth
        set(value) {
            clockMarkerWidth = value
        }

    private var clockMarkerSpecialLength by Delegates.notNull<Float>()
    var markerSpecialLength
        get() = clockMarkerSpecialLength
        set(value) {
            clockMarkerSpecialLength = value
        }

    private var clockMarkerSpecialWidth by Delegates.notNull<Float>()
    var markerSpecialWidth
        get() = clockMarkerSpecialWidth
        set(value) {
            clockMarkerSpecialWidth = value
        }

    private var clockNumberTextSize by Delegates.notNull<Float>()
    var numberTextSize
        get() = clockNumberTextSize
        set(value) {
            clockNumberTextSize = value
        }

    private var isInit = false

    init {
        val style = context.obtainStyledAttributes(attributeSet, R.styleable.ClockView)
        clockCircleColor = style.getColor(R.styleable.ClockView_clock_circle_color, Color.BLACK)
        clockCircleInnerColor = style.getColor(R.styleable.ClockView_clock_circle_inner_color, Color.TRANSPARENT)

        clockHandSecondColor = style.getColor(R.styleable.ClockView_clock_hand_second_color, Color.RED)
        clockHandMinuteColor = style.getColor(R.styleable.ClockView_clock_hand_minute_color, Color.BLACK)
        clockHandHourColor = style.getColor(R.styleable.ClockView_clock_hand_hour_color, Color.BLACK)
        style.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (!isInit) {
            this.canvas = canvas!!
            onDrawClock()
        }

        clockRadius = clockMinSide / 2 - clockCircleWidth / 2
        clockHandSecondRadius =  (clockRadius  - clockCircleWidth / 2) * 0.7f
        clockHandMinuteRadius = (clockRadius - clockCircleWidth / 2) * 0.5f
        clockHandHourRadius = (clockRadius - clockCircleWidth / 2) * 0.3f

        drawCircle(canvas!!)
        drawHands(canvas)
        drawNumerals(canvas)

        postInvalidateDelayed(500)
    }

    private fun onDrawClock() {
        clockWidth = width.toFloat()
        clockHeight = height.toFloat()

        clockCenterX = clockWidth / 2
        clockCenterY = clockHeight / 2

        clockMinSide = min(clockHeight, clockWidth)
        clockRadius = clockMinSide / 2

        clockPaint = Paint()
        clockRect = Rect()

        initStyles()

        isInit = true
    }

    private fun initStyles() {
        val style = context.obtainStyledAttributes(attributeSet, R.styleable.ClockView)

        clockCircleWidth = style.getDimension(R.styleable.ClockView_clock_circle_width, clockMinSide / dpToPx(411f) * dpToPx(3f))

        clockNumberTextSize = style.getDimension(R.styleable.ClockView_clock_number_text_size, clockMinSide / dpToPx(411f) * dpToPx(22f))

        clockHandSecondWidth = style.getDimension(R.styleable.ClockView_clock_hand_second_width, clockMinSide / dpToPx(411f) * dpToPx(2.5f))
        clockHandMinuteWidth = style.getDimension(R.styleable.ClockView_clock_hand_minute_width, clockMinSide / dpToPx(411f) * dpToPx(5f))
        clockHandHourWidth = style.getDimension(R.styleable.ClockView_clock_hand_hour_width, clockMinSide / dpToPx(411f) * dpToPx(7f))

        clockMarkerLength = style.getDimension(R.styleable.ClockView_clock_marker_length, clockMinSide / dpToPx(411f) * dpToPx(5f))
        clockMarkerWidth = style.getDimension(R.styleable.ClockView_clock_marker_width, clockMinSide / dpToPx(411f) * dpToPx(2.5f))

        clockMarkerSpecialLength = style.getDimension(R.styleable.ClockView_clock_marker_special_length, clockMinSide / dpToPx(411f) * dpToPx(10f))
        clockMarkerSpecialWidth = style.getDimension(R.styleable.ClockView_clock_marker_special_width, clockMinSide / dpToPx(411f) * dpToPx(5f))

        style.recycle()

    }

    private fun drawCircle(canvas: Canvas) {
        setPaintAttributes(clockCircleColor, Paint.Style.STROKE, clockCircleWidth)
        canvas.drawCircle(clockCenterX, clockCenterY, clockRadius, clockPaint)

        setPaintAttributes(clockCircleInnerColor, Paint.Style.FILL, clockCircleWidth)
        canvas.drawCircle(clockCenterX, clockCenterY, clockRadius - (clockCircleWidth / 2), clockPaint)

        drawMarkers(canvas)
    }

    private fun drawMarkers(canvas: Canvas) {
        for(i: Int in 0 until 60) {
            val angle = Math.PI * i / 30 - Math.PI / 2
            when(i % 5) {
                0 -> drawMarkerSpecial(canvas, angle)
                else -> drawMarker(canvas, angle)
            }
        }
    }

    private fun drawMarkerSpecial(
        canvas: Canvas,
        angle: Double,
    ) {
        setPaintAttributes(Color.BLACK, Paint.Style.STROKE, clockMarkerSpecialWidth)
        canvas.drawLine(
            clockCenterX + (clockRadius - clockMarkerSpecialLength - clockCircleWidth / 2) * cos(angle).toFloat(),
            clockCenterY + (clockRadius - clockMarkerSpecialLength - clockCircleWidth / 2) * sin(angle).toFloat(),
            clockCenterX + (clockRadius - clockCircleWidth / 2) * cos(angle).toFloat(),
            clockCenterY + (clockRadius - clockCircleWidth / 2) * sin(angle).toFloat(),
            clockPaint)
    }

    private fun drawMarker(
        canvas: Canvas,
        angle: Double
    ) {
        setPaintAttributes(Color.BLACK, Paint.Style.STROKE, clockMarkerWidth)
        canvas.drawLine(
            clockCenterX + (clockRadius - clockMarkerLength - clockCircleWidth / 2) * cos(angle).toFloat(),
            clockCenterY + (clockRadius - clockMarkerLength - clockCircleWidth / 2) * sin(angle).toFloat(),
            clockCenterX + (clockRadius - clockCircleWidth / 2) * cos(angle).toFloat(),
            clockCenterY + (clockRadius - clockCircleWidth / 2) * sin(angle).toFloat(),
            clockPaint)
    }

    private fun setPaintAttributes(color: Int, stroke: Paint.Style, strokeWidth: Float) {
        clockPaint.reset()
        clockPaint.color = color
        clockPaint.style = stroke
        clockPaint.strokeWidth = strokeWidth
        clockPaint.isAntiAlias = true
    }

    private fun drawHands(canvas: Canvas) {
        val calendar = Calendar.getInstance()

        clockHours = calendar.get(Calendar.HOUR_OF_DAY) % 12
        clockMinutes = calendar.get(Calendar.MINUTE)
        clockSeconds = calendar.get(Calendar.SECOND)

        drawHandHour(canvas, (clockHours + clockMinutes / 60f) * 5f)
        drawHandMinute(canvas, clockMinutes.toFloat())
        drawHandSecond(canvas, clockSeconds.toFloat())

    }

    private fun drawHandHour(canvas: Canvas, location: Float) {
        setPaintAttributes(clockHandHourColor, Paint.Style.STROKE, clockHandHourWidth)
        clockAngle = Math.PI * location / 30 - Math.PI / 2
        canvas.drawLine(clockCenterX, clockCenterY, clockCenterX + cos(clockAngle).toFloat() * clockHandHourRadius
            , clockCenterY + sin(clockAngle).toFloat() * clockHandHourRadius, clockPaint)
    }

    private fun drawHandMinute(canvas: Canvas, location: Float) {
        setPaintAttributes(clockHandMinuteColor, Paint.Style.STROKE, clockHandMinuteWidth)
        clockAngle = Math.PI * location / 30 - Math.PI / 2
        canvas.drawLine(clockCenterX, clockCenterY, clockCenterX + cos(clockAngle).toFloat() * clockHandMinuteRadius
            , clockCenterY + sin(clockAngle).toFloat() * clockHandMinuteRadius, clockPaint)
    }

    private fun drawHandSecond(canvas: Canvas, location: Float) {
        setPaintAttributes(clockHandSecondColor, Paint.Style.STROKE, clockHandSecondWidth)
        clockPaint.color = clockHandSecondColor
        clockAngle = Math.PI * location / 30 - Math.PI / 2
        canvas.drawLine(clockCenterX, clockCenterY, clockCenterX + cos(clockAngle).toFloat() * clockHandSecondRadius
            , clockCenterY + sin(clockAngle).toFloat() * clockHandSecondRadius, clockPaint)
    }

    private fun drawNumerals(canvas: Canvas) {
        setPaintAttributes(Color.BLACK, Paint.Style.FILL_AND_STROKE,5f)
        clockPaint.textSize = clockNumberTextSize

        for (i in 0..11) {
            val number = clockNumbers[i]
            val num = number.toString()

            clockPaint.getTextBounds(num, 0, num.length, clockRect)

            val angle = Math.PI / 6 * (number - 3)
            val x = (clockCenterX + cos(angle) * (clockRadius - clockNumberTextSize / 2 - clockMarkerSpecialLength - 4 - clockCircleWidth / 2) - clockRect.width() / 2).toFloat()
            val y = (clockCenterY + sin(angle) * (clockRadius - clockNumberTextSize / 2 - clockMarkerSpecialLength - 4 - clockCircleWidth / 2) + clockRect.height() / 2).toFloat()

            canvas.drawText(num, x, y, clockPaint)
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()

        bundle.putParcelable("superState", super.onSaveInstanceState())

        bundle.putInt("circleInnerColor", clockCircleInnerColor)
        bundle.putInt("circleColor", clockCircleColor)

        bundle.putInt("handHourColor", clockHandHourColor)
        bundle.putInt("handMinuteColor", clockHandMinuteColor)
        bundle.putInt("handSecondColor", clockHandSecondColor)

        bundle.putFloat("markerLength", clockMarkerLength)
        bundle.putFloat("markerWidth", clockMarkerWidth)

        bundle.putFloat("markerSpecialLength", clockMarkerSpecialLength)
        bundle.putFloat("markerSpecialWidth", clockMarkerSpecialWidth)

        bundle.putFloat("handHourWidth", clockHandHourWidth)
        bundle.putFloat("handMinuteWidth", clockHandMinuteWidth)
        bundle.putFloat("handSecondWidth", clockHandSecondWidth)

        bundle.putFloat("numberTextSize", clockNumberTextSize)

        bundle.putFloat("circleWidth", clockCircleWidth)

        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var superState: Parcelable? = null
        if (state is Bundle) {
            clockCircleInnerColor = state.getInt("circleInnerColor")
            clockCircleColor = state.getInt("circleColor")

            clockHandHourColor = state.getInt("handHourColor")
            clockHandMinuteColor = state.getInt("handMinuteColor")
            clockHandSecondColor = state.getInt("handSecondColor")

            clockMarkerLength = state.getFloat("markerLength")
            clockMarkerWidth = state.getFloat("markerWidth")

            clockMarkerSpecialLength = state.getFloat("markerSpecialLength")
            clockMarkerSpecialWidth = state.getFloat("markerSpecialWidth")

            clockHandHourWidth = state.getFloat("handHourWidth")
            clockHandMinuteWidth = state.getFloat("handMinuteWidth")
            clockHandSecondWidth = state.getFloat("handSecondWidth")

            clockNumberTextSize = state.getFloat("numberTextSize")

            clockCircleWidth = state.getFloat("circleWidth")

            superState =
                if (SDK_INT >= 33) state.getParcelable("superState", Parcelable::class.java)
                else state.getParcelable("superState")
        }
        super.onRestoreInstanceState(superState)
    }

    private fun dpToPx(dp: Float) = dp * context.resources.displayMetrics.density

}