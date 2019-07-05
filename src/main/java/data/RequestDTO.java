package data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RequestDTO {
	@Min(value = 1)
	@NotNull
	Integer utmZone;

	@NotNull
	String latitudeBand;

	@NotNull
	String gridSquare;

	@Pattern(regexp = "[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")
	@NotNull
	String date;

	@NotNull
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
