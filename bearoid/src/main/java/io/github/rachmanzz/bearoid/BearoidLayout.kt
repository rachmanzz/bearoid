package io.github.rachmanzz.bearoid

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout

class BearoidLayout (context: Context){
    var context: Context? = null
    var linearLy: Linear? = null
    var relativeLy: Relative? = null
    var currentLayout: String? = null
    init {
        this.context = context
    }
    fun linear(): BearoidLayout {
        currentLayout = "ln"
        this.linearLy = context?.let { Linear(it) }
        return this
    }
    fun relative(): BearoidLayout {
        currentLayout = "rl"
        this.relativeLy = context?.let { Relative(it) }
        return this
    }

    fun setBackground(bg: android.graphics.drawable.Drawable): BearoidLayout {
        if (currentLayout === "ln") this.linearLy?.background = bg
        if (currentLayout === "rl") this.relativeLy?.background = bg
        return this
    }

    fun getLinear (): Linear? { return this.linearLy }
    fun getRelative (): Relative? { return this.relativeLy }

    class Linear(context: Context): LinearLayout(context){
        var lyParams: LayoutParams? = null
        init {
            lyParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutParams = this.lyParams

        }
        fun vertical(): Linear {
            orientation = VERTICAL
            return this
        }
        fun horizontal(): Linear {
            orientation = HORIZONTAL
            return this
        }

        fun setWeight (weight: Float): Linear {
            lyParams?.weight = weight
            return this
        }

        fun layoutHeight (params: Int): Linear {
            lyParams?.height = params
            return this
        }
        fun layoutWidth (params: Int): Linear {
            lyParams?.width = params
            return this
        }




        fun apply (): Linear {
            layoutParams = lyParams
            return this
        }

    }
    class Relative(context: Context): RelativeLayout(context){
        var lyParams: LinearLayout.LayoutParams? = null
        init {
            lyParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutParams = this.lyParams

        }

        fun setWeight (weight: Float): Relative {
            lyParams?.weight = weight
            return this
        }

        fun layoutHeight (params: Int): Relative {
            lyParams?.height = params
            return this
        }
        fun layoutWidth (params: Int): Relative {
            lyParams?.width = params
            return this
        }

        fun apply (): Relative {
            layoutParams = lyParams
            return this
        }
    }

    class Drawable(context: Context) {
        var resources: Resources? = null
        init {
            this.resources = context.resources
        }
        fun set(res: Int): android.graphics.drawable.Drawable? {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) return resources?.getDrawable(res, null)
            return return resources?.getDrawable(res)
        }
    }




    fun MATCH_PARENT(): Int {
        return ViewGroup.LayoutParams.MATCH_PARENT
    }
    fun WRAP_CONTENT(): Int {
        return ViewGroup.LayoutParams.WRAP_CONTENT
    }
}