package es.genol.explicacionAlertDialog

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleAlertDialogScreen() {
    val showAlertDialog = rememberSaveable { mutableStateOf(false) }
    var showAlertDialogFunctional by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            showAlertDialog.value = true
        }) {
            Text(text = "Muestra un Alert Dialog vacío")
        }
        Button(onClick = {
            showAlertDialogFunctional = true
        }) {
            Text(text = "Muestra un Alert Dialog funcional")
        }
    }

    SimpleAlertDialog(show = showAlertDialog)
    SimpleAlertDialogFunctional(
        show = showAlertDialogFunctional,
        onDismiss = { showAlertDialogFunctional = false },
        onConfirm = { showAlertDialogFunctional = false },
        confirmText = "Confirmar",
        dismissText = "Cancelar",
        title = "Alerta muy simple",
        text = "Esto es una alerta muy simple generada con Jetpack Compose"
    )
}

@Composable
fun SimpleAlertDialog(show: MutableState<Boolean>) {
    if (show.value) {
        AlertDialog(
            onDismissRequest = { show.value = false },
            confirmButton = { }
        )
    }
}

@Composable
fun SimpleAlertDialogFunctional(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    confirmText: String = "Confirm",
    dismissText: String = "Dismiss",
    title: String = "Title",
    text: String = "Text"
) {
    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                Button(onClick = onConfirm) {
                    Text(text = confirmText)
                }
            },
            modifier = modifier,
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text(text = dismissText)
                }
            },
            title = { Text(text = title) },
            text = { Text(text = text) },
        )
    }
}

@Composable
fun AlertDialogReturnScreen() {
    var showAlertDialogFunctional by rememberSaveable { mutableStateOf(false) }
    var mainText by rememberSaveable { mutableStateOf("Texto principal") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = mainText)
        Spacer(modifier = Modifier.height(100.dp))
        Button(onClick = {
            showAlertDialogFunctional = true
        }) {
            Text(text = "Muestra un Alert Dialog funcional")
        }
    }

    AlertDialogFunctional(
        show = showAlertDialogFunctional,
        onDismiss = { showAlertDialogFunctional = false },
        onConfirm = {
            mainText = it
            showAlertDialogFunctional = false
        },
        confirmText = "Confirmar",
        dismissText = "Cancelar",
        title = "Alerta con retorno",
        inputText = mainText
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun AlertDialogFunctional(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    modifier: Modifier = Modifier,
    confirmText: String = "Confirm",
    dismissText: String = "Dismiss",
    title: String = "Title",
    inputText: String = ""
) {
    var inputText by mutableStateOf(inputText)
    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                Button(onClick = { onConfirm(inputText) }) {
                    Text(text = confirmText)
                }
            },
            modifier = modifier,
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text(text = dismissText)
                }
            },
            title = { Text(text = title) },
            text = {
                TextField(value = inputText, onValueChange = {
                    inputText = it
                })
            },
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TapGesturesScreen() {
    var showAlertDialogFunctional by rememberSaveable { mutableStateOf(false) }
    var mainText by rememberSaveable { mutableStateOf("Texto principal") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = mainText)
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "Muestra un Alert Dialog funcional", modifier = Modifier.combinedClickable(onClick = {}, onLongClick = {showAlertDialogFunctional = true}))
    }

    SimpleAlertDialogFunctional(
        show = showAlertDialogFunctional,
        onDismiss = { showAlertDialogFunctional = false },
        onConfirm = { showAlertDialogFunctional = false },
        confirmText = "Confirmar",
        dismissText = "Cancelar",
        title = "Alerta muy simple",
        text = "Esto es una alerta muy simple generada con Jetpack Compose"
    )

    AlertDialogFunctional(
        show = showAlertDialogFunctional,
        onDismiss = { showAlertDialogFunctional = false },
        onConfirm = {
            mainText = it
            showAlertDialogFunctional = false
        },
        confirmText = "Confirmar",
        dismissText = "Cancelar",
        title = "Alerta con retorno",
        inputText = mainText
    )
}