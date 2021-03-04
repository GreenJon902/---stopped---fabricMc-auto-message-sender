package net.GreenJon902.auto.message.sender;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AutoMessageSender implements ModInitializer {
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		KeyBinding reloadKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("Reload config", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Auto Message Sender"));
		KeyBinding openConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("Open config file", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Auto Message Sender"));

		Path config_file_path = Paths.get(String.valueOf(FabricLoader.getInstance().getConfigDir()), "asm.json");

		LOGGER.info(new StringBuilder().append("ASM config file should be at ").append(config_file_path));
		LOGGER.info("Checking for config file");

		try {
			File config_file = new File(String.valueOf(config_file_path));
			if (config_file.createNewFile()) {
				LOGGER.info("No config file found, creating a new one");
				try {
					FileWriter config_file_writer = new FileWriter("filename.txt");
					config_file_writer.write("{\n" +
							"    \"comments1\": \"this is where the config for ASM will go\",\n" +
							"    \"comments2\": \"format it like this   'Letter': 'Message/command'\",\n" +
							"    \"comments3\": \"        'a': 'Hello World'\",\n" +
							"    \"comments4\": \"        'b': '/give @s diamond 100'\",\n" +
							"    \"comments5\": \"\",\n" +
							"    \"comments6\": \" or you can do some more compliacted events\",\n" +
							"    \"comments7\": \"        '{Player_Joins}': 'Weclome Back'\",\n" +
							"    \"comments8\": \"        '{Player_Dies}': ['Rip', 'Oof, 'Get Gud'] randomly select one of these'\",\n" +
							"    \"comments9\": \"        ‘q’: [‘gg’, ’well done', ‘Nice!']\",\n" +
							"    \"comments10\": \"\",\n" +
							"\n" +
							"    \"h\": \"Example Message\"\n" +
							"}");
					config_file_writer.close();
					LOGGER.info("Successfully created new config file");
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}

			} else {
				LOGGER.info("File Located!");
			}
		} catch (IOException e) {
			LOGGER.info("An error occurred");
			e.printStackTrace();
		}

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (reloadKey.wasPressed()) {
				client.player.sendMessage(Text.of("Reloading ASM Config"), true);
			}

			while (openConfigKey.wasPressed()) {
				client.player.sendMessage(Text.of("Opening the ASM Config file"), true);
			}
		});

        LOGGER.info("ASM Was initiated");
	}
}
