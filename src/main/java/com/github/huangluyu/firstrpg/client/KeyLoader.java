package com.github.huangluyu.firstrpg.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyLoader
{
    public static KeyBinding showTime;

    public KeyLoader()
    {
        KeyLoader.showTime = new KeyBinding("key.frpg.showTime", Keyboard.KEY_H, "key.categories.frpg");

        ClientRegistry.registerKeyBinding(KeyLoader.showTime);
    }
}