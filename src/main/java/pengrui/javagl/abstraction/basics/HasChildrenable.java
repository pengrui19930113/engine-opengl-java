package pengrui.javagl.abstraction.basics;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import pengrui.javagl.abstraction.util.CheckUtil;
import pengrui.javagl.abstraction.util.DebugUtil;
import pengrui.javagl.abstraction.util.ExceptionUtil;
import pengrui.javagl.abstraction.util.GlobalConfig;
import pengrui.javagl.abstraction.util.LogUtil;

@SuppressWarnings("all")
public interface HasChildrenable<T extends HasChildrenable<T>> {

	Collection<T> getChildren();
	
	void setChildren(Collection<T> c);
	/**
	 * 在当前子元素的容器中添加 child
	 * @param child
	 */
	default void addChild(T child){
		HasChildrenable.addChild((T)this, child);
	}
	
	/**
	 * 如果当前元素的子孙备中包含了child 则移除
	 * @param child
	 */
	default void removeChild(T child){
		HasChildrenable.removeChild((T)this, child);
	}
	
	/**
	 * 当前元素的子元素是否包含 child(不会查找孙辈元素)
	 * @param child
	 * @return
	 */
	default boolean hasTheChild(T child){
		return HasChildrenable.hasTheChild((T)this, child, false);
	}
	
	T getParent();
	
	default void setParent(T parent){
		HasChildrenable.setParent((T)this, parent);
	}
	
	void setDepth(int d);
	
	int getDepth();// 通常 深度 >= 1
	
	default void printInfo(){// 为了调试方便
		HasChildrenable.printInfo(this);
	} 

		public static boolean isOneLevelBean(Object bean){
		if(CheckUtil.paramNotNull(bean)){
			if(!(bean instanceof HasChildrenable))
				ExceptionUtil.throwRuntimeException("the bean must be implements HasChildrenable");
			
			return 1 == ((HasChildrenable<?>)bean).getDepth();
		}else{
			ExceptionUtil.throwRuntimeException("in param is null");
			return false;
		}
	}
	/**
	 * 如果parent 的子孙节点有多个 child(同一个对象) ，则全部移除
	 * @param parent
	 * @param child
	 */
	public static <G extends HasChildrenable<G>> void removeChild(G parent, G child) {
		removeChild(parent,child,true,true);
	}
	
	/**
	 * 
	 * @param parent
	 * @param child
	 * @param depthProcessChildren 是否递归查找子孙节点移除child
	 * @param removeAllSameLayerSameChild 是否移除同一父节点中所有子节点相同的child 如果 false 则只移出第一个找到的 如果是true 则移除该父节点相同的child子节点
	 */
	public static <G extends HasChildrenable<G>> 
		void removeChild(G parent
				,G child
				,boolean depthProcessChildren
				,boolean removeAllSameLayerSameChild) {
		if(!CheckUtil.paramsAllNotNull(parent,child)){
			return;
		}
		
		List<G> childs = new LinkedList<G>(); // 如果只是单线程的话这里还可以优化为静态成员变量，并且 加上懒加载 ， 让所有的需要深度处理子孙节点的函数 共用这两个容器
		List<G> tmps = new LinkedList<G>();
		childs.add(parent);
		
		boolean clearupSameLayerChild = false;
		LABEL1:
		for(;;){
			if(childs.isEmpty())
				break;
			
			tmps.clear();
			LABEL2:
			for(G c:childs){
					LABEL3:
					while(c.getChildren().contains(child)){// 如果是可重复容器，则循环移除child
						c.getChildren().remove(child);
						if(depthProcessChildren&&removeAllSameLayerSameChild){
							continue;
						}else if(!depthProcessChildren&&removeAllSameLayerSameChild){
							clearupSameLayerChild = true;
							continue ;
						}else if(depthProcessChildren&&!removeAllSameLayerSameChild){
							break LABEL3;
						}else if(!depthProcessChildren&&!removeAllSameLayerSameChild){
							break LABEL1;
						}							
					}
					tmps.addAll(c.getChildren());
			}
			if(clearupSameLayerChild){
				break LABEL1;
			}
			childs.clear();
			for(G cc:tmps){
				childs.addAll(cc.getChildren());
			}
		}
	}
	
	public static <G extends HasChildrenable<G>> void setParent(G child, G parent) {
		if(!CheckUtil.paramsAllNotNull(child,parent)){
			return;
		}

		G pparent;
		while (null != (pparent = parent.getParent())) {
			if (child == pparent) {// 避免成环
				LogUtil.info(
						String.format("warning! parent's parents has child , that is loop , invalid set parent , %s",
								getClassInfo(parent.getClass(), child.getClass())));
				return;
			}
		}
		child.setParent(parent);
		if (parent.getDepth() < 1)
			LogUtil.info("warning! parent's depth is less than 1 , there is error maybe!");

		child.setDepth(parent.getDepth() + 1);
		
		if(GlobalConfig.DEPTH_INFO_ENABLE)
			DebugUtil.depthMessage("setDrawableParent", child.getDepth(),child.getClass());
	}

