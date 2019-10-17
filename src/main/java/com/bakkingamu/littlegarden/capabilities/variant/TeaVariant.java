package com.bakkingamu.littlegarden.capabilities.variant;

import com.bakkingamu.littlegarden.models.TeaType;

public class TeaVariant implements ITeaVariant {
    private TeaType type = TeaType.None;
    @Override
    public TeaType getTeaType() {
        return type;
    }

    @Override
    public void setTeaType(TeaType type) {
        this.type = type;
    }
}
