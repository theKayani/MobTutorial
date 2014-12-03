package main.mobtutorial;

import main.mobtutorial.entities.EntitySoldier;
import main.mobtutorial.entities.RenderSoldier;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class MobClient extends MobCommon
{
	@Override
	public void registerRenderThings()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntitySoldier.class, new RenderSoldier());
	}
}
