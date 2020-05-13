package fr.darkxell.galaxia.logs;

/**
 * Makeshift logging utility, because LOG4J sucks ass for simple projects. Don't
 * judge me.
 */
public class Logger {

	protected static final Logger INSTANCE;

	public static Logger getInstance() {
		return INSTANCE;
	}

	static {
		INSTANCE = new Logger();
	}

	public LogLevel loggerLevel = LogLevel.DEBUG;

	public enum LogLevel {
		DEBUG("Debug", 0), INFO("Info", 1), WARN("Warn", 3), ERROR("Error", 5), FATAL("Fatal", 10);
		String prefix;
		int level;

		private LogLevel(String prefix, int level) {
			this.prefix = prefix;
			this.level = level;
		}
	}

	public void log(LogLevel level, String log) {
		if (this.loggerLevel.level <= level.level)
			System.out.println("[" + level.prefix + "] " + log);
	}

	public static void i(String log) {
		INSTANCE.log(LogLevel.INFO, log);
	}

	public static void d(String log) {
		INSTANCE.log(LogLevel.DEBUG, log);
	}

	public static void w(String log) {
		INSTANCE.log(LogLevel.WARN, log);
	}

	public static void e(String log) {
		INSTANCE.log(LogLevel.ERROR, log);
	}

	public static void f(String log) {
		INSTANCE.log(LogLevel.FATAL, log);
	}

}
