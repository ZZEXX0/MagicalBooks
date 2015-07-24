package com.mrbbot.magicalbooks.init;

import com.mrbbot.magicalbooks.gui.util.PositionedItemStack;
import com.mrbbot.magicalbooks.tileentity.TileEntityPedestal;
import com.mrbbot.magicalbooks.reference.Reference;
import com.mrbbot.magicalbooks.utility.LogHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

import java.util.*;

public class InfusionRecipes {

    private static ArrayList<InfusionRecipe> recipes = new ArrayList<InfusionRecipe>();

    public static class InfusionRecipe implements IRenderableRecipe {
        private int xp;
        private float health;
        private ItemStack output;
        private ItemStack main;
        private ItemStack[] others;
        private List<PositionedItemStack> positionedItemStacks;

        public InfusionRecipe(float health, int xp, ItemStack output, ItemStack main, ItemStack[] others) {
            this.xp = xp;
            this.health = health;
            this.output = output;
            this.main = main;
            this.others = others;

            switch(others.length) {
                case 2:
                    positionedItemStacks = Arrays.asList(
                            new PositionedItemStack(others[0], 95, 69),
                            new PositionedItemStack(others[1], 35, 69),
                            new PositionedItemStack(main, 65, 69),
                            new PositionedItemStack(output, 65, 125));
                    break;
                case 4:
                    positionedItemStacks = Arrays.asList(
                            new PositionedItemStack(others[0], 65, 39),
                            new PositionedItemStack(others[1], 95, 69),
                            new PositionedItemStack(others[2], 65, 99),
                            new PositionedItemStack(others[3], 35, 69),
                            new PositionedItemStack(main, 65, 69),
                            new PositionedItemStack(output, 65, 125));
                    break;
                case 6:
                    positionedItemStacks = Arrays.asList(
                            new PositionedItemStack(others[0], 89, 45),
                            new PositionedItemStack(others[1], 95, 69),
                            new PositionedItemStack(others[2], 89, 93),
                            new PositionedItemStack(others[3], 41, 93),
                            new PositionedItemStack(others[4], 35, 69),
                            new PositionedItemStack(others[5], 41, 45),
                            new PositionedItemStack(main, 65, 69),
                            new PositionedItemStack(output, 65, 125));
                    break;
                case 8:
                    positionedItemStacks = Arrays.asList(
                            new PositionedItemStack(others[0], 65, 39),
                            new PositionedItemStack(others[1], 89, 45),
                            new PositionedItemStack(others[2], 95, 69),
                            new PositionedItemStack(others[3], 89, 93),
                            new PositionedItemStack(others[4], 65, 99),
                            new PositionedItemStack(others[5], 41, 93),
                            new PositionedItemStack(others[6], 35, 69),
                            new PositionedItemStack(others[7], 41, 45),
                            new PositionedItemStack(main, 65, 69),
                            new PositionedItemStack(output, 65, 125));
                    break;
                default:
                    positionedItemStacks = new ArrayList<PositionedItemStack>();
                    break;
            }
        }

        @Override
        public List<PositionedItemStack> getPositionedItemStacks() {
            return positionedItemStacks;
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

        public int getXp() {
            return xp;
        }

        public float getHealth() {
            return health;
        }
    }

    public static void infuse(TileEntityPedestal main) {
        LogHelper.info("Attempting infusion with pedestal " + main.getPos() + "...");
        BlockPos pos = main.getPos();
        HashMap<TileEntityPedestal, Boolean> otherPedestals = new HashMap<TileEntityPedestal, Boolean>();
        for(int xOffset = -Reference.INFUSION_RANGE; xOffset <= Reference.INFUSION_RANGE; xOffset++) {
            for(int zOffset = -Reference.INFUSION_RANGE; zOffset <= Reference.INFUSION_RANGE; zOffset++) {
                int x = pos.getX() + xOffset;
                int z = pos.getZ() + zOffset;
                TileEntity tileEntity = main.getWorld().getTileEntity(new BlockPos(x, pos.getY(), z));
                if(tileEntity != null && tileEntity instanceof TileEntityPedestal) {
                    if(!tileEntity.equals(main))
                        otherPedestals.put((TileEntityPedestal) tileEntity, false);
                }
            }
        }
        boolean crafted = false;
        for(InfusionRecipe infusionRecipe : recipes) {
            if(main.getItemStack() != null && main.getItemStack().isItemEqual(infusionRecipe.getMain())) {
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
                    crafted = true;
                    break;
                }
            }
        }
        if(crafted)
            main.getWorld().playSoundEffect(pos.getX(), pos.getY(), pos.getZ(), "random.fizz", 1, 1);
        else
            main.getWorld().playSoundEffect(pos.getX(), pos.getY(), pos.getZ(), "note.bass", 1, 1);
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

    public static InfusionRecipe addInfusion(float health, int xp, ItemStack output, ItemStack main, ItemStack... others) {
        InfusionRecipe infusionRecipe = new InfusionRecipe(health, xp, output, main, others);
        recipes.add(infusionRecipe);
        return infusionRecipe;
    }

    public static ArrayList<InfusionRecipe> getRecipes() {
        return recipes;
    }
}
