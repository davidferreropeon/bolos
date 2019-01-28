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
		        <th scope="col">${mensaje}</th>
		    </tr>
		  </thead>
		  <tbody>
		 	
			    <tr>		    	
			      <th scope="row">${bolo.id}</th>
			      <th scope="row">${bolo.fecha}</th>			      		      
			      <th scope="row">${bolo.lugar}</th>
			      <th scope="row">${bolo.banda1}</th>
			      <th scope="row">${bolo.banda2}</th>
			      <th scope="row">${bolo.banda3}</th>
			      <th scope="row">${bolo.idCrew}</th>
			      <th scope="row">
			      	<fmt:formatDate value="${b.fecha}" pattern="yyyy-MM-dd hh:mm:ss"/>
			      </th>
			    </tr>    
		   
		  </tbody>
		</table>

	</main>				

<%@ include file="../../includes/footer.jsp"  %>  