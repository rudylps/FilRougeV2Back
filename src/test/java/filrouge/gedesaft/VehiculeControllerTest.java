package filrouge.gedesaft;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import filrouge.gedesaft.controller.VehiculeController;
import filrouge.gedesaft.dao.JdbcVehiculeDAO;
import filrouge.gedesaft.model.Vehicule;
import filrouge.gedesaft.service.VehiculeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=VehiculeController.class, secure=false)
public class VehiculeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VehiculeService vehiculeService;
	
	@MockBean
	private JdbcVehiculeDAO dataDaoImpl;
	
	@Test
    public void getVehiculeDetail() throws Exception {
			
		Vehicule vehicule = new Vehicule();
		vehicule.setId((long) 1);
		vehicule.setType("camion");
	
		Mockito.when(vehiculeService.getVehiculeDetail((long) 1)).thenReturn(vehicule);
			
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/vehicule/1").accept(MediaType.APPLICATION_JSON);
			
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		       
        System.out.println(result.getResponse());
        String expected = "{id:1, type:camion}";
	        
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}
