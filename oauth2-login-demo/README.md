## spring oauth github登录

- github的开发者设置里面创建一个OAuth Apps
- 配置HomeUrl 和 Authorization callback URL
> Authorization callback URL: http://localhost:8080/login/oauth2/github,这个是spring针对github的默认配置，我们不需要自己写这个接口

- 记录下clientID：b7aa563a3c9b85adaf36,secret key:5db6787a993fe682d7d8d1ff7a4db59fc12c7677