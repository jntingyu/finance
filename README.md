# finance
# /page/*	跳转到无登录验证的页面
# /check/*	处理无登录验证的接口
# /view/*	跳转到需要登录验证的页面
# !/check/*	处理登录验证的接口

首页模块 index


用户模块 user

注册
页面：/page/register
接口：/check/register

登录
页面：/page/login
接口：/check/login

忘记密码
页面：/page/forgetPwd
接口：/check/forgetPwd

修改密码
页面：/view/changePwd
接口：/user/changePwd

绑定邮箱
页面：/view/bindingEmail
接口：/user/bindingEmail

注销
接口：/user/logout

查看个人信息
页面：/view/myInfo
接口：/user/myInfo

设置头像
接口：/user/changeProfilePic

个人信息修改
页面：/view/updateInfo
接口：/user/updateInfo
