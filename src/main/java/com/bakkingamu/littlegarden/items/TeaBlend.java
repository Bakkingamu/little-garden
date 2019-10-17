package com.bakkingamu.littlegarden.items;

import com.bakkingamu.littlegarden.capabilities.ingredients.BlendIngredientProvider;
import com.bakkingamu.littlegarden.capabilities.ingredients.IBlendIngredients;
import com.bakkingamu.littlegarden.models.TeaIngredient;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class TeaBlend extends Item {
    public static final String NAME = "tea_blend";
    public static final String UNLOCALIZED_NAME = "tea_blend";
    public static final String REGISTRY_NAME = "tea_blend_registry";

    public TeaBlend(){
        super();
        this.setCreativeTab(CreativeTabs.FOOD);
        setRegistryName(REGISTRY_NAME);
        setUnlocalizedName(UNLOCALIZED_NAME);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        IBlendIngredients ingredients = stack.getCapability(BlendIngredientProvider.INGREDIENT_CAPABILITY, null);
        if(ingredients != null){
            ingredients.getIngredients()
                    .forEach((TeaIngredient ingredient, Integer count)-> tooltip.add(ingredient.name() + ": " + count.toString()));
        }
    }

}
