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
                            <a href="/sell/seller/category/list">类目列表</a>
                        </li>
                        <li class="active">
                            类目详情
                        </li>
                    </ul>
                </div>
                <div class="col-md-12 column">
                    <form role="form" style="width: 25%" action="/sell/seller/category/update" method="post">
                        <div class="form-group">
                            <label for="categoryName">类目名称</label>
                            <input type="text" class="form-control" id="categoryName"
                                   value=""
                                   name="categoryName"/>
                        </div>
                        <div class="form-group">
                            <label for="categoryType">type</label>
                            <input type="text" class="form-control" id="categoryType"
                                   value="" name="categoryType"/>
                        </div>
                        <#--<div class="form-group">-->
                            <#--<label for="createTime">创建时间</label>-->
                            <#--<input type="text" class="form-control" id="createTime"-->
                                   <#--value="${category.createTime}" name="createTime"/>-->
                        <#--</div>-->
                        <#--<div class="form-group">-->
                            <#--<label for="updateTime">更新时间</label>-->
                            <#--<input class="form-control" value="${category.updateTime}" id="updateTime" name="updateTime">-->
                        <#--</div>-->
                        <input type="hidden" value="" name="categoryId">
                        <button type="submit" class="btn btn-default btn-primary">添加</button>
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