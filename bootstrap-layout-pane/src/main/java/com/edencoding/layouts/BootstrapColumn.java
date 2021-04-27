package com.edencoding.layouts;

import com.edencoding.math.MathUtils;
import javafx.scene.Node;

public class BootstrapColumn {

    Node content;
    
    private int col = 1;
    private int colSm = -1;
    private int colMd = -1;
    private int colLg = -1;
    private int colXL = -1;

    public BootstrapColumn(Node content) {
        this.content = content;
        percolate();
    }

    /**
     * Propagate column break points based on a copy-smaller biased approach
     * This replicates the min-width media query approach addopted by Bootstrap
     */
    private void percolate() {
        col = MathUtils.clamp(col, 1, 12); //ensure col is valid before percolating
        if (!isValid(colSm)) colSm = col;
        if (!isValid(colMd)) colMd = colSm;
        if (!isValid(colLg)) colLg = colMd;
        if (!isValid(colXL)) colXL = colLg;
    }

    /**
     * Whether a value is between 1 and 12 (i.e. a valid column width)
     *
     * @param value the value being tested
     * @return whether the value is a valid column width
     */
    private boolean isValid(int value) {
        return value > 0 && value <= 12;
    }

    /**
     * Set the column width of the content at the specified breakpoint
     *
     * @param breakPoint the screen size break point being specified
     * @param width      the requested width at this breakpoint (must be between 1 and 12);
     */
    public void setBreakpointColumnWidth(BootstrapColumnBreakPoint breakPoint, int width) {
        switch (breakPoint) {
            case XSMALL:
                col = MathUtils.clamp(width, 1, 12);
                break;
            case SMALL:
                colSm = MathUtils.clamp(width, 1, 12);
                break;
            case MEDIUM:
                colMd = MathUtils.clamp(width, 1, 12);
                break;
            case LARGE:
                colLg = MathUtils.clamp(width, 1, 12);
                break;
            case XLARGE:
                colXL = MathUtils.clamp(width, 1, 12);
                break;
        }
        percolate();
    }

    /**
     * Remove a previously-specified column breakpoint width setting
     *
     * @param breakPoint the breakpoint to reset
     */
    public void unsetBreakPoint(BootstrapColumnBreakPoint breakPoint) {
        switch (breakPoint) {
            case XSMALL:
                col = 1;
                break;
            case SMALL:
                colSm = -1;
                break;
            case MEDIUM:
                colMd = -1;
                break;
            case LARGE:
                colLg = -1;
                break;
            case XLARGE:
                colXL = -1;
                break;
        }
        percolate();
    }

    /**
     * Reset all column width break points, so the default width at all break points is 1.
     */
    public void unsetAllBreakPoints() {
        this.col = 1;
        this.colSm = -1;
        this.colMd = -1;
        this.colLg = -1;
        this.colXL = -1;
    }

    /**
     * Iterate through breakpoints, beginning at the specified bp, travelling down. Return first valid bp value.
     * If none are valid, return 1
     *
     * @param breakPoint the breakpoint at which to determine the column width
     * @return the requested width at that breakpoint, or based on a lower breakpoint if the specified bp has not been set.
     */
    public int getColumnWidth(BootstrapColumnBreakPoint breakPoint) {
        int[] breakPoints = new int[]{col, colSm, colMd, colLg, colXL};

        //Iterate through breakpoints, beginning at the specified bp, travelling down. Return first valid bp value.
        for (int i = breakPoint.getValue(); i > 0; i--) {
            if (isValid(breakPoints[i])) return breakPoints[i];
        }

        //If none are valid, return 1
        return 1;
    }
}
