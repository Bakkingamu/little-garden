package com.example.examplemod.recipes;

import com.example.examplemod.items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class TeaBlendRecipe implements IRecipe {
    private ItemStack resultItem = ItemStack.EMPTY;

    /*
    * Cinnamon
    * Sugar
    * Bergmot
    * Milk
    * Ginger
    * */

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        final int TEA_LEAVES =  0;
        final int CINNAMON =    1;
        final int SUGAR =       2;
        final int BERGMOT =     3;
        final int MILK =        4;
        final int GINGER =      5;
        int[] ingredientCount ={ 0,0,0,0,0,0};
        this.resultItem = ItemStack.EMPTY;
        for( int invSlot = 0; invSlot < inv.getSizeInventory(); invSlot++){
            if(inv.getStackInSlot(invSlot).getItem() == Items.SUGAR) { ingredientCount[SUGAR]++; continue; }
            if(inv.getStackInSlot(invSlot).getItem() == Items.MILK_BUCKET){ ingredientCount[MILK]++; continue; }
            if(inv.getStackInSlot(invSlot).getItem() == ModItems.teaLeaves){ ingredientCount[TEA_LEAVES]++; continue; }
        }
        if(ingredientCount[TEA_LEAVES]>0){
            return true;
        }else return false;

    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return null;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public IRecipe setRegistryName(ResourceLocation name) {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return null;
    }

    @Override
    public Class<IRecipe> getRegistryType() {
        return null;
    }
}
