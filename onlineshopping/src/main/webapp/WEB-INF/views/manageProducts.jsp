<div class="container">
    <div class="row">
        <c:if test="${not empty message}">
            <br/>
            <div class="alert alert-success alert-dismissable">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                ${message}
        </c:if>
    </div>
    <div class="row">     
        <div class="col-xs-12">
            <button type="button" class="btn btn-primary" id="addProduct" onclick="addProduct()">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
            <strong>Products Availabe</strong>
            <hr/>
        </div>
        <div class="col-xs-12">
            <div class="container-fluid">
                <div class="table-responsive">
                    <!-- Product Table for Admin --->
                    <table id="adminProductsTable" class="table table-striped table-bordered table-responsive">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>&#160</th>
                                <th>Name</th>
                                <th>Brand</th>
                                <th>Quantity</th>
                                <th>Unit Price</th>
                                <th>Active</th>
                                <th>Edit</th>
                            </tr>
                        </thead>

                        <tfoot>
                            <tr>
                                <th>Id</th>
                                <th>&#160</th>
                                <th>Name</th>
                                <th>Brand</th>
                                <th>Quantity</th>
                                <th>Unit Price</th>
                                <th>Active</th>
                                <th>Edit</th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