	/**
	 * 假如 child 是parent 的子元素 则不会添加(不会深度查询子孙元素)
	 * @param parent
	 * @param child
	 */
	public static <G extends HasChildrenable<G>> void addChild(G parent, G child) {
		if(!CheckUtil.paramsAllNotNull(parent,child))
			return;

		if(null == parent.getChildren()){
			parent.setChildren(new LinkedList<G>());
		}
		if (!hasTheChild(parent,child,false)) {
			parent.addChild(child);
		} else {
			if(GlobalConfig.DEPTH_INFO_ENABLE)
				DebugUtil.depthMessage("child's depth parent's class", child.getDepth(), parent.getClass());
		}
	}

	/**
	 * 如果 child 是parent 的子孙元素 则返回 true
	 * @param parent
	 * @param child
	 * @return
	 */
	public static <G extends HasChildrenable<G>> boolean hasTheChild(G parent, G child) {
		return hasTheChild(parent,child,true);
	}
	
	/**
	 * 
	 * @param parent
	 * @param child
	 * @param deepthSearch 是否深度查询子孙节点包含child
	 * @return
	 */
	public static <G extends HasChildrenable<G>> boolean hasTheChild(G parent, G child,boolean deepthSearch) {
		if (null == child || null == parent) {
			LogUtil.debug("test has the child param invalid");
			ExceptionUtil.throwRuntimeException("test has the child param invalid");
		}
		
		List<G> childs = new LinkedList<G>();
		List<G> tmps = new LinkedList<G>();
		childs.add(parent);
		for(;;){
			if(childs.isEmpty())
				break;
			
			tmps.clear();
			for(G c:childs){
				if(c.getChildren().contains(child)){
					return true;
				}
				if(!deepthSearch){
					return false;
				}
				tmps.addAll(c.getChildren());
			}
			childs.clear();
			for(G cc:tmps){
				childs.addAll(cc.getChildren());
			}
		}
		return false;
	}

	
	public static <G extends HasChildrenable> void printTree(G hasChildrenable) {
		if (!CheckUtil.paramNotNull(hasChildrenable))
			return;

		List<G> ls = new LinkedList<G>(); // 当前层级指针
		List<G> tmp = new LinkedList<G>();
		ls.add(hasChildrenable);
		for (;;) {// 树的层级遍历
			if (ls.isEmpty())
				break;

			tmp.clear();
			for (G d : ls) {
				tmp.add(d);// 保留当前层的所有元素
				d.printInfo();
			}
			ls.clear();// 清除零级根元素
			for (G ds : tmp) {
				ls.addAll(ds.getChildren());// 用下面的循环效果一样
				// for (G dds : ds.getChildren())
				// ls.add(dds);// 将当前层指针指向 下一层 的所有元素
			}
		}
	}

	/**
	 * 找到该节点的最上级父节点并打印整棵树
	 * 如果找到
	 * @param hasChildrenable
	 */
	public static <G extends HasChildrenable<G>> 
		void findTopLevelNodeAndCheckItIsOneLevelNodeAndPrintTree
			(G hasChildrenable
				,boolean throwExceptionIfErrorRaletive) {
		
		if (!CheckUtil.paramNotNull(hasChildrenable))
			return;

		G lastLater = hasChildrenable;
		G last = null;

		do {
			last = lastLater;
			if (lastLater.getDepth() > 1)
				continue;
		} while (null != (lastLater = hasChildrenable.getParent()));

		if (last.getDepth() != 1) {
			if(throwExceptionIfErrorRaletive){
				printTree(last);
				ExceptionUtil.throwRuntimeException("error raletive");
			}else{
				LogUtil.error("the depth of the top level node is not 1");
			}
		}
		printTree(last);
	}

	public static <G extends HasChildrenable> void printInfo(G g) {
		if (CheckUtil.paramNotNull(g))
			LogUtil.debug(String.format("depth:%d,class:%s,%s"
					, g.getDepth()
					,g.getClass()
					,g.toString()));
	}
	
	static void printParamIsNullMessage() {
		LogUtil.debug("class is null , ignore");
	}

	static String getClassInfo(Class<?> clazz) {
		if (CheckUtil.paramNotNull(clazz))
			return String.format("classInfo:%s ", clazz);
		else {
			printParamIsNullMessage();
			return "";
		}
	}

	static String getClassInfo(Class<?> parentClass, Class<?> childClass) {
		if (CheckUtil.paramsAllNotNull(parentClass, childClass))
			return String.format("classInfo: parent:%s,child%s ", parentClass, childClass);
		else {
			printParamIsNullMessage();
			return "";
		}
	}


}
