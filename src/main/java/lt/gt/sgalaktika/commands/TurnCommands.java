package lt.gt.sgalaktika.commands;

import java.util.ArrayList;
import java.util.List;

public class TurnCommands {
	private List<OrganisationCommand> organisationCommands = new ArrayList<OrganisationCommand>();
	private List<BuildCommand> buildCommands = new ArrayList<BuildCommand>();
	private List<FlightCommand> flightCommands = new ArrayList<FlightCommand>();
	
	public List<OrganisationCommand> getOrganisationCommands() {
		return organisationCommands;
	}
	public void setOrganisationCommands(
			List<OrganisationCommand> organisationCommands) {
		this.organisationCommands = organisationCommands;
	}
	public List<BuildCommand> getBuildCommands() {
		return buildCommands;
	}
	public void setBuildCommands(List<BuildCommand> buildCommands) {
		this.buildCommands = buildCommands;
	}
	public List<FlightCommand> getFlightCommands() {
		return flightCommands;
	}
	public void setFlightCommands(List<FlightCommand> flightCommands) {
		this.flightCommands = flightCommands;
	}
	
	
}
