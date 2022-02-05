package com.tecknix.modding.api.event.type;

import com.tecknix.modding.api.event.TMEvent;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;

/**
 * TMMouseEvent
 * <p>
 * This event is called in the {@link Minecraft} class.
 * It gets called on every mouse movement you made.
 * <p>
 * It can <b>NOT</b> be canceled.
 *
 * @author Si1kn
 */
public class TMMouseEvent extends TMEvent {

    /**
     * @returns Absolute x.
     */
    @Getter
    private final int x;

    /**
     * @returns Absolute y.
     */
    @Getter
    private final int y;

    /**
     * @returns Absolute delta x.
     */
    @Getter
    private final int dx;

    /**
     * @returns Absolute delta y.
     */
    @Getter
    private final int dy;

    /**
     * @returns Absolute delta z.
     */
    @Getter
    private final int dwheel;

    /**
     * Current mouse button being pressed.
     *
     * @returns -1 if not pressed, 0 is left, right 1, middle lick is 2
     */
    @Getter
    private final int button;


    public TMMouseEvent() {
        this.x = Mouse.getEventX();
        this.y = Mouse.getEventY();
        this.dx = Mouse.getEventDX();
        this.dy = Mouse.getEventDY();
        this.dwheel = Mouse.getEventDWheel();
        this.button = Mouse.getEventButton();
    }
}
