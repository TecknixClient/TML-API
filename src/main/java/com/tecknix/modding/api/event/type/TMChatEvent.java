package com.tecknix.modding.api.event.type;


import com.tecknix.modding.api.event.TMEvent;
import lombok.Getter;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

/**
 * TMChatEvent
 * <p>
 * This event is called in the {@link NetHandlerPlayClient} class.
 * It gets called on every chat message that you recived.
 * <p>
 * It can be canceled.
 *
 * @author Si1kn
 */
public class TMChatEvent extends TMEvent {

    /**
     * When this gets used, the string will display for example:
     * <p><font color="white">
     * "&lt;§rPlayer0§r&gt; §rI love tecknix!§r"
     * </font>
     */
    @Getter
    private final String unformattedText;

    /**
     * When this gets used, the string will display for example:
     * <p><font color="white">
     * "&lt;Player0&gt; I love tecknix!"
     * </font>
     */
    @Getter
    private final String formattedText;

    public TMChatEvent(IChatComponent message) {
        this.unformattedText = message.getUnformattedText();
        this.formattedText = message.getFormattedText();
    }
}
