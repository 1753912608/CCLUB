package cclub.demo.service;

import cclub.demo.dao.exam.exam;
import java.util.List;


public interface ExamService {


    /**
     *
     * @param exam
     * @return
     * 新建笔试
     */
    int createExam(exam exam);


    /**
     *
     * @param user_id
     * @return
     * 根据当前用户的id获取创建的所有笔试
     */
    List<exam>getMyCreatedExamList(String user_id);
}
