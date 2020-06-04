package per.xgt.service;

import per.xgt.pojo.Loginlog;

import java.util.List;

public interface loginlogService {

    public int insertLoginlog(Loginlog loginlog);

    public List<Loginlog> getAllLoginlogs(String userNo);

}
