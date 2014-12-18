package Chaos.Util;


public final class Misc {
	// All System.out.println(); use as this:
	public static final void log(String msg) {
		System.out.println("[Info] " + msg);
	}

	public static void err(Exception e) {
		e.printStackTrace();

	}
}
