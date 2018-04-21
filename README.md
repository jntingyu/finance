# finance

# 集成框架

# 整体页面和接口说明
/page/{pageName}	跳转到无登录验证的页面
/check/**			处理无登录验证的接口
/view/{module}/{viewName}	跳转到需要登录验证的页面
!/check/**			处理登录验证的接口

#首页模块 index


# 用户模块 user

## 注册
页面：GET /page/register
接口：POST /check/register

## 登录
页面：GET /page/login
接口：POST /check/login

## 忘记密码
页面：GET /page/forgetPwd
请求邮件接口：GET /check/forgetPwd/{userName}
修改接口：PUT /check/forgetPwd/{userName}

## 修改密码
页面：GET /view/user/changePwd
接口：PUT /user/changePwd

## 绑定邮箱
页面：GET /view/user/bindingEmail
请求邮件接口：GET /user/bindingEmail
接口：PUT /user/bindingEmail

## 注销
接口：GET /user/logout

## 查看个人信息
页面：GET /view/user/myInfo
接口：GET /user/myInfo

## 设置头像
接口：PUT /user/changeProfilePic

## 个人信息修改
页面：GET /view/user/updateInfo
接口：PUT /user/updateInfo


# 记账单模块

## 我的记账单列表
页面：GET /view/bill/list
接口：GET /bill

## 添加记账单
页面：GET /view/bill/add
接口：POST /bill

## 修改记账单
页面：GET /view/bill/update
接口：PUT /bill/{id}

## 删除记账单
接口：DELETE /bill/{id}


# 分类模块

## 新增分类
接口：/classify

## 修改分类
接口：/classify

## 删除分类
接口：/classify
