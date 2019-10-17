package com.bakkingamu.littlegarden.items;

import com.bakkingamu.littlegarden.capabilities.ingredients.BlendIngredientProvider;
import com.bakkingamu.littlegarden.capabilities.ingredients.IBlendIngredients;
import com.bakkingamu.littlegarden.models.TeaIngredient;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class BrewedTea extends ItemBucketMilk {
    public static final String NAME = "brewed_tea";
    public static final String UNLOCALIZED_NAME = "brewed_tea";
    public static final String REGISTRY_NAME = "brewed_tea_registry";
    private Integer doses;
    public BrewedTea(){
        super();
        this.setCreativeTab(CreativeTabs.FOOD);
        setMaxStackSize(16);
        setRegistryName(REGISTRY_NAME);
        setUnlocalizedName(UNLOCALIZED_NAME);

    }
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving){
        EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
        if (entityLiving instanceof EntityPlayer && !((EntityPlayer)entityLiving).capabilities.isCreativeMode)
        {
            IBlendIngredients ingredients = stack.getCapability(BlendIngredientProvider.INGREDIENT_CAPABILITY, null);
            System.out.println(ingredients);
            if(ingredients != null){
                ingredients
                        .getIngredients()
                        .entrySet()
                        .stream()
                        .filter(entry->entry.getValue() > 0)
                        .forEach(entry->entityLiving.addPotionEffect(new PotionEffect(getPotionEffectFromIngredient(entry.getKey()),2400)));
            }
            entityLiving.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2400));
            stack.shrink(1);
        }
        if (entityplayer instanceof EntityPlayerMP)
        {
            CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
        }
        return stack;
    }

    public static List<PotionEffect> getPotionEffectsFromIngredients(List<TeaIngredient> ingredients){
        return ingredients
                    .stream()
                    .map((TeaIngredient ingredient)->new PotionEffect(getPotionEffectFromIngredient(ingredient)))
                    .collect(Collectors.toList());

    }

    public static Potion getPotionEffectFromIngredient(TeaIngredient ingredient){
        switch (ingredient){
            case MILK:
                return MobEffects.RESISTANCE;
            case SUGAR:
                return MobEffects.SPEED;
            case GINGER:
                return MobEffects.WATER_BREATHING;
            case BERGMOT:
                return MobEffects.STRENGTH;
            case CINNAMON:
                return MobEffects.HEALTH_BOOST;
            default:
                return MobEffects.REGENERATION;
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        IBlendIngredients ingredients = stack.getCapability(BlendIngredientProvider.INGREDIENT_CAPABILITY, null);
        if(ingredients != null){
            ingredients.getIngredients()
                    .forEach((TeaIngredient ingredient, Integer count)-> tooltip.add(ingredient.name() + ": " + count.toString()));
        }
    }

    public static ItemStack brewedTeaFromBlend(ItemStack blend){
        ItemStack brewedTea = new ItemStack(ModItems.brewedTea);
        if(brewedTea.hasCapability(BlendIngredientProvider.INGREDIENT_CAPABILITY, null)
            && blend.hasCapability(BlendIngredientProvider.INGREDIENT_CAPABILITY, null) ){
            brewedTea
                    .getCapability(BlendIngredientProvider.INGREDIENT_CAPABILITY, null)
                    .setIngredients(blend.getCapability(BlendIngredientProvider.INGREDIENT_CAPABILITY, null).getIngredients());
        }
        return brewedTea;
    }

}
