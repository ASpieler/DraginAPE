/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2014
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.Exception;

import Reika.DragonAPI.Base.DragonAPIMod;
import Reika.DragonAPI.Extras.ModVersion;

public class VersionMismatchException extends DragonAPIException {

	public VersionMismatchException(DragonAPIMod mod, ModVersion v, DragonAPIMod mod2, ModVersion v2) {
		this(mod, v, mod2.getDisplayName(), v2);
	}

	public VersionMismatchException(DragonAPIMod mod, ModVersion v, String mod2, ModVersion v2) {
		message.append(mod.getDisplayName()+" was not installed correctly:\n");
		message.append(mod.getDisplayName()+" "+v+" was installed with "+mod2+" "+v2+"\n");
		if (v.majorVersion != v2.majorVersion) {
			message.append("The major version numbers must match!\n");
		}
		else {
			message.append("Version "+v+" of "+mod+" cannot run with "+mod2+" "+v2+"\n");
		}
		message.append("This is not a "+mod.getDisplayName()+" bug. Do not post it to "+mod.getDocumentationSite().toString()+" unless you are really stuck.");
		this.crash();
	}

	public static final class APIMismatchException extends VersionMismatchException {

		public APIMismatchException(DragonAPIMod mod, ModVersion version, ModVersion api) {
			super(mod, version, "DragonAPI", api);
		}

	}

}