/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2015
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.Instantiable.Effects;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import Reika.DragonAPI.Interfaces.MotionController;

public class EntityFluidFX extends EntityFX {

	private Fluid type;

	private MotionController motionController;

	public EntityFluidFX(World world, double x, double y, double z, Fluid f) {
		this(world, x, y, z, 0, 0, 0, f);
	}

	public EntityFluidFX(World world, double x, double y, double z, double vx, double vy, double vz, Fluid f) {
		super(world, x, y, z, vx, vy, vz);
		particleIcon = f.getStillIcon();
		particleScale = 1F;
		type = f;
		motionX = vx;
		motionY = vy;
		motionZ = vz;
	}

	public EntityFluidFX setGravity(float g) {
		particleGravity = g;
		return this;
	}

	public EntityFluidFX setScale(float s) {
		particleScale = s;
		return this;
	}

	public EntityFluidFX setLife(int l) {
		particleMaxAge = l;
		return this;
	}

	public EntityFluidFX setMotionController(MotionController m) {
		motionController = m;
		return this;
	}

	@Override
	public int getBrightnessForRender(float par1)
	{
		return type.getLuminosity() > 12 ? 240 : super.getBrightnessForRender(par1);
	}

	@Override
	public int getFXLayer()
	{
		return 1;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (motionController != null) {
			motionX = motionController.getMotionX(this);
			motionY = motionController.getMotionY(this);
			motionZ = motionController.getMotionZ(this);
			motionController.update(this);
		}
	}

}
