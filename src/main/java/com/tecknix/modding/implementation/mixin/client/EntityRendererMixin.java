package com.tecknix.modding.implementation.mixin.client;

import com.tecknix.modding.api.event.EventBus;
import com.tecknix.modding.api.event.type.TMRenderWorldEvent;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

    @Inject(method = "renderWorldPass", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;renderHand:Z", shift = At.Shift.BEFORE))
    private void renderWorldPassPre(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackInfo) {
        EventBus.post(new TMRenderWorldEvent(partialTicks, TMRenderWorldEvent.State.PRE));
    }

    @Inject(method = "renderWorldPass", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;renderHand:Z", shift = At.Shift.AFTER))
    private void renderWorldPassPost(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackInfo) {
        EventBus.post(new TMRenderWorldEvent(partialTicks, TMRenderWorldEvent.State.POST));
    }


}