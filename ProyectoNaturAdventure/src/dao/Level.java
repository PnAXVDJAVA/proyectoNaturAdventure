package dao;

public enum Level {

	easy,
	medium,
	hard;

	public static Level getOpcion(String level) {
		return valueOf(level);
	}
}
