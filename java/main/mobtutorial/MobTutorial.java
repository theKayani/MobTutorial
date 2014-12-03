package main.mobtutorial;

import main.mobtutorial.entities.EntitySoldier;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

@Mod(modid = MobTutorial.Info.MODID, name = "Mob Tutorial", version = MobTutorial.Info.VERSION)
public class MobTutorial
{
	@SidedProxy(clientSide = MobTutorial.Info.CLIENT_CLASS, serverSide = MobTutorial.Info.COMMON_CLASS)
	public static MobCommon proxy;

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		registerEntity(EntitySoldier.class, "Soldier", EnumCreatureType.creature);
		proxy.registerRenderThings();
	}

	public void registerEntity(Class<? extends EntityLiving> entityClass, String mobName, EnumCreatureType creature)
	{
		int id = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, mobName, id, 0xFFFFFF, 0x000000);
		EntityRegistry.registerModEntity(entityClass, mobName, id, this, 80, 3, true);
	}

	public static class Info
	{
		public static final String MODID = "mobtutorial";
		public static final String VERSION = "1.0";
		public static final String CLIENT_CLASS = "main.mobtutorial.MobClient";
		public static final String COMMON_CLASS = "main.mobtutorial.MobCommon";
	}
}