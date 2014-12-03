package main.mobtutorial.util;

import java.util.Random;
import net.minecraft.item.ItemDye;

public enum SoldierColors
{
	BLACK,
	RED,
	GREEN,
	BROWN,
	BLUE,
	PURPLE,
	CYAN,
	SILVER,
	GRAY,
	PINK,
	LIME,
	YELLOW,
	LIGHT_BLUE,
	MAGENTA,
	ORANGE,
	WHITE;

	public final String name;
	public final int color;

	private SoldierColors()
	{
		name = name().substring(0, 1).toUpperCase().replaceAll("_", " ") + name().substring(1).toLowerCase().replaceAll("_", " ");
		color = ItemDye.field_150922_c[ordinal()];
	}

	public static SoldierColors random()
	{
		return values()[new Random().nextInt(values().length)];
	}
}
