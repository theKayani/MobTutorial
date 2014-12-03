package main.mobtutorial.entities;

import java.awt.Color;
import main.mobtutorial.MobTutorial;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSoldier extends RenderLiving
{
	public RenderSoldier()
	{
		super(new ModelSoldier(), 0.5F);
		setRenderPassModel(mainModel);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase entity, int pass, float f)
	{
		if (pass == 0)
		{
			Color color = new Color(((EntitySoldier) entity).getColor().color);
			float r = color.getRed() / 255.0F;
			float g = color.getGreen() / 255.0F;
			float b = color.getBlue() / 255.0F;
			float a = color.getAlpha() / 255.0F;
			GL11.glColor4f(r, g, b, a);
			return 1;
		}
		else return -1;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity soldier)
	{
		return new ResourceLocation(MobTutorial.Info.MODID + ":textures/entities/soldier.png");
	}
}