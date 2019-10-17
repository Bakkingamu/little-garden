
package com.bakkingamu.littlegarden.capabilities.ingredients;

        import com.bakkingamu.littlegarden.models.TeaIngredient;
        import net.minecraft.nbt.NBTBase;
        import net.minecraft.nbt.NBTTagCompound;
        import net.minecraft.nbt.NBTTagList;
        import net.minecraft.util.EnumFacing;
        import net.minecraftforge.common.capabilities.Capability;
        import javax.annotation.Nullable;

public class BlendIngredientsStorage implements Capability.IStorage<IBlendIngredients> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IBlendIngredients> capability, IBlendIngredients instance, EnumFacing side) {
        NBTTagList tagList = new NBTTagList();
        instance.getIngredients()
                .entrySet()
                .stream()
                .map((entry)-> {
                    NBTTagCompound compound = new NBTTagCompound();
                    compound.setString("key", entry.getKey().name());
                    compound.setInteger("value",entry.getValue());
                    return compound;
                })
                .forEach(tagList::appendTag);
        return tagList;
    }

    @Override
    public void readNBT(Capability<IBlendIngredients> capability, IBlendIngredients instance, EnumFacing side, NBTBase nbt) {
        ((NBTTagList)nbt)
                .iterator()
                .forEachRemaining((NBTBase base)->
                        instance.setIngredient(
                                TeaIngredient.valueOf(((NBTTagCompound)base).getString("key")),
                                ((NBTTagCompound)base).getInteger("value")));
    }
}