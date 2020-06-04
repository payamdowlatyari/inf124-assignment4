package iae.s20;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
public class ProductResource {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAllProducts() {
		List<Product> products = ProductService.getAllProducts();
		
		if(products == null || products.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(products).build();
	}
	
	@Path("{id}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getProductById(@PathParam("id") int id) {
		Product product = ProductService.getProductById(id);
		
		if(product == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(product).build();
	}
}
