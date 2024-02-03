package com.xendv.storeroom.ui.scanner

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.CompoundBarcodeView
import com.xendv.storeroom.ui.cards.TopSurfaceCardShape
import com.xendv.storeroom.ui.colors.paleGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalScanner(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    ),
    scanResultCallback: (String) -> Unit,
    onDismissRequest: () -> Unit = {},
) {
    ModalBottomSheet(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f),
        sheetState = sheetState,
        dragHandle = null,
        onDismissRequest = onDismissRequest,
        shape = TopSurfaceCardShape,
    ) {
        Box {
            ScanView(
                modifier = Modifier
                    .fillMaxSize(),
                scanResultCallback = scanResultCallback,
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
                    .clip(CircleShape)
                    .background(color = paleGray.copy(alpha = 0.3f))
                    .size(
                        height = 6.dp,
                        width = 60.dp,
                    ),
            )
        }
    }
}

@Composable
fun ScanView(
    modifier: Modifier = Modifier,
    scanResultCallback: (String) -> Unit,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val compoundBarcodeView = remember {
        CompoundBarcodeView(context).apply {
            val capture = CaptureManager(context as Activity, this)
            capture.initializeFromIntent(context.intent, null)
            this.setStatusText("")
            capture.decode()
            this.decodeContinuous { result ->
                result.text?.let { text ->
                    this.pause()
                    scanResultCallback(text)
                }
            }
        }
    }
    AndroidView(
        factory = { compoundBarcodeView },
        modifier = modifier,
    )
    DisposableEffect(Unit) {
        focusManager.clearFocus(force = true)
        compoundBarcodeView.resume()
        onDispose {
            compoundBarcodeView.pause()
        }
    }
}