package com.newcms.web.controller;

import com.newcms.core.feature.orm.mybatis.Page;
import com.newcms.web.model.User;
import com.newcms.web.security.PermissionSign;
import com.newcms.web.security.RoleSign;
import com.newcms.web.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户控制器
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     * 
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                return "redirect:/";
            }
            if (result.hasErrors()) {
                model.addAttribute("error", "参数错误！");
                return "login";
            }
            // 身份验证
            subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
            // 验证成功在Session中保存用户信息
            final User authUserInfo = userService.selectByUsername(user.getUsername());
            request.getSession().setAttribute("userInfo", authUserInfo);
        } catch (AuthenticationException e) {
            // 身份验证失败
            model.addAttribute("error", "用户名或密码错误 ！");
            return "login";
        }
        return "redirect:/";
    }

    /**
     * 用户登出
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("userInfo");
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    /**
     * 基于角色 标识的权限控制案例
     */
    @RequestMapping(value = "/admin")
    @ResponseBody
    @RequiresRoles(value = RoleSign.ADMIN)
    public String admin() {
        return "拥有admin角色,能访问";
    }

    /**
     * 基于权限标识的权限控制案例
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    @RequiresPermissions(value = PermissionSign.USER_CREATE)
    public String create() {
        return "拥有user:create权限,能访问";
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String queryUser(Model m){
        List list = userService.selectList();
        m.addAttribute("userlist",list);
        return "templates";
    }

    @RequestMapping(value = "/query1")
    public String queryUser(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        String page = request.getParameter("page");
        int pageIndex = 0;
        if(null!=page){
            pageIndex = (Integer.parseInt(page)==0? 1:Integer.parseInt(page));//当前页
        }
        else{
            pageIndex = 1;
        }
        int pageSize = 5;//这里用来设置每页要展示的数据数量，建议把这个写到web.config中来全局控制
        //获取需要展示的部门数据
        Page pageUtil = new Page(pageIndex,pageSize);
        List list = userService.selectByPage(pageUtil);
        //得到数据的条数
        int rowCount = list.size();
        //通过计算，得到分页应该需要分几页，其中不满一页的数据按一页计算
        if(rowCount%pageSize!=0)
        {
            rowCount = rowCount / pageSize + 1;
        }
        else
        {
            rowCount = rowCount / pageSize;
        }
        StringBuffer sbf = new StringBuffer();
        String str1="{'username':'zhangsan',password:'1111111','createTime':'20161123'}";
        for(int i=0;i<5;i++){
            if(i>0){
                sbf.append(","+str1);
            }
            else{
                sbf.append(str1);
            }

        }

        System.out.println(pageUtil.getResult().toString());
        System.out.println(pageUtil.getTotalCount());
        String jsonstr="["+sbf.toString()+"]";
        //转成Json格式
        String strResult = "{\"pageCount\":"+11+",\"CurrentPage\":"+pageIndex+",\"list\":" +jsonstr +"}";
        response.getWriter().write(strResult);
        response.getWriter().close();
        return "templates";
    }
}
