/*
 * Copyright Tecknix Software 2022.
 *
 * This is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.tecknix.modding.implementation.mixin.client;

import com.tecknix.modding.api.event.EventBus;
import com.tecknix.modding.api.event.type.TMGuiOpenedEvent;
import com.tecknix.modding.api.event.type.TMMouseEvent;
import com.tecknix.modding.api.event.type.TMTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "startGame", at = @At("HEAD"))
    private void injectStartGame(CallbackInfo ci) {
        //TODO Start Event of some sort.
    }

    @Inject(method = "runTick", at = @At("HEAD"), cancellable = true)
    private void injectRunTick(CallbackInfo ci) {
        final TMTickEvent event = new TMTickEvent();
        EventBus.post(event);

        if (event.isCanceled()) ci.cancel();
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButton()I"))
    private void mouseUpdate(CallbackInfo callbackInfo) {
        EventBus.post(new TMMouseEvent());
    }


    @Inject(method = "displayGuiScreen", at = @At(value = "HEAD"), cancellable = true)
    private void guiDisplayed(GuiScreen s, CallbackInfo callbackInfo) {
        TMGuiOpenedEvent t = new TMGuiOpenedEvent(s);

        EventBus.post(t);

        if (t.isCanceled())
            callbackInfo.cancel();
    }
}
