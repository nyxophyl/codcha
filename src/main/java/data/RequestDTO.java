package data;

public class RequestDTO {
	Integer utmZone;
	String latitudeBand;
	String gridSquare;
	String date;
	String channelMap;

	public RequestDTO () {
	}

	public RequestDTO(Integer utmZone, String latitudeBand, String gridSqare, String date, String channelMap) {
		this.utmZone = utmZone;
		this.latitudeBand = latitudeBand;
		this.gridSquare = gridSqare;
		this.date = date;
		this.channelMap = channelMap;
	}

	public Integer getUtmZone() {
		return utmZone;
	}

	public void setUtmZone(Integer utmZone) {
		this.utmZone = utmZone;
	}

	public String getLatitudeBand() {
		return latitudeBand;
	}

	public void setLatitudeBand(String latitudeBand) {
		this.latitudeBand = latitudeBand;
	}

	public String getGridSquare() {
		return gridSquare;
	}

	public void setGridSquare(String gridSquare) {
		this.gridSquare = gridSquare;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getChannelMap() {
		return channelMap;
	}

	public void setChannelMap(String channelMap) {
		this.channelMap = channelMap;
	}
	
	@Override
	public String toString() {
		return "utmZone: " + this.utmZone + ", latitudeBand: " + this.latitudeBand + ", gridSquare: " + this.gridSquare + ", date: " + this.date + ", channelMap: " + this.channelMap;
	}
}
