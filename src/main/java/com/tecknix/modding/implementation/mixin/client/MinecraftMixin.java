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
import com.tecknix.modding.api.event.type.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "startGame", at = @At("HEAD"), cancellable = true)
    private void injectStartGame(CallbackInfo ci) {
        final TMStartupEvent event = new TMStartupEvent();

        if (event.isCanceled()) {
            ci.cancel();
        }

        EventBus.post(event);
    }

    @Inject(method = "shutdown", at = @At("HEAD"), cancellable = true)
    private void injectShutDown(CallbackInfo ci) {
        final TMShutdownEvent event = new TMShutdownEvent();

        if (event.isCanceled()) {
            ci.cancel();
        }

        EventBus.post(event);
    }

    @Inject(method = "runTick", at = @At("HEAD"), cancellable = true)
    private void injectRunTick(CallbackInfo ci) {
        final TMTickEvent event = new TMTickEvent();
        EventBus.post(event);

        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButton()I"))
    private void mouseUpdate(CallbackInfo callbackInfo) {
        EventBus.post(new TMMouseEvent());
    }


    @Inject(method = "displayGuiScreen", at = @At(value = "HEAD"), cancellable = true)
    private void guiDisplayed(GuiScreen s, CallbackInfo callbackInfo) {
        final TMGuiOpenedEvent event = new TMGuiOpenedEvent(s);

        if (event.isCanceled()) {
            callbackInfo.cancel();
        }

        EventBus.post(event);
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", remap = false, target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", ordinal = 0, shift = At.Shift.BEFORE))
    private void onKeyboard(CallbackInfo callbackInfo) {

        int key = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();

        if (Keyboard.getEventKeyState()) {
            EventBus.post(new TMKeyEvent(key));
        }
    }
}
