<html xmlns:th="http://www.thymeleaf.org">
<#include '../common/header.ftl'>
<style>
    th, td {
        text-align: center;
    }</style>
<body>
<#--左边框-->
<div id="wrapper" class="toggled">
    <#include '../common/nav.ftl'>

    <div id="page-content-wrapper">
        <div class="container" style="width: 100%">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <ul class="breadcrumb">
                        <li>
                            <a href="#">首页</a>
                        </li>
                        <li>
                            <a href="/sell/seller/product/list">商品列表</a>
                        </li>
                        <li class="active">
                            商品新增
                        </li>
                    </ul>
                </div>
                <div class="col-md-12 column">
                    <form role="form" style="width: 25%" action="/sell/seller/product/save" method="post">
                        <div class="form-group">
                            <label for="productName">商品名称</label>
                            <input type="text" class="form-control" id="productName"
                                   value=""
                                   name="productName"/>
                        </div>
                        <div class="form-group">
                            <label for="productIcon">图片</label>
                            <div>
                                <img style="width: 100px;height: 80px" src="">
                            </div>
                            <input type="text" class="form-control" id="productIcon"
                                   value=""
                                   name="productIcon"/>
                        </div>
                        <div class="form-group">
                            <label for="productPrice">单价</label>
                            <input type="text" class="form-control" id="productPrice"
                                   value="" name="productPrice"/>
                        </div>
                        <div class="form-group">
                            <label for="productStock">库存</label>
                            <input type="text" class="form-control" id="productStock"
                                   value="" name="productStock"/>
                        </div>
                        <div class="form-group">
                            <label for="productDescription">描述</label>
                            <textarea class="form-control" id="productDescription" name="productDescription"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="categoryType">类目</label>
                            <select class="form-control" id="categoryType" name="categoryType">
                                <option value="">请选择</option>
                                <#list categoryList as category>
                                <option value="${category.categoryType}">${category.categoryName}</option>
                                </#list>
                            </select>
                        </div>
                        <input type="hidden" value="" name="productId">
                        <input type="hidden" value="" name="productStatus">
                        <button type="submit" class="btn btn-default btn-primary">修改</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>

</script>