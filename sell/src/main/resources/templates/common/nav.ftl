<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                卖家管理系统
            </a>
        </li>
        <li class="dropdown open">
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">订单</li>
                <li>
                    <a href="/sell/seller/order/list"><i class="fa fa-fw fa-list-alt"></i> 列表</a>
                </li>
            </ul>
        </li>

        <li class="dropdown open">
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">商品</li>
                <li><a href="/sell/seller/product/list">列表</a></li>
                <li><a href="/sell/seller/product/index">新增</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">类目</li>
                <li><a href="/sell/seller/category/list">列表</a></li>
                <li><a href="/sell/seller/category/detail?categoryId=">新增</a></li>
            </ul>
        </li>

        <li class="dropdown open">
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">功能</li>
                <li>
                    <a href="/sell/seller/user/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
                </li>
            </ul>
        </li>
    </ul>
</nav>