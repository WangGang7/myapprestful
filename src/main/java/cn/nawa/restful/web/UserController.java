package cn.nawa.restful.web;

import cn.nawa.restful.bean.JsonResult;
import cn.nawa.restful.bean.User;
import cn.nawa.restful.service.UserService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * @author nawa
 * @ClassName cn.saytime.web.UserController
 * @Description
 */
@RestController
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LogManager.getLogger();

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserById (@PathVariable(value = "id") Long id){
        logger.info("Get, id:" + id);
        JsonResult r = new JsonResult();
        try {
            User user = userService.selectById(id.intValue());
            if (user != null) {
                r.setResult(user);
                r.setStatus("ok");
            } else {
                r.setStatus("error");
            }
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 查询用户列表
     * @return
     */
    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "Long", paramType = "path")
    })
    @RequestMapping(value = "/getAll/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserList (@PathVariable(value = "pageNum") Long pageNum, @PathVariable(value = "pageSize") Long pageSize){
        JsonResult r = new JsonResult();
        logger.info("GetAll, pageNum:" + pageNum + ", pageSize:" + pageSize);
        try {
            PageHelper.startPage(pageNum.intValue(), pageSize.intValue());
            List<User> userList = userService.selectAllUser();
            r.setResult(userList);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> add (@RequestBody User user){
        JsonResult r = new JsonResult();
        logger.info("add, user:" + user);
        try {
            user.setCreatetime(null);
            user.setUpdatetime(null);
            user.setId(null);
            userService.addUser(user);
            user = userService.selectById(user.getId());
            r.setResult(user);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");

            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JsonResult> delete (@PathVariable(value = "id") Long id){
        JsonResult r = new JsonResult();
        logger.info("del, id:" + id);
        try {
            userService.deleteUser(id.intValue());
            r.setResult(id);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");

            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 根据id修改用户信息
     * @param user
     * @return
     */
    @ApiOperation(value="更新信息", notes="根据url的id来指定更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<JsonResult> update (@PathVariable("id") Long id, @RequestBody User user){
        logger.info("update, user:" + user);
        JsonResult r = new JsonResult();
        try {
            User u = userService.selectById(id.intValue());
            u.setName(user.getName());
            u.setComment(user.getComment());
            u.setUpdatetime(null);
            userService.updateUser(u);
            u = userService.selectById(u.getId());
            r.setResult(u);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");

            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/termsOfService", method = RequestMethod.GET)
    public String termsOfService() {
        return "This is a private Restful service, If you want to use it, please contact Nawa.";
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String  jsonTest() {
        return " hi guy!";
    }


}