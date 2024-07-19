package com.orcchg.yandexcontest.search_bar.ui

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.OverScroller
import androidx.core.content.res.use
import androidx.core.view.GestureDetectorCompat
import com.orcchg.yandexcontest.design.R as Design

class SearchFlowLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private var horizontalSpacing = 0
    private var verticalSpacing = 0
    private var endSideLedge = 0

    private val gestureListener = SearchFlowGestureListener()
    private val gestureDetector = GestureDetectorCompat(context, gestureListener)
    private val scroller = OverScroller(context)

    var onItemClickListener: OnItemClickListener? = null

    init {
        val defaultHorizontalSpacing = context.resources.getDimensionPixelSize(Design.dimen.keyline_1)
        val defaultVerticalSpacing = context.resources.getDimensionPixelSize(Design.dimen.keyline_2)

        context.obtainStyledAttributes(attrs, R.styleable.SearchFlowLayout, defStyleAttr, 0)
            .use {
                horizontalSpacing = it.getDimensionPixelSize(
                    R.styleable.SearchFlowLayout_search_flow_layout_horizontalSpacing,
                    defaultHorizontalSpacing
                )
                verticalSpacing = it.getDimensionPixelSize(
                    R.styleable.SearchFlowLayout_search_flow_layout_verticalSpacing,
                    defaultVerticalSpacing
                )
                endSideLedge = it.getDimensionPixelSize(R.styleable.SearchFlowLayout_search_flow_layout_endSideLedge, 0)
            }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val startPadding = maxOf(paddingLeft, paddingStart)
        var xpos = startPadding
        var ypos = paddingTop
        val width = r - l
        val availableWidth = width + endSideLedge
        var row = 0
        var maxChildHeight = 0

        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            if (childView.visibility == View.GONE) {
                continue
            }

            maxChildHeight = maxOf(maxChildHeight, childView.measuredHeight + verticalSpacing)

            if (xpos + childView.measuredWidth > availableWidth) { // new line
                xpos = startPadding
                ypos += maxChildHeight
                ++row
            }

            childView.layout(
                xpos,
                ypos,
                xpos + childView.measuredWidth,
                ypos + childView.measuredHeight
            )
            xpos += childView.measuredWidth + horizontalSpacing
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val startPadding = maxOf(paddingLeft, paddingStart)
        var xpos = startPadding
        var ypos = paddingTop
        val width: Int = MeasureSpec.getSize(widthMeasureSpec) - xpos - maxOf(
            paddingRight,
            paddingEnd
        )
        val availableWidth = width + endSideLedge
        var height: Int = MeasureSpec.getSize(heightMeasureSpec) - paddingTop - paddingBottom
        val childViewWidthSpec = MeasureSpec.makeMeasureSpec(availableWidth, MeasureSpec.AT_MOST)
        val childViewHeightSpec =
            when (MeasureSpec.getMode(heightMeasureSpec)) {
                MeasureSpec.AT_MOST -> MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST)
                else -> MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            }

        var row = 0
        var maxChildHeight = 0

        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            if (childView.visibility == View.GONE) {
                continue
            }

            childView.measure(childViewWidthSpec, childViewHeightSpec)
            maxChildHeight = maxOf(maxChildHeight, childView.measuredHeight + verticalSpacing)

            if (xpos + childView.measuredWidth > availableWidth) { // new line
                xpos = startPadding
                ypos += maxChildHeight
                ++row
            }

            xpos += childView.measuredWidth + horizontalSpacing
        }

        when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.AT_MOST -> if (height > ypos + maxChildHeight) {
                height = ypos + maxChildHeight
            }
            MeasureSpec.UNSPECIFIED -> height = ypos + maxChildHeight
            else -> { /* no-op */ }
        }

        setMeasuredDimension(width, height)
    }

    // Touches & Scroll
    @Suppress("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        fun up(): Boolean = when (event.actionMasked) {
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> true
            else -> false
        }

        if (event.actionMasked == MotionEvent.ACTION_DOWN &&
            !scroller.isFinished
        ) {
            scroller.abortAnimation()
        }

        gestureDetector.onTouchEvent(event)

        // TODO: fix scrolling beyond flow view
        if (up()) {
            var newScrollX = scrollX
            if (scrollX < 0) {
                newScrollX = 0
            } else if (scrollX > endSideLedge) {
                newScrollX = endSideLedge
            }
            var newScrollY = scrollY
            if (scrollY < 0) {
                newScrollY = 0
            } else if (scrollY > 0) {
                newScrollY = 0
            }
            if (newScrollX != scrollX || newScrollY != scrollY) {
                scroller.startScroll(scrollX, scrollY, newScrollX - scrollX, newScrollY - scrollY)
            }
        }

        if (event.actionMasked == MotionEvent.ACTION_UP) {
            performClickOnChildView(event)
        }

        return true
    }

    override fun computeHorizontalScrollRange(): Int = width + endSideLedge

    override fun computeScroll() {
        if (scroller.computeScrollOffset()) {
            val oldX = scrollX
            val oldY = scrollY
            val x = scroller.currX
            val y = scroller.currY
            scrollTo(x, y)
            if (oldX != scrollX || oldY != scrollY) {
                onScrollChanged(scrollX, scrollY, oldX, oldY)
            }
            postInvalidate()
        }
        super.computeScroll()
    }

    private fun performClickOnChildView(event: MotionEvent) {
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            if (childView.visibility != View.VISIBLE) {
                continue
            }

            val roi = Rect()
            childView.getHitRect(roi)
            if (roi.contains(event.x.toInt(), event.y.toInt())) {
                onItemClickListener?.onClick(childView)
                break
            }
        }
    }

    fun interface OnItemClickListener {
        fun onClick(view: View)
    }

    private inner class SearchFlowGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean = true

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val availableWidth = width + endSideLedge
            if (((scrollX < 0) || (scrollX > availableWidth) || (scrollY < 0) || (scrollY > height))) {
                return false
            }

            scroller.fling(
                scrollX,
                scrollY,
                -velocityX.toInt(),
                -velocityY.toInt(),
                0,
                endSideLedge,
                0,
                0,
                36,
                0
            )

            return true
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            scrollBy(distanceX.toInt(), 0) // only horizontal scroll
            return true
        }
    }
}
