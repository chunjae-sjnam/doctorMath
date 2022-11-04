package com.chunjae.doctormath.main.operation;


import com.chunjae.doctormath.common.StringUtil;
import com.chunjae.doctormath.common.tree.Node;
import com.chunjae.doctormath.common.tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 운영관리>문자관리
 * service
 */
@Service
public class TextManagementService {
    @Autowired
    private TextManagementMapper textManagementMapper;

    /**
     * 운영관리>문자관리
     * 문자전송 화면
     * @param param
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectHisSent(Map<String, Object> param) throws Exception{
        Map<String, Object> result = new HashMap<>();

        // 수신자 목록에 세팅 되어있는 수신인 list
        List<Map<String, Object>> smsReciver =textManagementMapper.selectreciverList(param);

        // 첫번째 수신인의 발송 내역 가져오기
        List<Map<String, Object>> smsHis = new ArrayList();
        if(smsReciver.size()>0){
            smsHis =textManagementMapper.selectHisSent(smsReciver.get(0));
//            if(null != smsHis || !smsHis.isEmpty()){
//                result.put("smsHis", smsHis);
//            }
        }

        // 템플릿 가져오기
        Integer templateCnt = textManagementMapper.templateCnt(param);
        List<Map<String, Object>> templateList;
        if(templateCnt >0){
            templateList = textManagementMapper.getTemplateListWithMark(param);
        }else{
            //기본 템플렛을 tb_SMS_template에 저장, 가져오기
            textManagementMapper.insertToTbSMSTemplate(param);
            templateList = textManagementMapper.getTemplateListWithMark(param);
        }

        result.put("smsReciver", smsReciver);
        result.put("smsHis", smsHis);
        result.put("templateList", templateList);

        return result;
    }

    public Tree getTreeClassList(Map<String,Object> param) throws Exception{
        Tree tree = new Tree();
        List<Map<String,Object>> listOfClassification = textManagementMapper.getClassList(param);
        for(Map<String,Object> classification : listOfClassification){
            List<Node> nodes = new ArrayList<>();
            nodes.add(new Node(Long.parseLong(StringUtil.stringNull(classification.get("seq"))),classification.get("ClassName"),1));
            nodes.add(new Node(Long.parseLong(StringUtil.stringNull(classification.get("StudentCode")).substring(1)),classification.get("Name"),2));
            tree.addNode(nodes);

        }
        return tree;

    }

    public List<Map<String, Object>> selectTemplates(Map<String, Object> param) throws Exception{
        List<Map<String,Object>> templates=textManagementMapper.getTemplateList(param);
        return templates;
    }
}
