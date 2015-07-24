package com.mrbbot.magicalbooks.gui;

import com.mrbbot.magicalbooks.gui.util.*;
import com.mrbbot.magicalbooks.init.ModItems;
import com.mrbbot.magicalbooks.init.Recipes;
import com.mrbbot.magicalbooks.reference.GuiIds;
import com.mrbbot.magicalbooks.reference.Names;
import com.mrbbot.magicalbooks.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiBookKnowledge extends GuiScreen {
    private final int GUI_WIDTH = 146;
    private final int GUI_HEIGHT = 180;
    private final int INDENT = 14;
    private final int BUTTON_WIDTH = GUI_WIDTH - INDENT;
    private final int LINE_HEIGHT = 12;

    private int page = GuiIds.Buttons.BOOK_KNOWLEDGE_MAIN;
    private int subPage = 0;

    private List<IRenderableComponent> renderList;

    public GuiBookKnowledge(EntityPlayer player, World world, int x, int y, int z) {
        super();
        renderList = new ArrayList<IRenderableComponent>();
    }

    @Override
    public void initGui() {
        renderList.clear();
        buttonList.clear();
        int buttonX = getPosX() + 14;

        switch (page) {
            case GuiIds.Buttons.BOOK_KNOWLEDGE_MAIN: {
                renderList.add(new RenderText("§n" + StatCollector.translateToLocal("gui.bookKnowledge.main"), INDENT, getLineY(0), 0));
                buttonList.add(new TextButton(GuiIds.Buttons.BOOK_KNOWLEDGE_PEDESTAL, buttonX, getButtonY(1), BUTTON_WIDTH, LINE_HEIGHT, StatCollector.translateToLocal("gui.bookKnowledge.pedestal")));
                buttonList.add(new TextButton(GuiIds.Buttons.BOOK_KNOWLEDGE_BOOKS, buttonX, getButtonY(2), BUTTON_WIDTH, LINE_HEIGHT, StatCollector.translateToLocal("gui.bookKnowledge.books")));
                break;
            } case GuiIds.Buttons.BOOK_KNOWLEDGE_PEDESTAL: {
                switch (subPage) {
                    case 0: {           //Pedestals (Page 1)
                        renderList.add(new RenderText("§n" + StatCollector.translateToLocal("gui.bookKnowledge.pedestal"), INDENT, getLineY(0), 0));
                        renderList.add(new RenderText(getDescription(Names.Blocks.PEDESTAL), INDENT, getLineY(1)));
                        addForwardButton();
                        addHomeButton();
                        break;
                    } case 1: {         //Pedestals (Page 2)
                        renderList.add(new RenderText("§n" + StatCollector.translateToLocal("gui.bookKnowledge.pedestal"), INDENT, getLineY(0), 0));
                        renderList.add(new RenderRecipe(Recipes.recipePedestal, 0, -12));
                        renderList.add(new RenderText(getDescription(Names.Blocks.PEDESTAL + "2"), INDENT, getLineY(6)));
                        addBackButton(0);
                        addForwardButton();
                        break;
                    }
                }
                addHomeButton();
                break;
            } case GuiIds.Buttons.BOOK_KNOWLEDGE_BOOKS: {
                switch (subPage) {
                    case 0: {           //Books (Page 1)
                        renderList.add(new RenderText("§n" + StatCollector.translateToLocal("gui.bookKnowledge.books"), INDENT, getLineY(0), 0));
                        buttonList.add(new TextButton(0, buttonX, getButtonY(1), BUTTON_WIDTH, LINE_HEIGHT, getTitle(ModItems.bookCraft, true), 11, true));
                        buttonList.add(new TextButton(0, buttonX, getButtonY(2), BUTTON_WIDTH, LINE_HEIGHT, getTitle(ModItems.bookStar, true), 21, true));
                        buttonList.add(new TextButton(0, buttonX, getButtonY(3), BUTTON_WIDTH, LINE_HEIGHT, getTitle(ModItems.bookGrowth, true), 31, true));
                        addHomeButton();
                        break;
                    } case 11: {       //Book of Crafting (Page 1)
                        renderList.add(new RenderText(getTitle(ModItems.bookCraft), INDENT, getLineY(0), 0));
                        renderList.add(new RenderText(getDescription(Names.Items.BOOK_CRAFT), INDENT, getLineY(1)));
                        addBackButton(0);
                        addForwardButton();
                        break;
                    } case 12: {       //Book of Crafting (Page 2)
                        renderList.add(new RenderText(getTitle(ModItems.bookCraft), INDENT, getLineY(0), 0));
                        renderList.add(new RenderText(StatCollector.translateToLocal("gui.bookKnowledge.infusion"), INDENT, getLineY(1)));
                        renderList.add(new RenderRecipe(Recipes.recipeBookCraft, 0, 0));
                        addBackButton();
                        break;
                    } case 21: {       //Book of Nether Stars (Page 1)
                        renderList.add(new RenderText(getTitle(ModItems.bookStar), INDENT, getLineY(0), 0));
                        renderList.add(new RenderText(getDescription(Names.Items.BOOK_NETHER_STAR), INDENT, getLineY(1)));
                        addBackButton(0);
                        addForwardButton();
                        break;
                    } case 22: {       //Book of Nether Stars (Page 2)
                        renderList.add(new RenderText(getTitle(ModItems.bookStar), INDENT, getLineY(0), 0));
                        renderList.add(new RenderText(StatCollector.translateToLocal("gui.bookKnowledge.infusion"), INDENT, getLineY(1)));
                        renderList.add(new RenderRecipe(Recipes.recipeBookStar, 0, 0));
                        addBackButton();
                        addForwardButton();
                        break;
                    } case 23: {       //Book of Nether Stars (Page 3)
                        renderList.add(new RenderText(getTitle(ModItems.itemActivationRod), INDENT, getLineY(0), 0));
                        renderList.add(new RenderText(getDescription(Names.Items.ACTIVATION_ROD), INDENT, getLineY(1)));
                        addBackButton();
                        addForwardButton();
                        break;
                    } case 24: {       //Book of Nether Stars (Page 4)
                        renderList.add(new RenderText(getTitle(ModItems.itemActivationRod), INDENT, getLineY(0), 0));
                        renderList.add(new RenderText(StatCollector.translateToLocal("gui.bookKnowledge.crafting"), INDENT, getLineY(1)));
                        renderList.add(new RenderRecipe(Recipes.recipeActivationRod, 0, 0));
                        addBackButton();
                        break;
                    } case 31: {       //Book of Growth (Page 1)
                        renderList.add(new RenderText(getTitle(ModItems.bookGrowth), INDENT, getLineY(0), 0));
                        renderList.add(new RenderText(getDescription(Names.Items.BOOK_GROWTH), INDENT, getLineY(1)));
                        addBackButton(0);
                        addForwardButton();
                        break;
                    } case 32: {       //Book of Growth (Page 2)
                        renderList.add(new RenderText(getTitle(ModItems.bookGrowth), INDENT, getLineY(0), 0));
                        renderList.add(new RenderText(StatCollector.translateToLocal("gui.bookKnowledge.infusion"), INDENT, getLineY(1)));
                        renderList.add(new RenderRecipe(Recipes.recipeBookGrowth, 0, 0));
                        addBackButton();
                        break;
                    }
                }
                break;
            }
        }
    }

    public String getTitle(Object object) {
        return getTitle(object, false);
    }

    public String getTitle(Object object, boolean noUnderline) {
        String underline = "§n";
        if(noUnderline)
            underline = "";
        if(object instanceof Block)
            return underline + StatCollector.translateToLocal(((Block)object).getUnlocalizedName() + ".name");
        if(object instanceof Item)
            return underline + StatCollector.translateToLocal(((Item)object).getUnlocalizedName() + ".name");
        return null;
    }

    public String getDescription(String item) {
        String description = StatCollector.translateToLocal("gui.bookKnowledge.desc." + item);
        int lastIndexOfSpace = description.lastIndexOf("`") + 1;
        description = description.substring(lastIndexOfSpace);
        description = description.replace("\\n", "\n");
        return description;
    }

    private void addHomeButton() {
        buttonList.add(new ImageButton(GuiIds.Buttons.BOOK_KNOWLEDGE_MAIN, 21 + getPosX(), 155 + getPosY(), 18, 10, 22, 193, 45, 193, 0, false));
    }

    private void addBackButton() {
        buttonList.add(new ImageButton(0, 21 + getPosX(), 155 + getPosY(), 18, 10, 22, 193, 45, 193, -1, false));
    }

    private void addBackButton(int page) {
        buttonList.add(new ImageButton(0, 21 + getPosX(), 155 + getPosY(), 18, 10, 22, 193, 45, 193, page, true));
    }

    private void addForwardButton() {
        buttonList.add(new ImageButton(0, 103 + getPosX(), 155 + getPosY(), 18, 10, 22, 180, 45, 180, 1, false));
    }

    private int getLineY(int line) {
        return (12 * line) + 16;
    }

    private int getButtonY(int line) {
        return (12 * line) + 16 + getPosY();
    }

    public int getPosX() {
        return (width - GUI_WIDTH) / 2;
    }

    public int getPosY() {
        return (height - GUI_HEIGHT) / 2;
    }

    private long lastClick = 0;
    @Override
    protected void actionPerformed(GuiButton button) {
        long timeBetween = System.currentTimeMillis() - lastClick;
        if(button instanceof TextButton && timeBetween > 3) {
            TextButton textButton = (TextButton)button;
            if(textButton.shouldSetSubPage()) {
                subPage = textButton.getSubPage();
            } else if(textButton.getSubPage() != 0) {
                subPage += textButton.getSubPage();
            } else {
                page = button.id;
                subPage = 0;
            }
            initGui();
            lastClick = System.currentTimeMillis();
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        drawGuiContainerForegroundLayer(mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.GUI_BOOK_KNOWLEDGE);
        int posX = getPosX();
        int posY = getPosY();
        this.drawTexturedModalRect(posX, posY, 0, 0, GUI_WIDTH, GUI_HEIGHT);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int posX = getPosX();
        int posY = getPosY();

        for(IRenderableComponent renderableComponent : renderList) {
            renderableComponent.render(this, posX, posY);
        }
    }

    public FontRenderer getFontRenderer() {
        return fontRendererObj;
    }

    public void renderItemStack(ItemStack stack, int x, int y) {
        GlStateManager.translate(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        this.itemRender.zLevel = 200.0F;
        FontRenderer font = null;
        if (stack != null) font = stack.getItem().getFontRenderer(stack);
        if (font == null) font = fontRendererObj;
        this.itemRender.func_180450_b(stack, x, y);
        String altText = "";
        if(stack.stackSize > 1) altText = String.valueOf(stack.stackSize);
        this.itemRender.func_180453_a(font, stack, x, y, altText);
        this.zLevel = 0.0F;
        this.itemRender.zLevel = 0.0F;
    }

}
