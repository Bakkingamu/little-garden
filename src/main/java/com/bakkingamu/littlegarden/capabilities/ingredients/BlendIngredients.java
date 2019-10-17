package com.bakkingamu.littlegarden.capabilities.ingredients;

import com.bakkingamu.littlegarden.models.TeaIngredient;
import java.util.HashMap;
import java.util.Map;

public class BlendIngredients implements IBlendIngredients{
    private Map<TeaIngredient,Integer> ingredients = new HashMap<>();

    @Override
    public Map<TeaIngredient,Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public void setIngredients(Map<TeaIngredient,Integer> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public void addIngredient(TeaIngredient ingredient) {
        setIngredient(ingredient, ingredients.getOrDefault(ingredient,0) + 1);
    }

    @Override
    public void setIngredient(TeaIngredient ingredient, int count) {
        ingredients.put(ingredient, count);
    }

    @Override
    public void removeIngredient(TeaIngredient ingredient) {
        setIngredient(ingredient, ingredients.getOrDefault(ingredient,0) - 1);
    }
    @Override
    public void clearIngredient(TeaIngredient ingredient) {
        ingredients.put(ingredient,0);
    }

    @Override
    public void clearAllIngredients() {
        ingredients.clear();
    }
}
