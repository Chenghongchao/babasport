# babasport
巴巴运动商城(前台+后台)
# 架构说明
  1.spring + spring mvc + mybatis<br>
  2.数据库mysql<br>
  3.商品列表使用oscache进行缓存<br>
  4.商品的详细页使用freemarker静态化技术生成静态化页面<br>
  5.通过3,4操作减轻服务压力<br>
  6.图片服务器和应用服务分离.使用Jersey框架进行图片上传<br>
# 待完善功能
  1.配置solr对商品列表搜索功能减压<br>
  2.使用memcache/redis进行分布式缓存处理,达到多集群项目共用session<br>
  
