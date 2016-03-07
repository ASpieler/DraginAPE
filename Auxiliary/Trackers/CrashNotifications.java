package Reika.DragonAPI.Auxiliary.Trackers;

import net.minecraft.crash.CrashReport;
import Reika.DragonAPI.Instantiable.Data.Maps.MultiMap;



public class CrashNotifications {

	public static final CrashNotifications instance = new CrashNotifications();

	private final MultiMap<Class, CrashNotification> entries = new MultiMap();

	private CrashNotifications() {

	}

	/** Supply a null class to wildcard and parse all error types. */
	public void addNotification(Class<? extends Throwable> crashType, CrashNotification n) {
		entries.addValue(crashType, n);
	}

	public void notifyCrash(CrashReport c) {
		Throwable e = c.getCrashCause();
		for (CrashNotification n : entries.get(e.getClass())) {
			String s = n.addMessage(e);
			if (s != null)
				c.getCategory().addCrashSection(n.getLabel(), s);
		}
		for (CrashNotification n : entries.get(null)) {
			String s = n.addMessage(e);
			if (s != null)
				c.getCategory().addCrashSection(n.getLabel(), s);
		}
	}

	public static interface CrashNotification {

		public String getLabel();

		/** Return null to append nothing. */
		public String addMessage(Throwable crash);

	}

}
