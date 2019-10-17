package com.bakkingamu.littlegarden.recipes;

import com.bakkingamu.littlegarden.capabilities.ingredients.BlendIngredientProvider;
import com.bakkingamu.littlegarden.items.ModItems;
import com.bakkingamu.littlegarden.items.TeaBlend;
import com.bakkingamu.littlegarden.models.TeaIngredient;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeFireworks;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Arrays;

public class TeaBlendRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    private ItemStack resultItem = ItemStack.EMPTY;
    private int[] ingredientCount = {0,0,0,0,0,0};
    final int TEA_LEAVES =  0;
    final int CINNAMON =    1;
    final int SUGAR =       2;
    final int BERGMOT =     3;
    final int MILK =        4;
    final int GINGER =      5;
    /*
    * Cinnamon
    * Sugar
    * Bergmot
    * Milk
    * Ginger
    * */

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {

        Arrays.fill(ingredientCount, 0);
        this.resultItem = ItemStack.EMPTY;
        for( int invSlot = 0; invSlot < inv.getSizeInventory(); invSlot++){
            if(!inv.getStackInSlot(invSlot).isEmpty()){
                if(inv.getStackInSlot(invSlot).getItem() == Items.SUGAR) { ingredientCount[SUGAR]++; continue; }
                if(inv.getStackInSlot(invSlot).getItem() == Items.MILK_BUCKET){ ingredientCount[MILK]++; continue; }
                if(inv.getStackInSlot(invSlot).getItem() == ModItems.teaLeaves){ ingredientCount[TEA_LEAVES]++; continue; }
                return false;
            }
        }
        if(ingredientCount[TEA_LEAVES]>0){
            ItemStack stack = new ItemStack(ModItems.teaBlend);
            stack.getCapability(BlendIngredientProvider.INGREDIENT_CAPABILITY, null).setIngredient(TeaIngredient.CINNAMON, ingredientCount[CINNAMON] > 0 ? 1 : 0);
            stack.getCapability(BlendIngredientProvider.INGREDIENT_CAPABILITY, null).setIngredient(TeaIngredient.SUGAR, ingredientCount[SUGAR] > 0 ? 1 : 0);
            stack.getCapability(BlendIngredientProvider.INGREDIENT_CAPABILITY, null).setIngredient(TeaIngredient.BERGMOT, ingredientCount[BERGMOT] > 0 ? 1 : 0);
            stack.getCapability(BlendIngredientProvider.INGREDIENT_CAPABILITY, null).setIngredient(TeaIngredient.MILK, ingredientCount[MILK] > 0 ? 1 : 0);
            stack.getCapability(BlendIngredientProvider.INGREDIENT_CAPABILITY, null).setIngredient(TeaIngredient.GINGER, ingredientCount[GINGER] > 0 ? 1 : 0);
            this.resultItem = stack;
            return true;
        }else return false;

    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return resultItem.copy();
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        for( int invSlot = 0; invSlot < inv.getSizeInventory(); invSlot++){
            if(inv.getStackInSlot(invSlot).getItem() == Items.SUGAR) { nonnulllist.set(invSlot, new ItemStack(Items.SUGAR, ingredientCount[SUGAR]-1)); continue; }
            if(inv.getStackInSlot(invSlot).getItem() == Items.MILK_BUCKET){ nonnulllist.set(invSlot, new ItemStack(Items.MILK_BUCKET, ingredientCount[MILK]-1)); continue; }
            if(inv.getStackInSlot(invSlot).getItem() == ModItems.teaLeaves){ nonnulllist.set(invSlot, new ItemStack(ModItems.teaLeaves, ingredientCount[TEA_LEAVES]-1)); continue; }
        }
        return nonnulllist;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return resultItem;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }
}
