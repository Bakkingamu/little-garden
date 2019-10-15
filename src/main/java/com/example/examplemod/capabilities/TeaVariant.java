package com.example.examplemod.capabilities;

import com.example.examplemod.models.TeaType;

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
