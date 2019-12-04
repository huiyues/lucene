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
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <ul class="breadcrumb" >
                        <li>
                            <a href="#">首页</a>
                        </li>
                        <li>
                            <a href="/sell/seller/order/list">订单列表</a>
                        </li>
                        <li class="active">
                            订单详情
                        </li>
                    </ul>
                </div>
                <div class="col-md-4 column">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>
                                订单编号
                            </th>
                            <th>
                                订单总金额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                            ${orderDTO.orderId}
                            </td>
                            <td>
                            ${orderDTO.orderAmount}￥
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>
                                商品编号
                            </th>
                            <th>
                                商品名称
                            </th>
                            <th>
                                价格
                            </th>
                            <th>
                                数量
                            </th>
                            <th>
                                总额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                <#list orderDTO.getOrderDetailList() as orderDetail>
                <tr>
                    <td>
                        ${orderDetail.productId}
                    </td>
                    <td>
                        ${orderDetail.productName}
                    </td>
                    <td>
                        ${orderDetail.productPrice}￥
                    </td>
                    <td>
                        ${orderDetail.productQuantity}
                    </td>
                    <td>
                        ${orderDetail.productPrice * orderDetail.productQuantity}￥
                    </td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>

            <#--操作-->
                <div class="col-md-12 column">
                <#if orderDTO.getOrderStatusEnum().getMessage() == '新订单'>
                    <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button"
                       class="btn btn-default btn-primary">完结订单</a>
                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button"
                       class="btn btn-default btn-danger">取消订单</a>
                </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>