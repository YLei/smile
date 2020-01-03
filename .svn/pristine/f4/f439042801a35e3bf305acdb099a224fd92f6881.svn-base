package com.emx.platform.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TreeUtil {
	
    public List<TreeEntity> menuCommon;
    public List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
 
    public List<Map<String,Object>> treeMenu(List<TreeEntity> menu){
        this.menuCommon = menu;
        for (TreeEntity TreeEntity : menu) {
            Map<String,Object> mapArr = new LinkedHashMap<String, Object>();
            if(TreeEntity.getParentId().equals("-1")){
                setTreeMap(mapArr,TreeEntity);
                list.add(mapArr);
            }
        }
        return list;
    }
    
 
    public List<?> menuChild(String id){
        List<Object> lists = new ArrayList<Object>();
        for(TreeEntity a:menuCommon){
            Map<String,Object> childArray = new LinkedHashMap<String, Object>();
            if(a.getParentId() .equals(id)){
               setTreeMap(childArray,a);
               lists.add(childArray);
            }
        }
        return lists;
    }
 
    private void setTreeMap(Map<String,Object> mapArr,TreeEntity TreeEntity){
        mapArr.put("id", TreeEntity.getId());
        mapArr.put("name", TreeEntity.getName());
        mapArr.put("parentId", TreeEntity.getParentId());
        mapArr.put("url",TreeEntity.getUrl());
        List<?> childrens = menuChild(TreeEntity.getId());
        if(childrens.size()>0){
            mapArr.put("hasChild",true);
        }
        else{
            mapArr.put("hasChildren",false);
        }
        mapArr.put("childrens", menuChild(TreeEntity.getId()));
    }
}
