<%@ include file="../../includes/header.jsp"  %>

<%@ include file="../../includes/alerta.jsp"  %>

    <main role="main" class="container">
	
	
		<c:if test = "${mensaje!=null}">	
			<div class="alert alert-info alert-dismissible fade show" role="alert">
		  		<strong> ${mensaje }</strong>
			  	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			   	 	<span aria-hidden="true">&times;</span>
			  	</button>
			</div>
		</c:if>	
	
		<a href="nuevo.jsp" class="btn btn-outline-primary mb-2">"Nuevo Bolo"</a>
		<a href="nuevo.jsp" class="btn btn-outline-primary mb-2">"Modificar Bolo"</a>
		
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
		 	 <c:forEach items="${bolos}" var="b">
			    <tr>		    	
			      <th scope="row">${b.id}</th>
			      <th scope="row">${b.fecha}</th>			      		      
			      <th scope="row">${b.lugar}</th>
			      <th scope="row">${b.banda1}</th>
			      <th scope="row">${b.banda2}</th>
			      <th scope="row">${b.banda3}</th>
			    
			      <th scope="row">
			      	<fmt:formatDate value="${b.fecha}" pattern="yyyy-MM-dd hh:mm:ss"/>
			      </th>
			    </tr>    
		    </c:forEach>
		  </tbody>
		</table>

	</main>				

<%@ include file="../../includes/footer.jsp"  %>  