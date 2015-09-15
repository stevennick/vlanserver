package org.ccma.itri.vlanserver.servlet;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.ccma.itri.vlanserver.ISwitch;
import org.ccma.itri.vlanserver.coreswitch.CiscoSwitch;
import org.ccma.itri.vlanserver.model.Vlan;
import org.ccma.itri.vlanserver.worker.ProcessWorker;

@Path("/vlan")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + "; qs=0.9" })
public class VlanService {

	private ISwitch coreSwitch;
	private ProcessWorker worker;

	public VlanService() {
		coreSwitch = new CiscoSwitch();
		worker = ProcessWorker.getInstance();
	}

	@GET
	public List<Vlan> getAllVLan(@QueryParam("switchIp") String switchIp) throws Exception {
		return coreSwitch.getAllVLan(switchIp);
	}

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
	public Vlan createNewVlan(Vlan vlan) throws Exception {
		return coreSwitch.createNewVlan(vlan);
	}

	@Path("/{id}")
	@GET
	public Vlan getVlanById(@PathParam("id") String id) throws Exception {
		return coreSwitch.getVlanById(Long.parseLong(id));
	}
	
	@Path("/{id}")
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
	public Vlan updateVlanById(@PathParam("id") String id, Vlan vlan) throws Exception {
		vlan.setId(Long.parseLong(id));
		return coreSwitch.updateVlanById(vlan);
	}
	
	@Path("/{id}")
	@DELETE
	public boolean deleteVlanById(@PathParam("id") String id) throws Exception {
		return coreSwitch.deleteVlanById(Long.parseLong(id));
	}
	
	@Path("/{id}/demo")
	@GET
	public String getWorkerId() throws Exception {
		String output = "Wherever times you call this method, will still return same object for you.\n";
		return output + worker.toString();
	}
	
	

}
