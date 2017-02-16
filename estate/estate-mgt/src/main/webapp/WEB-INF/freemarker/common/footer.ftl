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
<script src="${contextPath}/js/libs/require.min.js?vn=${bts!}"></script>
<script src="${contextPath!}/js/require-config.js?vn=${bts!}"></script>
<script src="${contextPath}/js/app/index.min.js"></script>
<script src="${contextPath}/js/app/identity/userInfo.js"></script>
</body>
</html>