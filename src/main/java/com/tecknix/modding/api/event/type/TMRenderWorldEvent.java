package com.tecknix.modding.api.event.type;

import com.tecknix.modding.api.event.TMEvent;
import lombok.Data;
import lombok.NonNull;

/**
 * TMRenderWorldEvent
 * <p>
 * This event is called in the {@link net.minecraft.client.renderer.EntityRenderer} class.
 * It is used to render 3D elements in the world.
 *
 * @author quickdaffy
 */

@Data
public class TMRenderWorldEvent extends TMEvent {
    @NonNull private float partialTicks;
    @NonNull private State state;

    public enum State {
        PRE, POST
    }
}
