package com.emx.platform.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TreeDeptUtil {
	 public List<TreeDept> menuCommon;
	    public List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	 
	    public List<Map<String,Object>> treeMenu(List<TreeDept> menu){
	        this.menuCommon = menu;
	        for (TreeDept TreeEntity : menu) {
	            Map<String,Object> mapArr = new LinkedHashMap<String, Object>();
	            if("0".equals(TreeEntity.getPid())){
	                setTreeMap(mapArr,TreeEntity);
	                list.add(mapArr);
	            }
	        }
	        return list;
	    }
	    
	 
	    public List<?> menuChild(String id){
	        List<Object> lists = new ArrayList<Object>();
	        for(TreeDept a:menuCommon){
	            Map<String,Object> childArray = new LinkedHashMap<String, Object>();
	            if(a.getPid() .equals(id)){
	               setTreeMap(childArray,a);
	               lists.add(childArray);
	            }
	        }
	        return lists;
	    }
	 
	    private void setTreeMap(Map<String,Object> mapArr,TreeDept TreeEntity){
	        mapArr.put("deptId", TreeEntity.getDeptId());
	        mapArr.put("pid", TreeEntity.getPid());
	        mapArr.put("name", TreeEntity.getName());
	        mapArr.put("code", TreeEntity.getCode());
	        mapArr.put("leader", TreeEntity.getLeader());
	        mapArr.put("city", TreeEntity.getCity());
	        mapArr.put("regon", TreeEntity.getRegon());
	        mapArr.put("address", TreeEntity.getAddress());
	        mapArr.put("stat", TreeEntity.getStat());
	        List<?> children = menuChild(TreeEntity.getDeptId());
	        if(children.size()>0){
	            mapArr.put("hasChild",true);
	        }
	        else{
	            mapArr.put("hasChildren",false);
	        }
	        mapArr.put("children", menuChild(TreeEntity.getDeptId()));
	    }
	 
	   /* public  static void main(String[] args){
	 
	        List<TreeEntity> TreeEntityList = new ArrayList<>();
	        TreeEntity TreeEntity1 = new TreeEntity("1","0","首页");
	        TreeEntity TreeEntity2 = new TreeEntity("2","0","订单");
	        TreeEntity TreeEntity3 = new TreeEntity("3","1","预约");
	        TreeEntity TreeEntity4 = new TreeEntity("4","2","捐献");
	        TreeEntity TreeEntity5 = new TreeEntity("5","4","我的订单");
	        TreeEntity TreeEntity6 = new TreeEntity("6","5","个人中心");
	        TreeEntity TreeEntity7 = new TreeEntity("7","6","个人中心2");
	        TreeEntity TreeEntity8 = new TreeEntity("8","99","个人中心3");
	        TreeEntityList.add(TreeEntity1);
	        TreeEntityList.add(TreeEntity6);
	        TreeEntityList.add(TreeEntity5);
	        TreeEntityList.add(TreeEntity3);
	        TreeEntityList.add(TreeEntity4);
	        TreeEntityList.add(TreeEntity2);
	        TreeEntityList.add(TreeEntity7);
	        TreeEntityList.add(TreeEntity8);
	 
	 
	        TreeUtil treeUtil = new TreeUtil();
	        System.out.print(JsonUtil.toJson(treeUtil.treeMenu(TreeEntityList)));
	 
	    }*/
}
