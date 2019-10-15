package com.example.examplemod.capabilities;

import com.example.examplemod.models.TeaIngredient;

import java.util.List;

public interface IBlendIngredients {
    List<TeaIngredient> getIngredients();
    void addIngredient();
    void setIngredient(TeaIngredient ingredient, int count);
    void removeIngredient();
    void clearIngredients();
}
