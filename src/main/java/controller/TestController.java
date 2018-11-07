package controller;

import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SlaveService;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SlaveService slaveService;

    public SlaveService getSlaveService() {
        return slaveService;
    }

    public void setSlaveService(SlaveService slaveService) {
        this.slaveService = slaveService;
    }
/*

select u.id userid,u.nickname nickname,up.truename truename,
up.company cengji,up.varcharField4 zhuanye,us.score score,us.year year,
c.id courseId,c.buyable courseType,c.title courseName
from user u
left join user_profile up on u.id = up.id
left join user_score us on up.id = us.userId
left join course c on us.courseId = c.id


select u.id userId,cm.courseId courseId,cm.createdTime createdTime
from user u left join course_member cm on u.id = cm.userId
 */
    @RequestMapping("/getTest")
    @ResponseBody
    public String getTest(HttpServletResponse response){
        int result = slaveService.count();
        Map map = new HashMap();
        map.put("count",result);
        response.setHeader("Transfer-Encoding","chunked");
        return JSONUtils.toJSONString(map);
    }
}
