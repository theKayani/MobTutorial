package main.mobtutorial.entities;

import main.mobtutorial.util.SoldierColors;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.util.DamageSource;

public class EntitySoldier extends EntityCreature
{
	public EntitySoldier(World world)
	{
		super(world);
		setSize(0.2F, 0.5F);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAITempt(this, 1.0D, Items.paper, false));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
		tasks.addTask(7, new EntityAIWander(this, 1.0D));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, new IEntitySelector()
		{
			@Override
			public boolean isEntityApplicable(Entity entity)
			{
				return entity instanceof EntitySoldier && !((EntitySoldier) entity).getColor().equals(getColor());
			}
		}));
		experienceValue = 0;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(29, new Integer(0));
	}

	@Override
	public void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3F);
	}

	public void setColor(SoldierColors color)
	{
		dataWatcher.updateObject(29, new Integer(color.ordinal()));
		setCustomNameTag(color.name + " Soldier");
	}

	public SoldierColors getColor()
	{
		return SoldierColors.values()[dataWatcher.getWatchableObjectInt(29)];
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData stuff)
	{
		setColor(SoldierColors.random());
		return super.onSpawnWithEgg(stuff);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		if (entity instanceof EntitySoldier)
		{
			if (((EntitySoldier) entity).getColor() == getColor()) return super.attackEntityAsMob(entity);
		}

		super.attackEntityAsMob(entity);
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 1);
	}

	@Override
	public boolean attackEntityFrom(DamageSource ds, float damage)
	{
		if (ds.getEntity() != null && ds.getEntity() instanceof EntityPlayer) return super.attackEntityFrom(ds, getMaxHealth());
		return super.attackEntityFrom(ds, damage);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		setColor(SoldierColors.values()[nbt.getInteger("Color")]);

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("Color", getColor().ordinal());
	}
}
