<div class="container">
	<div class="row">

		<!-- Would be to display sidebar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>

		<!-- to display the actual products -->
		<div class="col-md-9">

			<!-- Added breadcrumb component -->
			<div class="row">
				<div class="col-lg-12">
					<ol class="breadcrumb">

						<c:if test="${userClickAllProducts == true}">
							<li><a href="${contextRoot}/home">Home</a></li>
							&nbsp;/&nbsp; 
							<li class="active">All Products</li>
						</c:if>

						<c:if test="${userClickCategoryProducts == true}">
							<li><a href="${contextRoot}/home">Home</a></li>
							&nbsp;/&nbsp; 
							<li class="active">Category</li>
							&nbsp;/&nbsp; 
							<li class="active">${category.name}</li>
						</c:if>

					</ol>
				</div>
			</div>

		</div>

	</div>
</div>