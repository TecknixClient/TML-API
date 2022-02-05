package com.tecknix.modding.api.event.type;

import com.tecknix.modding.api.event.TMEvent;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;


/**
 * TMGuiOpenedEvent
 * <p>
 * This event is called in the {@link Minecraft} class.
 * It gets called on every gui that has been opened on the client.
 * <p>
 * It can be canceled.
 *
 * @author Si1kn
 */
public class TMGuiOpenedEvent extends TMEvent {

    /**
     * Current GUI Screen
     *
     * @returns GuiScreen
     */
    @Getter
    private final GuiScreen currentGUI;

    public TMGuiOpenedEvent(GuiScreen currentGUI) {
        this.currentGUI = currentGUI;
    }
}
