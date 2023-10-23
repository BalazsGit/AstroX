package astrox.util;

import com.formdev.flatlaf.icons.FlatTabbedPaneCloseIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatUIUtils;

import java.awt.geom.Path2D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;

public class FlatTabbedPaneAddIcon extends FlatTabbedPaneCloseIcon {

    protected void paintIcon(Component c, Graphics2D g) {
        Color bg = FlatButtonUI.buttonStateColor(c, this.closeBackground, (Color)null, (Color)null, this.closeHoverBackground, this.closePressedBackground);
        if (bg != null) {
            g.setColor(FlatUIUtils.deriveColor(bg, c.getBackground()));
            g.fillRoundRect((this.width - this.closeSize.width) / 2, (this.height - this.closeSize.height) / 2, this.closeSize.width, this.closeSize.height, this.closeArc, this.closeArc);
        }

        Color fg = FlatButtonUI.buttonStateColor(c, this.closeForeground, (Color)null, (Color)null, this.closeHoverForeground, this.closePressedForeground);
        g.setColor(FlatUIUtils.deriveColor(fg, c.getForeground()));
        float mx = (float)(this.width / 2);
        float my = (float)(this.height / 2);
        float r = (bg != null ? this.closeCrossFilledSize : this.closeCrossPlainSize) / 2.0F;
        Path2D path = new Path2D.Float(0);
        path.append(new java.awt.geom.Line2D.Float(mx - r, my - r, mx + r, my + r), false);
        path.append(new java.awt.geom.Line2D.Float(mx - r, my + r, mx + r, my - r), false);
        g.rotate(Math.PI/4, mx, my);
        g.setStroke(new BasicStroke(this.closeCrossLineWidth));
        g.draw(path);
    }
}
