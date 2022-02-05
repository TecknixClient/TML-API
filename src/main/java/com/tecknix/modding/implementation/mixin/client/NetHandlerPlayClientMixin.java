package com.tecknix.modding.implementation.mixin.client;

import com.tecknix.modding.api.event.EventBus;
import com.tecknix.modding.api.event.type.TMChatEvent;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S02PacketChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class NetHandlerPlayClientMixin {

    @Inject(method = "handleChat", at = @At("HEAD"), cancellable = true)
    public void handleChat(S02PacketChat packetIn, CallbackInfo ci) {
        final TMChatEvent event = new TMChatEvent(packetIn.getChatComponent());
        EventBus.post(event);

        if (event.isCanceled())
            ci.cancel();
    }
}
