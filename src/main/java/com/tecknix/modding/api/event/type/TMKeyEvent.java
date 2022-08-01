package com.tecknix.modding.api.event.type;

import com.tecknix.modding.api.event.TMEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lwjgl.input.Keyboard;

/**
 * TMKeyEvent
 * <p>
 * This event is called in the {@link net.minecraft.client.Minecraft} class.
 * It can be used for calling things constantly on every tick of the game.
 *
 * @author Tecknix Software
 */
@AllArgsConstructor
public class TMKeyEvent extends TMEvent {

    @Getter
    private int key;

}
