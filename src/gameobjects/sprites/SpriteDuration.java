package gameobjects.sprites;

import utils.Constants;

public class SpriteDuration {
	private long last_updated;
	private long requiredTime;
	
	public SpriteDuration() {
		this.last_updated = System.currentTimeMillis();
		
		this.requiredTime = Constants.SPRITE_DURATION;
	}

	public boolean isTimeCovered() {
		double time_elapsed = System.currentTimeMillis() - this.last_updated;
		
		if(time_elapsed > this.requiredTime) {
			this.last_updated = System.currentTimeMillis();

			return true;
		}
		return false;
	}
}
