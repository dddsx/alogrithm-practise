package test;

import java.util.List;

import engine.SearchBase;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		SearchBase search = SearchBase.getSerachBase();
		search.add("1", "你好！", "你好！");
		search.add("2", "你好！我是张三。", "你好！我是张三。");
		search.add("3", "今天的天气挺好的。", "今天的天气挺好的。");
		search.add("4", "你是谁？", "你是谁？");
		search.add("5", "高数这门学科很难", "高数确实很难。");
		search.add("6", "测试", "上面的只是测试");
		String ids = search.getIds("您好三测");
		System.out.println(ids);
		List<Object> objs = search.getObjects(ids);
		if (objs != null) {
			for (Object obj : objs) {
				System.out.println((String) obj);
			}
		}
	}

}
