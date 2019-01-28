<%@ include file="../../includes/header.jsp"  %>
<%@ include file="../../includes/navbar.jsp"  %>
<%@ include file="../../includes/mensajes.jsp"  %>

    <main role="main" class="container">
	
		<a href="" class="btn btn-outline-success mb-2">""</a>
		
		<table class="table tablaOrdenable">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">id</th>
		      <th scope="col">fecha</th>
		      <th scope="col">lugar</th>
		      <th scope="col">banda1</th>
		      <th scope="col">banda2</th>
		      <th scope="col">banda3</th>
		      <th scope="col">idCrew</th> 
		      <th scope="col">${mensaje}</th>
		    </tr>
		  </thead>
		  <tbody>
	
			    <tr>		    	
			      <th scope="row">${boloBuscar.id}</th>
			      <th scope="row">${boloBuscar.fecha}</th>			      		      
			      <th scope="row">${boloBuscar.lugar}</th>
			      <th scope="row">${boloBuscar.banda1}</th>
			      <th scope="row">${boloBuscar.banda2}</th>
			      <th scope="row">${boloBuscar.banda3}</th>
			      <th scope="row">${boloBuscar.idCrew}</th>
			      <th scope="row">
			      	<fmt:formatDate value="${boloBuscar.hora}" pattern="yyyy-MM-dd hh:mm:ss"/>
			      </th>
			    </tr>    
		 
		  </tbody>
		</table>

	</main>				

<%@ include file="../../includes/footer.jsp"  %>  