package com.mrbbot.magicalbooks.item;

import com.mrbbot.magicalbooks.achievement.ModAchievements;
import com.mrbbot.magicalbooks.init.ModItems;
import com.mrbbot.magicalbooks.reference.Names;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.List;

public class ItemActivationStick extends ItemMagicalBooks {
    public ItemActivationStick() {
        super();
        setUnlocalizedName(Names.Items.ACTIVATION_STICK);
        setLore("It tingles...");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        AxisAlignedBB region = AxisAlignedBB.fromBounds(pos.getX() - 2, pos.getY() - 2, pos.getZ() - 2, pos.getX() + 2, pos.getY() + 2, pos.getZ() + 2);
        List<Entity> entities = worldIn.getEntitiesWithinAABB(EntityItem.class, region);
        boolean crafted = false;
        for(Entity entity : entities) {
            if(entity instanceof EntityItem) {
                EntityItem entityItem = (EntityItem)entity;
                if(entityItem.getEntityItem().getItem().equals(Items.writable_book)) {
                    entityItem.setEntityItemStack(new ItemStack(ModItems.bookKnowledge));
                    worldIn.playSoundEffect(entityItem.posX, entityItem.posY, entityItem.posZ, "random.fizz", 1, 1);
                    playerIn.addStat(ModAchievements.achKnowledge, 1);
                    crafted = true;
                }
            }
        }
        if(!crafted) worldIn.playSoundEffect(pos.getX(), pos.getY(), pos.getZ(), "note.bass", 1, 1);
        return true;
    }
}
