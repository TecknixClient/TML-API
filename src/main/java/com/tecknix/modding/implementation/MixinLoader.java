package com.tecknix.modding.implementation;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

public class MixinLoader {

    public static void init() {
        MixinBootstrap.init();

        MixinEnvironment environment = MixinEnvironment.getDefaultEnvironment();

        Mixins.addConfiguration("mixins.moddingapi.json");

        if (environment.getObfuscationContext() == null) {
            environment.setObfuscationContext("notch");
        }

        environment.setSide(MixinEnvironment.Side.CLIENT);
    }
}
