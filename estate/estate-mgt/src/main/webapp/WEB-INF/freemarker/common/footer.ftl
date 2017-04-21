<input hidden="hidden" id="contextPath" value="${contextPath!}"/>
<input hidden="hidden" id="clientId" value="${clientId!}"/>
<input hidden="hidden" id="vn" value="${bts!}"/>
<input hidden="hidden" id="baiduKey" value="${mapKey!}"/>
<footer class="main-footer">
    Copyright &copy; 2014-2016 <a href="#">Yingke Estate</a>.All rights
    reserved.
</footer>
</div>
<script type="text/javascript">
    var contextPath = document.getElementById('contextPath').value;
    var clientId = document.getElementById('clientId').value;
    var vn = document.getElementById('vn').value;
    var baiduKey = document.getElementById('baiduKey').value;
</script>
<script src="${contextPath}/js/libs/require.min.js?vn=${bts!}"></script>
<script src="${contextPath!}/js/require-config.js?vn=${bts!}"></script>
<script src="${contextPath}/js/app/identity/userInfo.js"></script>
<script src="${contextPath!}/js/cnzz/index.js?vn=${bts!}"></script>
</body>
</html>