<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html lang=es>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>DeBolo</title>

    <!-- Bootstrap core CSS  DFDFDF-->
    <link href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/login.css" rel="stylesheet">
<!-- FONT AWESOME -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
  <link rel="stylesheet" href="css/styles.css">
  
  </head>
	
	
<body>
<main class="container">
		
			
			<section>
				<form  class="form-signin" action="bolo?op=listar" method="post" >
				 	  
				  	 	<div class="row "> 
						   
						   <input class="col-sm 4" type="number" id="fecha" name=fecha placeholder=" id"  class="form-control">
					 	</div>
					    <button class="btn btn-lg  btn-block mb-3 mt-3  btn-info" type="submit">
							buscar bolo por id
						</button>
				</form>
			</section>

		
		
</main>
</body>	

</html>