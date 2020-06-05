package iae.s20;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/orders")
public class OrderResource {
	@POST
//	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Consumes({MediaType.APPLICATION_JSON})
//	@Consumes({MediaType.TEXT_PLAIN})
	public Response addOrder(Order order) {
		if(OrderService.AddOrder(order) == true) {
			return Response.ok().entity("Order added succeessfully").build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@Path("/product")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Response addProductOrder(ProductOrder po) {
		if(OrderService.AddOrderProduct(po.getOrderId(), po.getProductId(), po.getQuantity()) == true) {
			return Response.ok().entity("Order product added successfully").build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
