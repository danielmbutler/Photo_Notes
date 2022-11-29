package com.dbtechprojects.photonotes.ui

import androidx.compose.foundation.clickable
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun GenericAppBar(
    title: String,
    onIconClick: (() -> Unit)?,
    icon: @Composable() (() -> Unit)?,
    onTitleClick: (() -> Unit)? = null,
    iconState: MutableState<Boolean>
) {
    TopAppBar(
        title = { Text(title, Modifier.clickable { onTitleClick?.invoke() }) },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            IconButton(
                onClick = {
                    onIconClick?.invoke()
                },
                content = {
                    if (iconState.value){
                        icon?.invoke()
                    }
                }

            )
        }
    )
}