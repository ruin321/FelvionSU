package me.weishu.kernelsu.ui.component.liquid

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.weishu.kernelsu.ui.theme.LocalLiquidGlass
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurColors
import top.yukonga.miuix.kmp.blur.LayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * Renders a static frosted glass surface behind the composable it is applied to.
 * The blur is sampled from the already rendered [backdrop], so scrolling never
 * rebuilds it. When liquid glass is disabled (or there is no backdrop) this is a
 * no-op and callers must fall back to their original colors.
 */
@Composable
fun Modifier.glassSurface(backdrop: LayerBackdrop?, cornerRadius: Dp = 16.dp): Modifier {
    if (backdrop == null || !LocalLiquidGlass.current) return this
    return this.textureBlur(
        backdrop = backdrop,
        shape = RoundedCornerShape(cornerRadius),
        blurRadius = 20f,
        colors = BlurColors(
            blendColors = listOf(
                BlendColorEntry(color = MiuixTheme.colorScheme.surface.copy(alpha = 0.6f)),
            ),
        ),
    )
}

/**
 * Card color that lets the frosted backdrop show through while liquid glass is
 * active, and the regular opaque surface color otherwise.
 */
@Composable
fun glassCardColor(backdrop: LayerBackdrop?): Color =
    if (backdrop != null && LocalLiquidGlass.current) {
        Color.Transparent
    } else {
        MiuixTheme.colorScheme.surface
    }
