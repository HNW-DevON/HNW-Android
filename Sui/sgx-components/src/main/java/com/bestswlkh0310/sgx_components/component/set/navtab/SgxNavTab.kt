package com.bestswlkh0310.sgx_components.component.set.navtab

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bestswlkh0310.sgx_components.foundation.Text
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.LocalContentColor
import kotlin.math.max

/**
 * Dodam Bottom NavTab, just single tab, can use with navbar
 *
 * @param text name
 * @param icon main icon
 * @param onClick action when click tab
 * @param modifier modifier
 * @param selectedColor color when selected
 * @param selected state of navTab
 * @param interactionSource interactionSource
 */
@Composable
fun RowScope.SgxNavTab(
    text: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedColor: Color = SgxTheme.color.Black,
    selected: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val contentColor = if (selected) {
        selectedColor
    } else {
        SgxTheme.color.Gray200
    }

    val labelColor = if (selected) {
        selectedColor
    } else {
        SgxTheme.color.Gray400
    }

    val style = SgxTheme.typography.body3.copy(fontSize = 10.sp)

    CompositionLocalProvider(
        LocalContentColor provides contentColor
    ) {
        Box(
            modifier = modifier
                .selectable(
                    selected = selected,
                    onClick = onClick,
                    enabled = true,
                    role = Role.Tab,
                    interactionSource = interactionSource,
                    indication = null,
                )
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Layout(
                {
                    Box(Modifier.layoutId("icon")) { icon() }
                    Box(Modifier.layoutId("text")) {
                        Text(
                            text = text,
                            style = style,
                            color = labelColor,
                        )
                    }
                }
            ) { measurables, constraints ->
                val iconPlaceable = measurables.first { it.layoutId == "icon" }.measure(constraints)
                val textPlaceable = measurables.first { it.layoutId == "text" }.measure(
                    constraints.copy(minHeight = 0)
                )

                val height = constraints.maxHeight
                val containerWidth = max(textPlaceable.width, iconPlaceable.width)

                val baseline = textPlaceable[LastBaseline]
                val baselineOffset = 10.dp.roundToPx()

                val textY = height - baseline - baselineOffset

                val iconY = baselineOffset

                val iconX = (containerWidth - iconPlaceable.width) / 2

                val textX = (containerWidth - textPlaceable.width) / 2

                layout(containerWidth, height) {
                    textPlaceable.placeRelative(textX, textY)
                    iconPlaceable.placeRelative(iconX, iconY)
                }
            }
        }
    }
}
