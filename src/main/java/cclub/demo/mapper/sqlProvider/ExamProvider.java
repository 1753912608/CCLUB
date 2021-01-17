package cclub.demo.mapper.sqlProvider;

import org.apache.ibatis.jdbc.SQL;

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
}
