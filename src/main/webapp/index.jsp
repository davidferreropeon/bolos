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
	
<!--  -->	
<body>
<main class="container">
		
			
			<section>
		<a href="bolo?op=listar" class=" btn btn-outline-primary btn-block">listar </a>
			</section>

	
			<form action="bolo?op=buscar" method="post">
			
				<div class="form-group">
				    <input type="number" name="id"  id="id" placeholder="id" class="form-control">			    
				</div>	
				
				<button type="submit" class="btn btn-primary btn-block">Buscar por ID</button>
		</form>
</main>
</body>	

</html>