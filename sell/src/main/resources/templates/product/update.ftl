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
                            <a href="/sell/seller/product/list">订单列表</a>
                        </li>
                        <li class="active">
                            订单详情
                        </li>
                    </ul>
                </div>
                <div class="col-md-12 column">
                    <form role="form" style="width: 25%" action="/sell/seller/product/update" method="post">
                        <div class="form-group">
                            <label for="productName">商品名称</label>
                            <input type="text" class="form-control" id="productName"
                                   value="${productInfo.productName}"
                                   name="productName"/>
                        </div>
                        <div class="form-group">
                            <label for="productIcon">图片</label>
                            <div>
                                <img style="width: 100px;height: 80px" src="${productInfo.productIcon}">
                            </div>
                            <input type="hidden" class="form-control" id="productIcon"
                                   value="${productInfo.productIcon}"
                                   name="productIcon"/>
                        </div>
                        <div class="form-group">
                            <label for="productPrice">单价</label>
                            <input type="text" class="form-control" id="productPrice"
                                   value="${productInfo.productPrice}" name="productPrice"/>
                        </div>
                        <div class="form-group">
                            <label for="productStock">库存</label>
                            <input type="text" class="form-control" id="productStock"
                                   value="${productInfo.productStock}" name="productStock"/>
                        </div>
                        <div class="form-group">
                            <label for="productDescription">描述</label>
                            <textarea class="form-control" id="productDescription" name="productDescription">${productInfo.productDescription}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="categoryType">类目</label>
                            <select class="form-control" id="categoryType" name="categoryType">
                                <option value="">请选择</option>
                                <#list categoryList as category>
                                    <#if productInfo.categoryType == category.categoryType>
                                    <option value="${category.categoryType}" selected>${category.categoryName}</option>
                                    <#else >
                                <option value="${category.categoryType}">${category.categoryName}</option>
                                    </#if>
                                </#list>
                            </select>
                        </div>
                        <input type="hidden" value="${productInfo.productId}" name="productId">
                        <input type="hidden" value="${productInfo.productStatus}" name="productStatus">
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