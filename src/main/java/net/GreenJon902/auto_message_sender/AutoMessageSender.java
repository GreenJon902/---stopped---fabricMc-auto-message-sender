package net.GreenJon902.auto_message_sender;

import net.fabricmc.api.ModInitializer;

public class AutoMessageSender implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");
	}
}
