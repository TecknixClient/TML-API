package com.tecknix.modding.api.event.type;

import com.tecknix.modding.api.event.TMEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;


/**
 * TMRenderIngameGuiEvent
 * <p>
 * Called in the {@link net.minecraft.client.gui.GuiIngame} class.
 * Used to render 2D elements to the screen every tick.
 * <p>
 *
 * @author quickdaffy
 */
@AllArgsConstructor
public class TMRenderIngameGuiEvent extends TMEvent {
    @Getter private float partialTicks;
}
