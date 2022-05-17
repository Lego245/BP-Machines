package com.therealm18.beyond_planets_machines.guis.screens.solarpanels;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.therealm18.beyond_planets_machines.machines.solar.tile.SolarPanelT1BlockEntity;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.energy.IEnergyStorage;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.gauge.GaugeTextHelper;
import net.mrscauthd.beyond_earth.gauge.GaugeValueHelper;

@OnlyIn(Dist.CLIENT)
public class SolarPanelT3GuiWindow extends AbstractContainerScreen<SolarPanelT1Gui.GuiContainer> {

    public static final ResourceLocation texture = new ResourceLocation(BeyondEarthMod.MODID, "textures/screens/solar_panel.png");

    public SolarPanelT3GuiWindow(SolarPanelT1Gui.GuiContainer container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.inventoryLabelY = this.imageHeight - 92;
    }

    @Override
    public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        this.renderTooltip(ms, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack ms, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);
        GuiComponent.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(PoseStack ms, int p_97809_, int p_97810_) {
        super.renderLabels(ms, p_97809_, p_97810_);

        SolarPanelT1BlockEntity blockEntity = this.getMenu().getBlockEntity();
        IEnergyStorage energyStorage = blockEntity.getPrimaryEnergyStorage();

        this.font.draw(ms, GaugeTextHelper.getStoredText(GaugeValueHelper.getEnergy(energyStorage.getEnergyStored())).build(), this.titleLabelY, 28, 0x3C3C3C);
        this.font.draw(ms, GaugeTextHelper.getCapacityText(GaugeValueHelper.getEnergy(energyStorage.getMaxEnergyStored())).build(), this.titleLabelY, 40, 0x3C3C3C);
        this.font.draw(ms, GaugeTextHelper.getMaxGenerationPerTickText(GaugeValueHelper.getEnergy(blockEntity.getMaxGeneration())).build(), this.titleLabelY, 52, 0x3C3C3C);
    }
}