package com.edencoding.layouts;

public enum BootstrapColumnBreakPoint {
    XSMALL(0), SMALL(1), MEDIUM(2), LARGE(3), XLARGE(4);

    int value;

    BootstrapColumnBreakPoint(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
