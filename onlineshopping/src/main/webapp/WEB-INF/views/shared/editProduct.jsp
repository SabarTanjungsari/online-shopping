<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!-- Product Modal -->
<div class="modal fade" id="myProductModal" role="dialog" tabindex="-1">
    <div class="modal-dialog" role="document">	
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
                <h4 class="modal-title">Edit Product</h4>
            </div>
            <div class="modal-body">
                <sf:form id="productForm" class="form-horizontal" modelAttribute="product"
                         action="${contextRoot}/manage/products"
                         method="POST"
                         enctype="multipart/form-data"
                         >

                    <div class="form-group">
                        <label class="control-label col-md-4" for="name">Enter
                            Product Name:</label>
                        <div class="col-md-8">
                            <sf:input type="text" path="name" id="name"
                                      placeholder="Product Name" class="form-control" />
                            <sf:errors path="name" cssClass="help-block" element="em" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-4" for="brand">Enter
                            Brand Name:</label>
                        <div class="col-md-8">
                            <sf:input type="text" path="brand" id="brand"
                                      placeholder="Brand Name" class="form-control" />
                            <sf:errors path="brand" cssClass="help-block" element="em" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-4" for="descriptiom">Product
                            Description:</label>
                        <div class="col-md-8">
                            <sf:textarea rows="4" cols="" path="description"
                                         id="description" placeholder="Write a description"
                                         class="form-control"></sf:textarea>
                            <sf:errors path="description" cssClass="help-block" element="em" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-4" for="unitPrice">Enter
                            Unit Price:</label>
                        <div class="col-md-8">
                            <sf:input type="number" path="unitPrice" id="unitPrice"
                                      placeholder="Unit Price In &#x52;&#x70;" class="form-control" />
                            <sf:errors path="unitPrice" cssClass="help-block" element="em" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-4" for="quantity">Quantity
                            Availabe:</label>
                        <div class="col-md-8">
                            <sf:input type="number" path="quantity" id="quantity"
                                      placeholder="Quantity Available" class="form-control" />
                        </div>
                    </div>

                    <!-- File element for image upload -->
                    <div class="form-group">
                        <label class="control-label col-md-4" for="file">Select
                            an Image:</label>
                        <div class="col-md-8">
                            <sf:input type="file" path="file" id="file" class="form-control" />
                            <sf:errors path="file" cssClass="help-block" element="em" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-4" for="categoryId">Select
                            Category: </label>
                        <div class="col-md-8">
                            <sf:select class="form-control" id="categoryId"
                                       path="categoryId" items="${categories}" itemLabel="name"
                                       itemValue="id" />

                            <div class="text-right">
                                <br/>
                                <button id="addCategory" type="button" data-toggle="modal" data-target="#myCategoryModal" class="btn btn-warning">
                                    <span class="glyphicon glyphicon-plus"></span> Add Category
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" name="submit" id="submit" value="Save Changes"
                                   class="btn btn-primary"/>

                            <!-- Hidden field for products  -->
                            <sf:hidden path="id" />
                            <sf:hidden path="code" />
                            <sf:hidden path="supplierId" />
                            <sf:hidden path="active" />
                            <sf:hidden path="purchases" />
                            <sf:hidden path="views" />
                        </div>
                    </div>
                </sf:form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- Category Modal -->
<div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1">
    <div class="modal-dialog" role="document">	
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
                <h4 class="modal-title">Add New Category</h4>
            </div>
            <div class="modal-body">
                <sf:form id="categoryForm" modelAttribute="category" action="${contextRoot}/manage/category" method="POST" class="form-horizontal">
                    <div class="form-group">
                        <label for="category_name" class="control-label col-md-4">Category Name</label>
                        <div class="col-md-8">
                            <sf:input type="text" path="name" id="category_name" class="form-control"/>                                     
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="category_name" class="control-label col-md-4">Category Description</label>
                        <div class="col-md-8">
                            <sf:textarea cols="" rows="5" type="text" path="description" id="category_description" class="form-control"/>                                     
                        </div>
                    </div>
                        
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" value="Add Category" class="btn btn-primary"/>
                            </div>
                        </div>
                </sf:form>
            </div>
        </div>
    </div>
</div>