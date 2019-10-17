package com.bakkingamu.littlegarden.capabilities.ingredients;

import com.bakkingamu.littlegarden.models.TeaIngredient;
import java.util.Map;

public interface IBlendIngredients {
    Map<TeaIngredient,Integer> getIngredients();
    void addIngredient(TeaIngredient ingredient);
    void setIngredients(Map<TeaIngredient,Integer> ingredients);
    void setIngredient(TeaIngredient ingredient, int count);
    void removeIngredient(TeaIngredient ingredient);
    void clearIngredient(TeaIngredient ingredient);
    void clearAllIngredients();
}
