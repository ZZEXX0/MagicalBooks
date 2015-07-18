package com.mrbbot.magicalbooks.init;

import com.mrbbot.magicalbooks.block.tileentity.TileEntityPedestal;
import com.mrbbot.magicalbooks.reference.Reference;
import com.mrbbot.magicalbooks.utility.LogHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfusionRecipes {

    private static ArrayList<InfusionRecipe> recipes = new ArrayList<InfusionRecipe>();

    public static class InfusionRecipe {
        private ItemStack output;
        private ItemStack main;
        private ItemStack[] others;

        public InfusionRecipe(ItemStack output, ItemStack main, ItemStack[] others) {
            this.output = output;
            this.main = main;
            this.others = others;
        }

        public ItemStack getOutput() {
            return output;
        }

        public ItemStack getMain() {
            return main;
        }

        public ItemStack[] getOthers() {
            return others;
        }
    }

    public static void craft(TileEntityPedestal main) {
        HashMap<TileEntityPedestal, Boolean> otherPedestals = new HashMap<TileEntityPedestal, Boolean>();
        for(int xOffset = -Reference.INFUSION_RANGE; xOffset <= Reference.INFUSION_RANGE; xOffset++) {
            for(int zOffset = -Reference.INFUSION_RANGE; zOffset <= Reference.INFUSION_RANGE; zOffset++) {
                int x = main.xCoord + xOffset;
                int z = main.zCoord + zOffset;
                TileEntity tileEntity = main.getWorldObj().getTileEntity(x, main.yCoord, z);
                if(tileEntity != null && tileEntity instanceof TileEntityPedestal) {
                    if(!tileEntity.equals(main))
                        otherPedestals.put((TileEntityPedestal) tileEntity, false);
                }
            }
        }
        for(InfusionRecipe infusionRecipe : recipes) {
            //check main component
            if(main.getItemStack().isItemEqual(infusionRecipe.getMain())) {
                HashMap<ItemStack,Boolean> needs = new HashMap<ItemStack, Boolean>();
                for(ItemStack otherItemStack : infusionRecipe.getOthers()) needs.put(otherItemStack, false);

                boolean allNeedsCovered = true;
                for(Map.Entry<ItemStack, Boolean> need : needs.entrySet()) {
                    if(hasItem(otherPedestals, need.getKey())) {
                        need.setValue(true);
                    } else {
                        allNeedsCovered = false;
                        break;
                    }
                }

                if(allNeedsCovered) {
                    for(Map.Entry<TileEntityPedestal, Boolean> pedestal : otherPedestals.entrySet()) {
                        if(pedestal.getValue()) {
                            pedestal.getKey().setItemStack(null);
                        }
                    }

                    main.setItemStack(infusionRecipe.getOutput());
                    break;
                }
            }
        }
    }

    private static boolean hasItem(HashMap<TileEntityPedestal, Boolean> otherPedestals, ItemStack stack) {
        for(Map.Entry<TileEntityPedestal, Boolean> pedestal : otherPedestals.entrySet()) {
            if (!pedestal.getValue() && pedestal.getKey() != null && pedestal.getKey().getItemStack() != null && pedestal.getKey().getItemStack().isItemEqual(stack)) {
                pedestal.setValue(true);
                return true;
            }
        }
        return false;
    }

    public static void addRecipe(ItemStack output, ItemStack main, ItemStack... others) {
        recipes.add(new InfusionRecipe(output, main, others));
    }

    public static void init() {
        addRecipe(new ItemStack(ModItems.bookJump), new ItemStack(Items.book), new ItemStack(Items.nether_star), new ItemStack(Items.feather));

        LogHelper.info("Loaded " + recipes.size() + " infusion recipe(s)...");
    }

}
