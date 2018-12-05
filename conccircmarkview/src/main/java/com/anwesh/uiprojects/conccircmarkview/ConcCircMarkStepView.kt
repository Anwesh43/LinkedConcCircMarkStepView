package com.anwesh.uiprojects.conccircmarkview

/**
 * Created by anweshmishra on 05/12/18.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.graphics.RectF
import android.content.Context
import android.app.Activity

val nodes : Int = 5
val lines : Int = 6
val sizeFactor : Int = 3
val strokeFactor : Int = 90
val scDiv : Double = 0.51
val scGap : Float = 0.05f
val color : Int = Color.parseColor("#01579B")

fun Int.getInverse() : Float = 1f / this

fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.getInverse(), Math.max(0f, this - i * n.getInverse())) * n

fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()

fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.getInverse() + scaleFactor() * b.getInverse()

fun Float.updateScale(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * scGap * dir

fun Canvas.drawCCMSNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = w / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.color = color
    paint.strokeCap = Paint.Cap.ROUND
    save()
    translate(gap * (i + 1), h/2)
    drawCircle(0f, 0f, size, paint)
    drawArc(RectF(-size/3, -size/3, size/3, size/3), -90f, 360f * sc1, false, paint)
    val deg : Float = 360f / lines
    for (j in 0..(lines - 1)) {
        val sc : Float = sc2.divideScale(j, lines)
        save()
        rotate(deg * j)
        drawLine(0f, -size/3 - size/10, 0f, -size + size/10, paint)
        restore()
    }
    restore()
}