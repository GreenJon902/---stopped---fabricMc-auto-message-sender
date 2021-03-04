package net.GreenJon902.auto.message.sender;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AutoMessageSender implements ModInitializer {
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		KeyBinding run = KeyBindingHelper.registerKeyBinding(new KeyBinding("Reload", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Auto Message Sender"));
		Path config_file_path = Paths.get(String.valueOf(FabricLoader.getInstance().getConfigDir()), "asm.json");
		LOGGER.info(new StringBuilder().append("ASM config file should be at ").append(config_file_path));
		LOGGER.info("Checking for config file");

		try {
			File config_file = new File(String.valueOf(config_file_path));
			if (config_file.createNewFile()) {
				LOGGER.info("No config file found, creating a new one");

			} else {
				LOGGER.info("File Located!");
			}
		} catch (IOException e) {
			LOGGER.info("An error occurred");
			e.printStackTrace();
		}

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (run.wasPressed()) {
				client.player.sendChatMessage("/help");
			}
		});

        LOGGER.info("ASM Was initiated");
	}
}
