package fr.darkxell.galaxia;

public abstract class Updater {

	private final int ups;
	private Thread t;

	public Updater(int ups) {
		this.ups = ups;
	}

	public void run() {
		this.t = new Thread(new Runnable() {

			@Override
			public void run() {
				long start = System.currentTimeMillis();
				int frame = 0, miliperframe = 1000 / ups;
				while (true) {
					long timetowait = start + (frame * miliperframe);
					while (System.currentTimeMillis() < timetowait) {
						try {
							Thread.sleep(4);
						} catch (InterruptedException e) {
						}
					}
					update();
					frame++;
				}
			}
		});
		t.start();
	}

	public abstract void update();

}
