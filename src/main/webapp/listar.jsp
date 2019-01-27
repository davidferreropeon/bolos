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
		    </tr>
		  </thead>
		  <tbody>
		 	 <c:forEach items="${bolo}" var="b">
			    <tr>		    	
			      <th scope="row">${b.id}</th>
			      <th scope="row">${b.fecha}</th>			      		      
			      <th scope="row">${b.lugar}</th>
			      <th scope="row">${b.banda1}</th>
			      <th scope="row">${b.banda2}</th>
			      <th scope="row">${b.banda3}</th>
			      <th scope="row">${b.idCrew}</th>
			      <th scope="row">
			      	<fmt:formatDate value="${b.fecha}" pattern="yyyy-MM-dd hh:mm:ss"/>
			      </th>
			    </tr>    
		    </c:forEach>
		  </tbody>
		</table>

	</main>				


<%@ include file="../../includes/footer.jsp"  %>  