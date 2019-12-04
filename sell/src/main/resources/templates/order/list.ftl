<html xmlns:th="http://www.thymeleaf.org">
<#include '../common/header.ftl'>
<style>
    th, td {
        text-align: center;
    }
</style>
<body>
     <#include '../common/index.jsp'>
<#--左边框-->
<div id="wrapper" class="toggled">
    <#include '../common/nav.ftl'>
<#--内容区域-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12column">
                    <table style="width: 97%;margin-left: 25px" class="table table-bordered table-condensed">
                        <thead>
                        <tr id="tr">
                            <th>
                                订单Id
                            </th>
                            <th>
                                姓名
                            </th>
                            <th>
                                手机号
                            </th>
                            <th>
                                地址
                            </th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list orderPageList.content as orderDTO>
                <tr>
                    <td>
                        ${orderDTO.orderId}
                    </td>
                    <td>
                        ${orderDTO.buyerName}
                    </td>
                    <td>
                        ${orderDTO.buyerPhone}
                    </td>
                    <td>
                        ${orderDTO.buyerAddress}
                    </td>
                    <td>${orderDTO.orderAmount}</td>
                    <td>${orderDTO.getOrderStatusEnum().getMessage()}</td>
                    <td>${orderDTO.getPayStatusEnum().getMessage()}</td>
                    <td>${orderDTO.createTime}</td>
                    <td>
                        <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                    </td>
                    <td>
                        <#if orderDTO.getOrderStatusEnum().getMessage() == '新订单'>
                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                <#--分页-->
                    <ul class="pagination pagination-sm" style="float: right;margin-right: 20px;margin-top: -5px">

                <#if currentPage lte 1>
                <li class="disabled"><a href="#">上一页</a></li>
                <#else >
                <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=10">上一页</a></li>
                </#if>
                  <#list 1..orderPageList.getTotalPages() as index>
                      <#if currentPage == index>
                <li class="disabled"><a href="#">${index}</a></li>
                      <#else >
                  <li><a href="/sell/seller/order/list?page=${index}&size=10">${index}</a></li>
                      </#if>
                  </#list>
                 <#if currentPage gte orderPageList.getTotalPages()>
                <li class="disabled"><a href="#">下一页</a></li>
                 <#else >
                <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=10">下一页</a></li>
                 </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
<div class="modal" id="myModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" onclick="javascript:document.getElementById('myAudio').pause()" id="close" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" onclick="location.reload()" class="btn btn-primary">查看</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<audio   id="myAudio" loop="loop"  >
    <source src="/sell/mp3/song.mp3" type="audio/mpeg">
</audio>

</body>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script>

    var ws = null;
    if ("WebSocket" in window) {
        ws = new WebSocket('ws://www.txjava.cn/sell/webSocket')
    } else {
        alert('该浏览器不正常webSocket！')
    }

    ws.onopen = function (event) {
        console.log('建立连接')
    };

    ws.onclose = function (event) {
        console.log('关闭连接')
    };

    ws.onmessage = function (event) {
        console.log('收到消息：' + event.data);
        $(".modal-body").text("主人，" + event.data);
        $("#myModal").modal();

        setTimeout(toggleSound);
        function toggleSound() {
            var music = document.getElementById("myAudio");//获取ID
            console.log(music);
            console.log(music.paused);
            if (music.paused) { //判读是否播放
                music.paused=false;
                music.play(); //没有就播放
            }
        }
    };

    ws.onerror = function () {
        alert('发生错误')
    };

    //关闭webSocket
    window.onbeforeunload = function () {
        ws.close();
    }
</script>
</html>