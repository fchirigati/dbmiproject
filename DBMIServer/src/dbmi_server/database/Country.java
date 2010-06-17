package dbmi_server.database;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Country {

	@PrimaryKey
    private String code;

	@Persistent
    private String name;
    
    @Persistent(mappedBy = "country")
    private List<Airport> airports = new ArrayList<Airport>();

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public List<Airport> getAirports() {
		return airports;
	}
}
