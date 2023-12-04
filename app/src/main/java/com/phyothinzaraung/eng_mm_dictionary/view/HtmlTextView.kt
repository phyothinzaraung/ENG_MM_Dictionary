package com.phyothinzaraung.eng_mm_dictionary.view

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun HtmlTextView(htmlContent: String) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                loadDataWithBaseURL(null, htmlContent, "text/html", "utf-8", null)
            }
        }
    )
}