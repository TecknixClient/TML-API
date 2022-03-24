package com.tecknix.modding.implementation.tweaker;

import com.google.common.collect.Lists;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.List;

public class DevTweaker implements ITweaker {

    private final List<String> launchArguments = Lists.newArrayList();


    @Override
    public final void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.launchArguments.addAll(args);

        if (!args.contains("--version") && profile != null) {
            this.launchArguments.add("--version");
            this.launchArguments.add(profile);
        }
        if (!args.contains("--assetDir") && assetsDir != null) {
            this.launchArguments.add("--assetDir");
            this.launchArguments.add(assetsDir.getAbsolutePath());
        }
        if (!args.contains("--gameDir") && gameDir != null) {
            this.launchArguments.add("--gameDir");
            this.launchArguments.add(gameDir.getAbsolutePath());
        }
    }

    @Override
    public final void injectIntoClassLoader(LaunchClassLoader classLoader) {
        MixinBootstrap.init();

        MixinEnvironment environment = MixinEnvironment.getDefaultEnvironment();

        Mixins.addConfiguration("mixins." + "moddingapi" + ".json");

        if (environment.getObfuscationContext() == null) {
            environment.setObfuscationContext("notch");
        }

        environment.setSide(MixinEnvironment.Side.CLIENT);
    }

    @Override
    public String getLaunchTarget() {
        return MixinBootstrap.getPlatform().getLaunchTarget();
    }

    @Override
    public String[] getLaunchArguments() {
        return this.launchArguments.toArray(new String[0]);
    }
}