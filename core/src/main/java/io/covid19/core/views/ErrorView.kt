package io.covid19.core.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import io.covid19.core.R
import io.covid19.core.utils.hide
import io.covid19.core.utils.show
import com.google.android.material.button.MaterialButton

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var onRetryClick: (() -> Unit)? = null

    private var retryButton: MaterialButton? = null
    private var errorMessageTextView: AppCompatTextView? = null

    init {
        View.inflate(context, R.layout.view_error, this)
        hide()

        findViews()

        retryButton?.setOnClickListener {
            onRetryClick?.invoke()
            hide()
        }
    }

    private fun findViews() {
        retryButton = findViewById(R.id.button_error_view_retry)
        errorMessageTextView = findViewById(R.id.text_view_error_view_message)
    }

    fun errorMessage(message: String) {
        errorMessageTextView?.text = message
        show()
    }

    fun errorMessage(message: Int) {
        errorMessageTextView?.text = context?.getString(message)
        show()
    }

    fun setOnRetryClickListener(onRetryClick: (() -> Unit)?) {
        this.onRetryClick = onRetryClick
    }
}