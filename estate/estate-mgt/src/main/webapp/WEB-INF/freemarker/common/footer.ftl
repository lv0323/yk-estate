<input hidden="hidden" id="contextPath" value="${contextPath!}"/>
<input hidden="hidden" id="clientId" value="${clientId!}"/>
<input hidden="hidden" id="vn" value="${bts!}"/>
<footer class="main-footer">
    Copyright &copy; 2014-2016 <a href="#">Yingke Estate</a>.All rights
    reserved.
</footer>
<script type="text/javascript">
    var contextPath = document.getElementById('contextPath').value;
    var clientId = document.getElementById('clientId').value;
    var vn = document.getElementById('vn').value;
</script>
<!-- jQuery 2.2.3 -->
<script src="${contextPath}/js/libs/jquery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<#--<script src="${contextPath}/js/libs/bootstrap.js"></script>-->
<script src="${contextPath}/js/libs/require.min.js?vn=${bts!}"></script>
<script src="${contextPath!}/js/main.js?vn=${bts!}"></script>
<#--<script src="${contextPath!}/js/service/request-service.js"></script>-->
<!-- FastClick -->
<#--<script src="${contextPath}/js/plugins/fastclick/fastclick.js"></script>-->
<!-- AdminLTE for demo purposes -->
<script src="${contextPath}/js/demo.js"></script>
<!-- AdminLTE App -->
<script src="${contextPath}/js/app.js"></script>
<script src="${contextPath}/js/identity/userInfo.js"></script>
<!-- page script -->
</body>
</html>