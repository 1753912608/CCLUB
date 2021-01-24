package cclub.demo.mapper.sqlProvider;

import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class ExamProvider {


    /**
     *
     * @param exam_id
     * @param more
     * @return
     * 获取批量通知候选人的列表
     */
    public String getNoticeList(String exam_id,int more){
        return new SQL(){{
            SELECT("*");
            FROM("exam_user");
            if(more==0){
                WHERE("exam_notice=#{more}");
            }
            WHERE("exam_id=#{exam_id}");
        }}.toString();
    }



    /**
     *
     * @param question_id
     * @param choice_question_name
     * @param question_options
     * @param choice_question_answer
     * @param choice_question_difficult
     * @param choice_question_score
     * @param choice_question_remarks
     * @param user_id
     * @return
     * 添加选择题
     */
    public String addChoiceQuestion(String question_id,
                                    String choice_question_name,
                                    String[] question_options,
                                    String choice_question_answer,
                                    int choice_question_difficult,
                                    int choice_question_score,
                                    String choice_question_remarks,
                                    String user_id)
    {
        return new SQL(){{
            INSERT_INTO("choice_question");
            VALUES("choice_question_id","#{question_id}");
            VALUES("choice_question_name","#{choice_question_name}");
            VALUES("choice_question_answer","#{choice_question_answer}");
            VALUES("choice_question_created_user_id","#{user_id}");
            VALUES("choice_question_difficult","#{choice_question_difficult}");
            VALUES("choice_question_score","#{choice_question_score}");
            VALUES("choice_question_remarks","#{choice_question_remarks}");
            VALUES("choice_question_option_A","#{question_options[0]}");
            VALUES("choice_question_option_B","#{question_options[1]}");
            VALUES("choice_question_option_C","#{question_options[2]}");
            if(question_options.length>3){
                VALUES("choice_question_option_D","#{question_options[3]}");
            }
            if(question_options.length>4){
                VALUES("choice_question_option_E","#{question_options[4]}");
            }
            if(question_options.length>5){
                VALUES("choice_question_option_F","#{question_options[5]}");
            }
            if(question_options.length>6){
                VALUES("choice_question_option_G","#{question_options[6]}");
            }
        }}.toString();
    }



    /**
     *
     * @param question_id
     * @param question_type
     * @return
     * 删除题库中试题
     */
    public String deleteMySubjectQuestion(String question_id,int question_type)
    {
        return new SQL(){{
            if(question_type==1){
                DELETE_FROM("choice_question");
                WHERE("choice_question_id=#{question_id}");
            }else if(question_type==2){
                DELETE_FROM("judge_question");
                WHERE("judge_question_id=#{question_id}");
            }else if(question_type==3){
                DELETE_FROM("completion_question");
                WHERE("completion_question_id=#{question_id}");
            }
        }}.toString();
    }



    /**
     *
     * @param question_id
     * @param typeQuestion
     * @return
     * 获取单个试题信息
     */
    public String getOneQuestion(String question_id,int typeQuestion){
        return new SQL(){{
            SELECT("*");
            if(typeQuestion==1){
                FROM("choice_question");
                WHERE("choice_question_id=#{question_id}");
            }else if(typeQuestion==2){
                FROM("judge_question");
                WHERE("judge_question_id=#{question_id}");
            }else if(typeQuestion==3){
                FROM("completion_question");
                WHERE("completion_question_id=#{question_id}");
            }
        }}.toString();
    }


    /**
     *
     * @param question_id
     * @param choice_question_name
     * @param question_options
     * @param choice_question_answer
     * @param choice_question_difficult
     * @param choice_question_score
     * @param choice_question_remarks
     * @return
     * 更新选择题信息
     */
    public String updateChoiceQuestion(String question_id,
                                       String choice_question_name,
                                       String[] question_options,
                                       String choice_question_answer,
                                       int choice_question_difficult,
                                       int choice_question_score,
                                       String choice_question_remarks)
    {
        return new SQL(){{
            UPDATE("choice_question");
            SET("choice_question_name=#{choice_question_name}");
            SET("choice_question_answer=#{choice_question_answer}");
            SET("choice_question_difficult=#{choice_question_difficult}");
            SET("choice_question_score=#{choice_question_score}");
            SET("choice_question_remarks=#{choice_question_remarks}");
            SET("choice_question_option_A=#{question_options[0]}");
            SET("choice_question_option_B=#{question_options[1]}");
            SET("choice_question_option_C=#{question_options[2]}");
            if(question_options.length>3){
                SET("choice_question_option_D=#{question_options[3]}");
            }
            if(question_options.length>4){
                SET("choice_question_option_E=#{question_options[4]}");
            }
            if(question_options.length>5){
                SET("choice_question_option_F=#{question_options[5]}");
            }
            if(question_options.length>6){
                SET("choice_question_option_G=#{question_options[6]}");
            }
            WHERE("choice_question_id=#{question_id}");
        }}.toString();
    }



}
