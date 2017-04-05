/**
 * Created by robin on 17/4/5.
 */
var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
var cnzz_tag = document.createElement('script');
cnzz_tag.type = "text/javascript";
cnzz_tag.async = true;
cnzz_tag.charset = 'utf-8';
cnzz_tag.src = cnzz_protocol + "s4.cnzz.com/z_stat.php?id=1261615827&async=1";
var root_s =document.getElementsByTagName('script')[0];
root_s.parentNode.insertBefore(cnzz_tag, root_s);