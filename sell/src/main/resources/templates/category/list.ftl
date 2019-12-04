<html xmlns:th="http://www.thymeleaf.org">
<#include '../common/header.ftl'>
<style>
    th, td {
        text-align: center;
    }
</style>
<body>

<#--左边框-->
<div id="wrapper" class="toggled">
    <#include '../common/nav.ftl'>


<#--内容区域-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12column">
                    <nav class="navbar navbar-default navbar-inverse" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse"
                                    data-target="#bs-example-navbar-collapse-1"><span
                                    class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
                                    class="icon-bar"></span><span class="icon-bar"></span></button>
                            <a class="navbar-brand" href="#">Brand</a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="active">
                                    <a href="#">Link</a>
                                </li>
                                <li>
                                    <a href="#">Link</a>
                                </li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong
                                            class="caret"></strong></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="#">Action</a>
                                        </li>
                                        <li>
                                            <a href="#">Another action</a>
                                        </li>
                                        <li>
                                            <a href="#">Something else here</a>
                                        </li>
                                        <li class="divider">
                                        </li>
                                        <li>
                                            <a href="#">Separated link</a>
                                        </li>
                                        <li class="divider">
                                        </li>
                                        <li>
                                            <a href="#">One more separated link</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                            <form class="navbar-form navbar-left" role="search">
                                <div class="form-group">
                                    <input type="text" class="form-control"/>
                                </div>
                                <button type="submit" class="btn btn-default">Submit</button>
                            </form>
                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="#">Link</a>
                                </li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong
                                            class="caret"></strong></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="#">Action</a>
                                        </li>
                                        <li>
                                            <a href="#">Another action</a>
                                        </li>
                                        <li>
                                            <a href="#">Something else here</a>
                                        </li>
                                        <li class="divider">
                                        </li>
                                        <li>
                                            <a href="#">Separated link</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </nav>
                    <table style="width: 97%;margin-left: 25px" class="table table-bordered table-condensed">
                        <thead>
                        <tr id="tr">
                            <th>
                                类目编号
                            </th>
                            <th>
                                名称
                            </th>
                            <th>
                                type
                            </th>
                            <th>
                                创建时间
                            </th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list categoryList as category>
                <tr>
                    <td>
                        ${category.categoryId}
                    </td>
                    <td>
                        ${category.categoryName}
                    </td>
                    <td>
                        ${category.categoryType}
                    </td>
                    <td>${category.createTime}</td>
                    <td>${category.updateTime}</td>
                    <td>
                        <a href="/sell/seller/category/detail?categoryId=${category.categoryId}">修改</a>
                    </td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                <#--分页-->
                    <#--<ul class="pagination pagination-sm" style="float: right;margin-right: 20px;margin-top: -5px">-->

                <#--<#if currentPage lte 1>-->
                <#--<li class="disabled"><a href="#">上一页</a></li>-->
                <#--<#else >-->
                <#--<li><a href="/sell/seller/product/list?page=${currentPage - 1}&size=10">上一页</a></li>-->
                <#--</#if>-->
                  <#--<#list 1..productPageList.getTotalPages() as index>-->
                      <#--<#if currentPage == index>-->
                <#--<li class="disabled"><a href="#">${index}</a></li>-->
                      <#--<#else >-->
                  <#--<li><a href="/sell/seller/product/list?page=${index}&size=10">${index}</a></li>-->
                      <#--</#if>-->
                  <#--</#list>-->
                 <#--<#if currentPage gte productPageList.getTotalPages()>-->
                <#--<li class="disabled"><a href="#">下一页</a></li>-->
                 <#--<#else >-->
                <#--<li><a href="/sell/seller/product/list?page=${currentPage + 1}&size=10">下一页</a></li>-->
                 <#--</#if>-->
                    <#--</ul>-->
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<#--<h3>${orderPageList.getTotalElements()}</h3>-->