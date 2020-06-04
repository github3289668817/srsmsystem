package per.xgt.service.impl;

import org.springframework.stereotype.Service;
import per.xgt.dao.loginlogMapper;
import per.xgt.pojo.Loginlog;
import per.xgt.service.loginlogService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class loginlogServiceImpl implements loginlogService {

    @Resource
    private loginlogMapper loginlogMapper;

    @Override
    public int insertLoginlog(Loginlog loginlog) {
        return loginlogMapper.insertLoginLog(loginlog);
    }

    @Override
    public List<Loginlog> getAllLoginlogs(String userNo) {
        List<Loginlog> logs = loginlogMapper.getAllLoginlogs(userNo);
        for (Loginlog log:logs) {
            String time = log.getLoginLogCreatetime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = sdf.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String LoginLogCreatetime = sdf.format(date);
            log.setLoginLogCreatetime(LoginLogCreatetime);
        }

        return logs;
    }

}
