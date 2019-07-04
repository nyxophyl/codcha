package data;

public enum ChannelMap {
	visible(new String[] {"B04", "B03", "B02"}), vegetation(new String[] {"B05", "B06", "B07"}), waterVapor(new String[] {null, null, "B09"});
	
	private String[] rgbPostfixes;

	private ChannelMap(String[] rgbPostfixes) {
		this.rgbPostfixes = rgbPostfixes;
	}

	public String[] getRgbPostfixes() {
		return this.rgbPostfixes;
	}
}
