package me.weishu.kernelsu.ui.component.liquid

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.weishu.kernelsu.ui.theme.LocalLiquidGlass
import top.yukonga.miuix.kmp.shader.isRenderEffectSupported
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * Static frosted glass look for content surfaces (cards, list items).
 *
 * This deliberately does NOT sample any [top.yukonga.miuix.kmp.blur.LayerBackdrop]:
 * the backdrop that feeds the bottom/top bars is drawn from the page content, so a
 * card sampling it would end up drawing itself and recurse until the render tree
 * overflows the native stack. A semi-transparent surface plus a hairline highlight
 * gives a similar look with zero sampling.
 */
@Composable
fun Modifier.glassSurface(cornerRadius: Dp = 16.dp): Modifier {
    if (!LocalLiquidGlass.current || !isRenderEffectSupported()) return this
    return this.border(
        width = 1.dp,
        color = MiuixTheme.colorScheme.onSurface.copy(alpha = 0.08f),
        shape = RoundedCornerShape(cornerRadius),
    )
}

/**
 * Card color for the glass look: a translucent surface while liquid glass is
 * active, the regular opaque surface color otherwise.
 */
@Composable
fun glassCardColor(): Color =
    if (LocalLiquidGlass.current && isRenderEffectSupported()) {
        MiuixTheme.colorScheme.surface.copy(alpha = 0.72f)
    } else {
        MiuixTheme.colorScheme.surface
    }
